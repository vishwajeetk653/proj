package com.svladiko.services.command.admin;

import com.svladiko.model.Client;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authorization admin logic.
 *
 * @author Vladislav Serhiychuk
 */
public class Authorization implements Command, CommonConstants {
    /**
     * All logic for check data by authorization in the static method
     * CommonLogic.saveIfTrue(req, response).
     * If he return true: getById all card where parameter
     * card "unload card" and return admin.jsp
     * If he return false: return on login.jsp
     *
     * @param request
     * @param response
     * @return admin.jsp if CommonLogic.execute(req, response) return true.
     * login.jsp if return false.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            Client admin = CLIENT_DAO.getByLogin(login);

            if (admin == null || !(admin.getPassword().equals(password))) {
                request.setAttribute(MESSAGE, ERROR_LOGIN);
                return PAGE_ERROR;
            }

            HttpSession session = request.getSession();
            session.setAttribute("client", admin);
            request.setAttribute("cards", CARD_DAO.getUnlockCards());

            return PAGE_ADMIN;
        }
}
