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
 * Command block user card.
 *
 * @author Vladislav Serhiychuk
 */
public class Block implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(Block.class);

    /**
     * Read card in session.
     * Check blocked.
     * if check  false return on error.jsp
     * if true modified card. Update in database and session.
     * Return in card menu page.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("card");

        if (!card.getBlocked()) {
            card.setBlocked(true);
            card = CARD_DAO.updateById(card);
            session.setAttribute("card", card);

            LOG.info(CardOperation.BLOCK + card.getAccount());
            SaveEvent.execute(card.getAccount(), 0, 0, CardOperation.BLOCK);

            return PAGE_MENU_CARD;

        } else {
            request.setAttribute(MESSAGE, ERROR_CARD_BLOCKED);
            return PAGE_ERROR;
        }
    }
}
