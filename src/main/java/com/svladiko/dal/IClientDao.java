package com.svladiko.dal;

import com.svladiko.connection.DBWorker;
import com.svladiko.model.Client;

import java.util.List;

/**
 * Interface for working with table client.
 *
 * @author Vladislav Serhiychuk
 */
public interface IClientDao {
    DBWorker worker = new DBWorker();

    /**
     * Search Client in the database on id.
     *
     * @param client
     * @return Client
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Client getById(Client client);

    Client getById(int id);

    /**
     * Collects data on all the Client.
     *
     * @return
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    List<Client> getAll();

    /**
     * Remove Client in database on id.
     *
     * @param client
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    void removeById(Client client);

    /**
     * Remove all Client elements in database.
     *
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    void removeAll();

    /**
     * Update Client in database on id element.
     *
     * @param client
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Client updateById(Client client);

    /**
     * Save Client in database
     *
     * @param client
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Client create(Client client);

    /**
     * Save Client with the given id.
     * If the given id is busy all the data are changed on the parameter data.
     *
     * @param client
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Client createIdentified(Client client);

    /**
     * Search client in the database by its login.
     *
     * @param client
     * @return Client or null. If there is id in the database return Client. Null if not.
     * @throws java.sql.SQLException if there is no connection to the database.
     */
    Client getByLogin(Client client);

    Client getByLogin(String login);

}
