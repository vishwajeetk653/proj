package com.svladiko.dal.impl;

import com.svladiko.dal.ICardDao;
import com.svladiko.dal.utils.CommonMessageContent;
import com.svladiko.model.Card;
import com.svladiko.model.Client;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for working with table card.
 *
 * @author Vladislav Serghiychuk
 *         Created on 4/17/2015.
 */
public class CardDao implements ICardDao, CommonMessageContent {
    private final Logger LOG = Logger.getLogger(CardDao.class);
    private ResultSet rs;

    private static final String ID = "id";
    private static final String AMOUNT = "amount";
    private static final String BLOCKED = "blocked";
    private static final String ID_CLIENT = "id_client";
    private static final String ACCOUNT = "account";
    private static final String UNLOCK_CARD = "unlock_card";

    private static final String GET_ALL = "select * from card";
    private static final String GET_BY_ID = "select * from card where id = ?";
    private static final String GET_BY_ACCOUNT = "select * from card where account = ?";
    private static final String GET_ALL_UNLOCK_CARD = "select * from card where unlock_card = 1";
    private static final String GET_CARDS_BY_CLIENT_ID = "select * from card where id_client = ?";

    private static final String REMOVE_ALL = "delete from card";
    private static final String REMOVE_BY_ID = "delete from card where id = ?";

    private static final String SAVE = "insert into card(amount, blocked, id_client, account, unlock_card) values (?,?,?,?,?)";

    private static final String UPDATE_BY_ID = "update card set account= ?, amount= ?, blocked= ?, id_client = ?, unlock_card = ?  where id = ?";

    private static final String SAVE_IDENTIFIED = "insert into card(id, amount, blocked, id_client, account, unlock_card) values (?,?,?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE amount = ?, blocked= ?, id_client = ?, account = ?, unlock_card = ?";

    @Override
    public Card getById(int id) {
        Card newCard = null;
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ID)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                newCard = new Card();
                newCard.setId(rs.getInt(ID));
                newCard.setAccount(rs.getInt(ACCOUNT));
                newCard.setAmount(rs.getInt(AMOUNT));
                newCard.setBlocked(convertIntInBoolean(rs.getInt(BLOCKED)));
                newCard.setClient(new Client(rs.getInt(ID_CLIENT)));
                newCard.setUnlockCard(convertIntInBoolean(rs.getInt(UNLOCK_CARD)));
            }

        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_BY_ID + id, e);
            e.printStackTrace();
        }
        LOG.info(MESSAGE_GET_BY_ID + id);
        return newCard;
    }

    @Override
    public Card getById(Card card) {
        return getById(card.getId());
    }

    @Override
    public Card getByAccount(Card card) {
        return getByAccount(card.getAccount());
    }

    @Override
    public Card getByAccount(int account) {
        Card newCard = null;
        try (Connection con = worker.getConnection();) {
            newCard = getCardByAccountCommon(con, account);
        } catch (SQLException e) {}
        return newCard;
    }

    public List<Card> getAll() {
        List<Card> cards = null;
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL)) {
            cards = getCardsInDBCommonLogic(ps.executeQuery());
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_ALL + cards.size(), e);
        }
        LOG.info(MESSAGE_GET_ALL + cards.size());
        return cards;
    }

    public List<Card> getCardsById(Client client) {
        List<Card> cards = new ArrayList<>();
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(GET_CARDS_BY_CLIENT_ID)) {
            ps.setInt(1, client.getId());
            cards = getCardsInDBCommonLogic(ps.executeQuery());
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_CARDS_BY_CLIENT_ID + client.getId(), e);
        }

        LOG.info(MESSAGE_GET_CARDS_BY_CLIENT_ID + cards.size());
        return cards;
    }

    @Override
    public void removeById(Card card) {
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(REMOVE_BY_ID)) {
            ps.setInt(1, card.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(MESSAGE_REMOVE_BY_ID + card.getId(), e);
        }
        LOG.info(MESSAGE_REMOVE_BY_ID + card.getId());
    }

    @Override
    public void removeAll() {
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(REMOVE_ALL)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(MESSAGE_REMOVE_ALL, e);
        }
        LOG.info(MESSAGE_REMOVE_ALL);
    }

    @Override
    public Card updateById(Card card) {
        try (Connection con = worker.getConnection()) {
            updateDataDB(con, card);
        } catch (SQLException e) {
            LOG.error(MESSAGE_UPDATE_BY_ID + card.getId(), e);
        }
        LOG.info(MESSAGE_UPDATE_BY_ID + card.getId());
        return getById(card);
    }

    @Override
    public Card create(Card card) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE)) {
            ps.setInt(1, card.getAmount());
            ps.setInt(2, convertBooleanInInt(card.getBlocked()));
            ps.setInt(3, card.getClient().getId());
            ps.setInt(4, card.getAccount());
            ps.setInt(5, convertBooleanInInt(card.getUnlockCard()));
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(MESSAGE_CREATE, e);
        }
        LOG.info(MESSAGE_CREATE);
        return getByAccount(card);
    }

    @Override
    public Card createIdentified(Card card) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_IDENTIFIED)) {
            ps.setInt(1, card.getId());
            ps.setInt(2, card.getAmount());
            ps.setInt(3, convertBooleanInInt(card.getBlocked()));
            ps.setInt(4, card.getClient().getId());
            ps.setInt(5, card.getAccount());
            ps.setInt(6, convertBooleanInInt(card.getUnlockCard()));
            ps.setInt(7, card.getAmount());
            ps.setInt(8, convertBooleanInInt(card.getBlocked()));
            ps.setInt(9, card.getClient().getId());
            ps.setInt(10, card.getAccount());
            ps.setInt(11, convertBooleanInInt(card.getUnlockCard()));
            ps.execute();

        } catch (SQLException e) {
            LOG.error(MESSAGE_CREATE_IDENTIFIED + card.getId(), e);
        }
        LOG.info(MESSAGE_CREATE_IDENTIFIED + card.getId());
        return getByAccount(card);
    }

    public List<Card> getUnlockCards() {
        Card card;
        List<Card> cards = new ArrayList<>();
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_UNLOCK_CARD)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                card = new Card();
                card.setId(rs.getInt(ID));
                card.setAccount(rs.getInt(ACCOUNT));
                card.setAmount(rs.getInt(AMOUNT));
                card.setBlocked(convertIntInBoolean(rs.getInt(BLOCKED)));
                card.setClient(new Client(rs.getInt(ID_CLIENT)));
                card.setUnlockCard(convertIntInBoolean(rs.getInt(UNLOCK_CARD)));
                cards.add(card);
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_UNLOCK_CARDS + cards.size(), e);
        }
        LOG.info(MESSAGE_GET_UNLOCK_CARDS + cards.size());
        return cards;
    }

    @Override
    public String paymentTransaction(int accountSender, int accountRecipient, int amountSent) {
        try (Connection con = worker.getConnection()) {
            con.setAutoCommit(false);

            Card cardSender = getCardByAccountCommon(con, accountSender);

            if (assertNull(cardSender, con, ERROR_CARD_SENDER_ACCOUNT)) {
                return ERROR_CARD_SENDER_ACCOUNT;
            }

            if (cardSender.getAmount() < amountSent) {
                LOG.error(ERROR_CARD_SENDER_AMOUNT);
                con.rollback();
                return ERROR_CARD_SENDER_AMOUNT;
            }

            Card cardRecipient = getCardByAccountCommon(con, accountRecipient);

            if (assertNull(cardRecipient, con, ERROR_CARD_RECIPIENT_ACCOUNT)) {
                return ERROR_CARD_RECIPIENT_ACCOUNT;
            }

            cardRecipient.setAmount(cardRecipient.getAmount() + amountSent);
            updateDataDB(con, cardRecipient);

            cardSender.setAmount(cardSender.getAmount() - amountSent);
            updateDataDB(con, cardSender);

            con.commit();
        } catch (SQLException e) {
            LOG.error(FAILED_TRANSACTION, e);
        }
        return FAILED_TRANSACTION;
    }

    private boolean assertNull(Card card, Connection con, String message) throws SQLException {
        if (card == null) {
            LOG.error(message);
            con.rollback();
            return true;
        }
        return false;
    }

    private Card getCardByAccountCommon(Connection con, int account) {
        Card card = null;
        try (PreparedStatement ps = con.prepareStatement(GET_BY_ACCOUNT)) {
            ps.setInt(1, account);
            rs = ps.executeQuery();

            while (rs.next()) {
                card = new Card();
                card.setId(rs.getInt(ID));
                card.setAccount(rs.getInt(ACCOUNT));
                card.setAmount(rs.getInt(AMOUNT));
                card.setBlocked(convertIntInBoolean(rs.getInt(BLOCKED)));
                card.setClient(new Client(rs.getInt(ID_CLIENT)));
                card.setUnlockCard(convertIntInBoolean(rs.getInt(UNLOCK_CARD)));
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_GET_BY_ACCOUNT + account, e);
            e.printStackTrace();
        }
        LOG.info(MESSAGE_GET_BY_ACCOUNT + card.getAccount());
        return card;
    }

    private List<Card> getCardsInDBCommonLogic(ResultSet resultSet) throws SQLException {
        List<Card> cards = new ArrayList<>();
        while (resultSet.next()) {
            Card card = new Card();
            card.setId(resultSet.getInt(ID));
            card.setAccount(resultSet.getInt(ACCOUNT));
            card.setAmount(resultSet.getInt(AMOUNT));
            card.setBlocked(convertIntInBoolean(resultSet.getInt(BLOCKED)));
            card.setClient(new Client(resultSet.getInt(ID_CLIENT)));
            card.setUnlockCard(convertIntInBoolean(resultSet.getInt(UNLOCK_CARD)));
            cards.add(card);
        }
        return cards;
    }

    /**
     * It combines logic card updateById
     *
     * @param con
     * @param card
     */
    private void updateDataDB(Connection con, Card card) {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_BY_ID)) {
            ps.setInt(6, card.getId());
            ps.setInt(1, card.getAccount());
            ps.setInt(2, card.getAmount());
            ps.setInt(3, convertBooleanInInt(card.getBlocked()));
            ps.setInt(4, card.getClient().getId());
            ps.setInt(5, convertBooleanInInt(card.getUnlockCard()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bool, Boolean: These types are synonyms for TINYINT(1).
     * A value of zero is considered false. Non-zero values are considered true
     */
    private boolean convertIntInBoolean(int dataBlocked) {
        if (dataBlocked == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Convert boolean to int for database table card.
     *
     * @param block
     * @return int number.
     */
    private int convertBooleanInInt(boolean block) {
        if (block) {
            return 1;
        } else {
            return 0;
        }
    }
}
