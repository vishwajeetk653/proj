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
 * Implementation of payment
 *
 * @author Vladislav Serhiychuk
 */
public class Payment implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(Payment.class);
    private int amountSent, accountRecipient;
    private Card cardSender;
    private String amount, account;

    /**
     * All logic for check data by payment command in the static method
     * CheckDataByPayment.execute(req, response).
     * If he return true:  return on menuCard.jsp.
     * If he return false: return on error.jsp.
     *
     * @param request
     * @param response
     * @return PAGE_MENU_CARD or PAGE_ERROR
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            amount = request.getParameter(AMOUNT_SENT);
            account = request.getParameter(ACCOUNT_RECIPIENT);

            amountSent = Integer.parseInt(request.getParameter(AMOUNT_SENT));
            accountRecipient = Integer.parseInt(request.getParameter(ACCOUNT_RECIPIENT));

            cardSender = (Card) session.getAttribute("card");

            if ((CARD_DAO.getByAccount(accountRecipient) == null) || (amountSent > cardSender.getAmount()) ||
                    (accountRecipient == cardSender.getAccount())) throw new NumberFormatException();

        } catch (NumberFormatException e) {
            LOG.error("Account = " + account + ", amount = " + amount, e);
            request.setAttribute(MESSAGE, ERROR_NOT_DATA);
            return PAGE_ERROR;
        }

        SaveEvent.execute(cardSender.getAccount(), amountSent, accountRecipient, CardOperation.REPLENISHMENT_AMOUNT);
        CARD_DAO.paymentTransaction(cardSender.getAccount(), accountRecipient, amountSent);
        session.setAttribute("card", CARD_DAO.getByAccount(cardSender));

        return PAGE_MENU_CARD;
    }
}

