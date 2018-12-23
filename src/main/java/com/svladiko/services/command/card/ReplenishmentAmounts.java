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
import java.io.IOException;

/**
 * Deposit user card from account.
 *
 * @author Vladislav Sergiihcyck
 */
public class ReplenishmentAmounts implements Command, CommonConstants {
    private final Logger LOG = Logger.getLogger(ReplenishmentAmounts.class);

    /**
     * All logic for check data by deposit command in the static method
     * CheckDataByDeposit.execute(req, response).
     * If he return true: return on error.jsp
     * If he return false: return on menuCard.jsp
     *
     * @param request
     * @param response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int amountSent = Integer.parseInt(request.getParameter(AMOUNT_SENT));
        int accountRecipient = Integer.parseInt(request.getParameter(ACCOUNT_RECIPIENT));

        Card cardRecipient = CARD_DAO.getByAccount(accountRecipient);
        cardRecipient.setAmount(cardRecipient.getAmount() + amountSent);
        CARD_DAO.updateById(cardRecipient);

        SaveEvent.execute(accountRecipient, amountSent, accountRecipient, CardOperation.REPLENISHMENT_AMOUNT);
        LOG.info("amountSent = " + amountSent + "accountRecipient = " + accountRecipient);

        request.setAttribute("cards", CARD_DAO.getUnlockCards());
        return PAGE_ADMIN;
    }
}
