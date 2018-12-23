package com.svladiko.services.command.client;

import com.svladiko.model.Client;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authorization client logic.
 *
 * @author Vladislav Serhiychuk
 */
public class Authorization implements Command, CommonConstants {

    /**
     * All logic for check data by authorization in the static method CommonLogic.saveIfTrue(req, response).
     * If he return true: return on menuCards.jsp
     * If he return false: return on login.jsp
     *
     * @param request
     * @param response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Client client = CLIENT_DAO.getByLogin(login);

        if (client == null || !(client.getPassword().equals(password))) {
            request.setAttribute(MESSAGE, ERROR_LOGIN);
            return PAGE_ERROR;
        }

        HttpSession session = request.getSession();
        session.setAttribute("cards", client.getCards());
        session.setAttribute("client", client);

        return MENU_CARDS_PAGE;
    }
}
