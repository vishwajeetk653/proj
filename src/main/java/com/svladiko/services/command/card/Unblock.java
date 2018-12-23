package com.svladiko.services.command.card;

import com.svladiko.model.Card;
import com.svladiko.services.utils.CardOperation;
import com.svladiko.services.command.Command;
import com.svladiko.services.command.CommonConstants;
import com.svladiko.services.utils.SaveEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The query implementation unlock card
 *
 * @author Vladislav Serhiychuk
 *         Created on 4/27/2015.
 */
public class Unblock implements Command, CommonConstants {
    /**
     * Search card for account,
     * and updateById data for this card
     *
     * @param request
     * @param response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int account = Integer.parseInt(request.getParameter(ACCOUNT));

        Card cardUnblock = CARD_DAO.getByAccount(account);
        cardUnblock.setUnlockCard(false);
        cardUnblock.setBlocked(false);

        CARD_DAO.updateById(cardUnblock);
        request.setAttribute("cards", CARD_DAO.getUnlockCards());

        SaveEvent.execute(cardUnblock.getAccount(), 0, 0, CardOperation.UNBLOCK);
        return PAGE_ADMIN;
    }
}
