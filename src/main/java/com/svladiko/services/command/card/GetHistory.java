package com.svladiko.services.command.card;

import com.svladiko.model.Card;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Search data by operations with card,
 * return in history page.
 *
 * @author Vladislav Serhiychuk
 *         Created on 5/5/2015.
 */
public class GetHistory implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(GetHistory.class);

    /**
     * Read card in session.
     * Search info by account in table history,
     * create in Attribute req.
     *
     * @param request
     * @param response
     * @return history.jsp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("card");
        request.setAttribute("account", card.getAccount());
        request.setAttribute("histories", HISTORY_DAO.getByAccountSender(card));

        LOG.info("Account = " + card.getAccount());
        return PAGE_HISTORY_CARD;
    }
}
