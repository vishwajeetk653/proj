package com.svladiko.dal;


import com.svladiko.connection.DBWorker;
import com.svladiko.model.Card;
import com.svladiko.model.Client;

import java.util.List;

/**
 * Interface for working with table card.
 *
 * @author Vladislav Serhiychuk
 */
public interface ICardDao {
    DBWorker worker = new DBWorker();

    /**
     * Search Card in the database on id.
     *
     * @param card
     * @return Card
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Card getById(Card card);

    Card getById(int id);


    /**
     * Search card in the database by its account.
     *
     * @return Card filled from database.
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Card getByAccount(Card card);


    Card getByAccount(int account);
    /**
     * Collects data on all the Card.
     *
     * @return
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    List<Card> getAll();

    /**
     * Remove Card in database on id.
     *
     * @param card
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    void removeById(Card card);

    /**
     * Remove all Card elements in database.
     *
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    void removeAll();

    /**
     * Update Card in database on id element.
     *
     * @param card
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Card updateById(Card card);

    /**
     * Save Card in database
     *
     * @param card
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Card create(Card card);

    /**
     * Save Card with the given id.
     * If the given id is busy all the data are changed on the parameter data.
     *
     * @param card
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Card createIdentified(Card card);

    /**
     * * Search card in the database by client id.
     * <p>
     * One client can have multiple cards.
     * </p>
     *
     * @param client
     * @return List<Card> one client.
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    List<Card> getCardsById(Client client);

    /**
     * Search cards requesting unblock.
     *
     * @return List<Card> where param UNLOCK_CARD_i = true.
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    List<Card> getUnlockCards();

    String paymentTransaction(int accountSender, int accountRecipient, int amountSent);
}
