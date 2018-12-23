package com.svladiko.services.command.card;

import com.svladiko.model.Card;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import com.svladiko.services.utils.CardOperation;
import com.svladiko.services.utils.SaveEvent;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Request to admin unlock card if
 * card blocked
 *
 * @author Vladislav Serhiychuk
 */
public class RequestUnblock implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(RequestUnblock.class);

    /**
     * Search card by account in database.
     * Check card on a blocked.
     * If card not blocked translates to error page
     * If card blocked, unlock card. Update card in database in session and return to card page.
     *
     * @param request
     * @param response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("card");

        if (card.getBlocked()) {
            card.setUnlockCard(true);
            card = CARD_DAO.updateById(card);

            session.setAttribute("card", card);

            SaveEvent.execute(card.getAccount(), 0, 0, CardOperation.PLEASE_UNBLOCK);
            LOG.info(CardOperation.PLEASE_UNBLOCK + card.getAccount());
            return PAGE_MENU_CARD;

        } else {
            request.setAttribute(MESSAGE, ERROR_CARD_NOT_BLOCKED);
            return PAGE_ERROR;
        }
    }
}
