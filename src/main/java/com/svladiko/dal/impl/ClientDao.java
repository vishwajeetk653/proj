package com.svladiko.dal.impl;

import com.svladiko.dal.ICardDao;
import com.svladiko.dal.IClientDao;
import com.svladiko.dal.utils.CommonMessageContent;
import com.svladiko.dal.factory.DAOFactory;
import com.svladiko.model.Card;
import com.svladiko.model.Client;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

/**
 * The implementation of queries to the database table client.
 *
 * @author Vladislav Serhiychuk
 *         Created on 4/17/2015.
 */
public class ClientDao implements IClientDao, CommonMessageContent {

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PASSPORT = "passport";
    private static final String PHONE = "phone";

    private static final String GET_BY_ID = "select * from client where id = ?";
    private static final String GET_BY_LOGIN = "select * from client where login = ?";
    private static final String GET_ALL = "select * from client";
    private static final String REMOVE_BY_ID = "delete from client where id = ?";
    private static final String REMOVE_ALL = "delete from client";

    private final String UPDATE_BY_ID =
            "update client set login = ?, password = ?, first_name = ?, last_name = ?, passport = ?, phone =?  where id = ?;";

    private final String SAVE =
            "insert into client(login , password , first_name, last_name , passport , phone) values (?,?,?,?,?,?)";

    private final String SAVE_IDENTIFIED =
            "insert into client(id, login , password , first_name, last_name , passport , phone) values (?,?,?,?,?,?,?) " +
                    "on duplicate key update login = ?, password = ?, first_name = ?, last_name =? , passport = ?, phone= ?";

    private final Logger LOGGER = Logger.getLogger(ClientDao.class);
    private ResultSet rs;


    @Override
    public Client getById(Client client) {
       return getById(client.getId());
    }

    @Override
    public Client getById(int id) {
        Client newClient = null;
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ID)) {
            ps.setInt(1, id);
            newClient = getClient(ps.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_GET_BY_ID + id, e);
        }
        LOGGER.info(MESSAGE_GET_BY_ID + id);
        return newClient;
    }

    @Override
    public Client getByLogin(String login) {
        Client newClient = null;
        try (Connection con = worker.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_LOGIN)) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            newClient = getClient(rs);
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_GET_BY_LOGIN + login, e);
        }
        LOGGER.info(MESSAGE_GET_BY_LOGIN + login);
        return newClient;

    }


    @Override
    public Client getByLogin(Client client) {
        return getByLogin(client.getLogin());
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        Client client;
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                client = new Client();
                client.setId(rs.getInt(ID));
                client.setLogin(rs.getString(LOGIN));
                client.setPassword(rs.getString(PASSWORD));
                client.setName(rs.getString(NAME));
                client.setLastName(rs.getString(LAST_NAME));
                client.setPassport(rs.getString(PASSPORT));
                client.setPhone(rs.getString(PHONE));
                // Search of customer cards in the card table and fields
                client.setCards(searchClientCards(client));
                clients.add(client);
            }
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_GET_ALL + clients.size(), e);
        }
        LOGGER.info(MESSAGE_GET_ALL + clients.size());
        return clients;
    }

    @Override
    public void removeById(Client client) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(REMOVE_BY_ID)) {
            ps.setInt(1, client.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_REMOVE_BY_ID + client.getId(), e);
        }
        LOGGER.info(MESSAGE_REMOVE_BY_ID + client.getId());
    }

    @Override
    public void removeAll() {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(REMOVE_ALL)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(MESSAGE_REMOVE_ALL, e);
        }
        LOGGER.info(MESSAGE_REMOVE_ALL);
    }

    @Override
    public Client updateById(Client client) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_BY_ID)) {
            ps.setInt(7, client.getId());
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getName());
            ps.setString(4, client.getLastName());
            ps.setString(5, client.getPassport());
            ps.setString(6, client.getPhone());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(MESSAGE_UPDATE_BY_ID + client.getId(), e);
        }
        LOGGER.info(MESSAGE_UPDATE_BY_ID + client.getId());
        return getById(client);
    }

    @Override
    public Client createIdentified(Client client) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_IDENTIFIED)) {

            ps.setInt(1, client.getId());
            ps.setString(2, client.getLogin());
            ps.setString(3, client.getPassword());
            ps.setString(4, client.getName());
            ps.setString(5, client.getLastName());
            ps.setString(6, client.getPassport());
            ps.setString(7, client.getPhone());
            // Update if on id there data
            ps.setString(8, client.getLogin());
            ps.setString(9, client.getPassword());
            ps.setString(10, client.getName());
            ps.setString(11, client.getLastName());
            ps.setString(12, client.getPassport());
            ps.setString(13, client.getPhone());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(MESSAGE_CREATE_IDENTIFIED + client.getId(), e);
        }
        LOGGER.info(MESSAGE_CREATE_IDENTIFIED + client.getId());
        return getByLogin(client);
    }

    @Override
    public Client create(Client client) {
        try (Connection con = worker.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE)) {
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getName());
            ps.setString(4, client.getLastName());
            ps.setString(5, client.getPassport());
            ps.setString(6, client.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(MESSAGE_CREATE, e);
        }
        LOGGER.info(MESSAGE_CREATE);
        return getByLogin(client);
    }

    private List<Card> searchClientCards(Client client) {
        ICardDao CARD_DAO = DAOFactory.getInstance().createCardDao();
        if (client != null) {
            return CARD_DAO.getCardsById(client);
        }
        return null;
    }

    private Client getClient(ResultSet rs) throws SQLException {
        Client client = null;
        while (rs.next()) {
            client = new Client();
            client.setId(rs.getInt(ID));
            client.setLogin(rs.getString(LOGIN));
            client.setPassword(rs.getString(PASSWORD));
            client.setName(rs.getString(NAME));
            client.setLastName(rs.getString(LAST_NAME));
            client.setPassport(rs.getString(PASSPORT));
            client.setPhone(rs.getString(PHONE));
            // Search of customer cards in the card table and fields
            client.setCards(searchClientCards(client));
        }
        return client;
    }
}
