package com.svladiko.dal.factory;

import com.svladiko.dal.impl.CardDao;
import com.svladiko.dal.impl.ClientDao;
import com.svladiko.dal.impl.HistoryDao;

/**
 * @author Vladislav Serhiychuk
 */

public abstract class DAOFactory {

    public abstract CardDao createCardDao();

    public abstract ClientDao createClientDao();

    public abstract HistoryDao createHistoryDao();

    public static DAOFactory getInstance() {
        return new JdbcDAOFactory();
    }
}
