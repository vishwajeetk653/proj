package com.svladiko.dal;

import com.svladiko.connection.DBWorker;
import com.svladiko.model.Card;
import com.svladiko.model.History;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface by work with history table in MySQL.
 *
 * @author Vladislav Serhiychuk
 *         Created on 5/4/2015.
 */
public interface IHistoryDao {
    DBWorker worker = new DBWorker();
    /**
     * Search data about card by account in table history.
     *
     * @param card
     * @return information by card in List<History>.
     * @throws SQLException
     */
    List<History> getByAccountSender(Card card);

    /**
     * Return all data in table database history.
     *
     * @return information by cards in List<History>.
     * @throws SQLException
     */
    List<History> getAll();

    /**
     * Clean table history.
     *
     * @throws SQLException
     */
    void removeAll();

    /**
     * Save new operations with card in table history.
     * <p>
     * id history auto increment.
     * </p>
     *
     * @param history
     * @throws SQLException
     */
    void create(History history);

    /**
     * Save new operations with card in table history.
     * <p>
     * If id buzy method updateById data this id on data param
     * </p>
     *
     * @param history
     * @throws SQLException
     */
    void saveIdentified(History history);
}
