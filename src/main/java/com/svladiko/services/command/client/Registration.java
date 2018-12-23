package com.svladiko.services.command.client;

import com.svladiko.model.Card;
import com.svladiko.model.Client;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import static com.svladiko.services.utils.Generate.req;
import static com.svladiko.services.utils.Generate.getParam;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Register client in database if
 * it satisfies all the conditions of registration
 *
 * @author Vladislav Serhiychuk
 */
public class Registration implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(Registration.class);
    private String message = ERROR_REGISTER;
    private Client newUser;

    /**
     * All logic for check data by registration
     * in the static method CommonLogic.isValidRegisterData(req, response).
     * This method return address page which depends on this result.
     *
     * @param request
     * @param response
     * @return MENU_CARDS_PAGE  or  PAGE_ERROR
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        req = request;
        newUser = new Client();
        newUser.setName(getParam(NAME));
        newUser.setPhone(getParam(PHONE));
        newUser.setLogin(getParam(LOGIN));
        newUser.setPassword(getParam(PASSWORD));
        newUser.setLastName(getParam(LAST_NAME));
        newUser.setPassport(getParam(PASSPORT));

        if (checkData(newUser)) {
            request.setAttribute(MESSAGE, message);
            return PAGE_ERROR_REGISTER;
        }

        createCardForNewUser(CLIENT_DAO.create(newUser));
        newUser = CLIENT_DAO.getByLogin(newUser);

        HttpSession session = request.getSession();
        session.setAttribute("cards", newUser.getCards());
        session.setAttribute("client", newUser);

        LOG.info("New user " + newUser.getLogin());
        return MENU_CARDS_PAGE;
    }


    private void createCardForNewUser(Client client) {
        Card newCard = new Card();
        newCard.setAccount(generatorNewAccount());
        newCard.setClient(client);
        CARD_DAO.create(newCard);
    }

    private int generatorNewAccount() {
        int account;
        while (true) {
            account = (int) (Math.random() * 10000);
            if (CARD_DAO.getByAccount(account) == null) {
                return account;
            }
        }
    }

    private boolean checkData(Client client) {
        if (client.getLogin() == null) {
            message = "Login = null";
            return true;
        }
        if (CLIENT_DAO.getByLogin(client) != null){
            message = "Sorry this login busy";
            return true;
        }

        if (client.getPassword() == null || client.getPassport() == null ||
                client.getName() == null || client.getLastName() == null || client.getPhone() == null){
            message = "Not all field to filled a register command";
            return true;
        }

        if (client.getLogin().isEmpty() || client.getPassword().isEmpty() ||
                client.getPassport().isEmpty() || client.getName().isEmpty() ||
                client.getLastName().isEmpty() || client.getPhone().isEmpty()) {
            message = "Not all field to filled a register command";
            return true;
        }

        return false;
    }
}

