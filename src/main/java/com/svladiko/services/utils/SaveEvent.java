package com.svladiko.services.utils;

import com.svladiko.dal.IHistoryDao;
import com.svladiko.dal.factory.DAOFactory;
import com.svladiko.model.History;
import com.svladiko.services.command.CommonConstants;

import java.util.Date;

/**
 * Save history use card.
 *
 * @author Vladislav Serhiychuk
 */
public class SaveEvent implements CommonConstants {
    private static final IHistoryDao HISTORY_DAO = DAOFactory.getInstance().createHistoryDao();

    public static void execute( int accountSender, int amountRecipient, int accountRecipient, String operations) {
        HISTORY_DAO.create(new History(accountSender, amountRecipient, accountRecipient, operations, new Date()));
    }
}
