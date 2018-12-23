package com.svladiko.dal.impl;

import com.svladiko.dal.IHistoryDao;
import com.svladiko.dal.utils.CommonMessageContent;
import com.svladiko.model.Card;
import com.svladiko.model.History;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * For work with table database histiry.
 *
 * @author Vladislav Serhiychuk
 *         Created on 5/4/2015.
 */
public class HistoryDao implements IHistoryDao, CommonMessageContent {
    private static final String ID = "id";
    private static final String DATE = "date_operation";
    private static final String OPERATIONS = "operations";
    private static final String ACCOUNT_SENDER = "account_sender";
    private static final String AMOUNT_RETELLING = "amount_retelling";
    private static final String ACCOUNT_RETELLING = "account_retelling";

    private static final String GET_BY_ACCOUNT_SENDER = "select * from history where account_sender = ?";
    private static final String GET_ALL = "SELECT * FROM history";
    private static final String REMOVE_ALL = "DELETE FROM history";

    private static final String SAVE =
            "INSERT INTO history(operations, date_operation, account_sender,amount_retelling, account_retelling) VALUES (?,?,?,?,?)";

    private static final String SAVE_IDENTIFIED =
            "INSERT INTO history(id, operations, date_operation, account_sender, amount_retelling, account_retelling) VALUES (?,?,?,?,?,?) " +
                    "ON DUPLICATE KEY UPDATE operations = ?, date_operation =?, account_sender =?, amount_retelling = ?, account_retelling = ?";

    private final Logger LOG = Logger.getLogger(HistoryDao.class);

    @Override
    public List<History> getByAccountSender(Card card) {
        List<History> histories = new ArrayList<>();
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_BY_ACCOUNT_SENDER)) {
            ps.setInt(1, card.getAccount());
            histories = getHistories(ps.executeQuery());
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_BY_ACCOUNT + card.getAccount(), e);
        }
        LOG.info(MESSAGE_GET_BY_ACCOUNT + card.getAccount());
        return histories;
    }

    private List<History> getHistories(ResultSet rs) throws SQLException {
        History history;
        List<History> histories = new ArrayList<>();
        while (rs.next()) {
            history = new History();
            history.setId(rs.getInt(ID));
            history.setAccountRetelling(rs.getInt(ACCOUNT_RETELLING));
            history.setAmountRetelling(rs.getInt(AMOUNT_RETELLING));
            history.setAccountSender(rs.getInt(ACCOUNT_SENDER));
            history.setOperations(rs.getString(OPERATIONS));
            history.setDate(rs.getDate(DATE));
            histories.add(history);
        }
        return histories;
    }

    @Override
    public List<History> getAll() {
        List<History> histories = new ArrayList<>();
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL)) {
            histories = getHistories(ps.executeQuery());
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_ALL + histories.size(), e);
        }
        LOG.info(MESSAGE_GET_ALL + histories.size());
        return histories;
    }

    @Override
    public void removeAll() {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(REMOVE_ALL)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(MESSAGE_REMOVE_ALL, e);
        }
        LOG.info(MESSAGE_REMOVE_ALL);
    }

    @Override
    public void create(History history) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE)) {
            ps.setString(1, history.getOperations());
            ps.setDate(2, new java.sql.Date(history.getDate().getTime()));
            ps.setInt(3, history.getAccountSender());
            ps.setInt(4, history.getAmountRetelling());
            ps.setInt(5, history.getAccountRetelling());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(MESSAGE_CREATE, e);
        }
        LOG.info(MESSAGE_CREATE);
    }

    /**
     * Save new operations with card in table history.
     * <p>
     * If id buzy method updateById data this id on data param
     * </p>
     *
     * @param history
     * @throws SQLException
     */
    @Override
    public void saveIdentified(History history) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_IDENTIFIED)) {
            ps.setInt(1, history.getId());
            ps.setString(2, history.getOperations());
            ps.setDate(3, new java.sql.Date(history.getDate().getTime()));
            ps.setInt(4, history.getAccountSender());
            ps.setInt(5, history.getAmountRetelling());
            ps.setInt(6, history.getAccountRetelling());

            ps.setString(7, history.getOperations());
            ps.setDate(8, new java.sql.Date(history.getDate().getTime()));
            ps.setInt(9, history.getAccountSender());
            ps.setInt(10, history.getAmountRetelling());
            ps.setInt(11, history.getAccountRetelling());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(MESSAGE_CREATE_IDENTIFIED + history.getId(), e);
        }
        LOG.info(MESSAGE_CREATE_IDENTIFIED + history.getId());
    }
}
