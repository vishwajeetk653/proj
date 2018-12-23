package com.svladiko.dal.factory;

import com.svladiko.dal.impl.CardDao;
import com.svladiko.dal.impl.ClientDao;
import com.svladiko.dal.impl.HistoryDao;

/**
 * DAOFactory methods pattern.
 *
 * @author Vladislav Serhiychuk
 */
public class JdbcDAOFactory extends DAOFactory {
    public CardDao createCardDao() {
        return new CardDao();
    }

    public ClientDao createClientDao() {
        return new ClientDao();
    }

    public HistoryDao createHistoryDao() {
        return new HistoryDao();
    }
}
