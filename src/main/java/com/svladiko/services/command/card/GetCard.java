package com.svladiko.services.command.card;

import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Search by account card and create she in session
 *
 * @author Vladislav Serhiychuk
 *         Created on 4/30/2015.
 */
public class GetCard implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(GetCard.class);


    /**
     * Check account by search card in database.
     * Search card. Save in session.
     *
     * @param request
     * @param response
     * @return if found a map return to PAGE_MENU_CARD else PAGE_ERROR
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = request.getParameter(ACCOUNT);

        LOG.info(account);

        if (account ==null) {
            return PAGE_ERROR;
        }

        session.setAttribute("card", CARD_DAO.getByAccount(Integer.parseInt(account)));
        return PAGE_MENU_CARD;
    }
}
