package com.svladiko.dal;//package com.svladiko.dal;

import com.svladiko.dal.impl.CardDao;
import com.svladiko.dal.impl.ClientDao;
import com.svladiko.model.Card;
import com.svladiko.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test on:
 * <p/>
 * create()            +
 * getAll()            +
 * getById()           +
 * removeAll()         +
 * updateById()        +
 * removeById()        +
 * getByLogin()        +
 * createIdentified()    +
 */
public class ClientDaoTest {
    private static final IClientDao CLIENT_DAO = new ClientDao();
    private static final ICardDao CARD_DAO = new CardDao();
    private List<Client> databaseClientsData = new ArrayList<>();
    private List<Card> databaseCardsData = new ArrayList<>();
    private Client actualClientOne;
    private Client actualClientTwo;

    private final int ID = 3;
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String NAME = "name";
    private final String LAST_NAME = "last_name";
    private final String PASSPORT = "passport";
    private final String PHONE = "phone";

    @Before
    public void setUp() {
        databaseClientsData = CLIENT_DAO.getAll();
        databaseCardsData = CARD_DAO.getAll();

        CLIENT_DAO.removeAll();
        CARD_DAO.removeAll();

        actualClientOne = new Client(1, "login_1", "pass_1", "name_1", "last_name_1", "passport_1", "phone_1");
        actualClientTwo = new Client(2, "login_2", "pass_2", "name_2", "last_name_2", "passport_2", "phone_2");

        CLIENT_DAO.createIdentified(actualClientOne);
        CLIENT_DAO.createIdentified(actualClientTwo);

        CARD_DAO.createIdentified(new Card(1, 1, 100, false, actualClientOne));
        CARD_DAO.createIdentified(new Card(2, 2, 200, false, actualClientTwo));
        CARD_DAO.createIdentified(new Card(3, 3, 300, true, actualClientTwo));

        actualClientOne.setCards(CARD_DAO.getCardsById(actualClientOne));
        actualClientTwo.setCards(CARD_DAO.getCardsById(actualClientTwo));
    }

    @After
    public void tearDown() {
        CLIENT_DAO.removeAll();
        CARD_DAO.removeAll();

        for (Client client : databaseClientsData) {
            CLIENT_DAO.createIdentified(client);
        }

        for (Card card : databaseCardsData) {
            CARD_DAO.createIdentified(card);
        }
    }

    @Test
    public void getById() {
        comparisonAllField(CLIENT_DAO.getById(actualClientOne), actualClientOne);
        comparisonAllField(CLIENT_DAO.getById(actualClientTwo), actualClientTwo);
    }

    @Test
    public void getByLogin() {
        comparisonAllField(CLIENT_DAO.getByLogin(actualClientOne), actualClientOne);
        comparisonAllField(CLIENT_DAO.getByLogin(actualClientTwo), actualClientTwo);
    }

    @Test
    public void getAll() {
        assertEquals(2, CLIENT_DAO.getAll().size());
    }

    @Test
    public void removeById() {
        assertEquals(2, CLIENT_DAO.getAll().size());
        CLIENT_DAO.removeById(actualClientOne);
        assertEquals(1, CLIENT_DAO.getAll().size());
    }

    @Test
    public void removeAll() {
        assertEquals(2, CLIENT_DAO.getAll().size());
        CLIENT_DAO.removeAll();
        assertEquals(0, CLIENT_DAO.getAll().size());
    }

    @Test
    public void updateById() {
        Client actual = CLIENT_DAO.getById(actualClientOne);
        modifyField(actual);
        comparisonAllField(CLIENT_DAO.updateById(actual), actual);
    }

    @Test
    public void save() {
        Client actual = new Client();
        modifyField(actual);
        comparison(CLIENT_DAO.create(actual), actual);
    }

    @Test
    public void saveIdentified() {
        Client actual = CLIENT_DAO.getById(actualClientOne);
        modifyField(actual);
        comparison(CLIENT_DAO.createIdentified(actual), actual);
    }

    private void comparison(Client expect, Client actual) {
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getPhone(), actual.getPhone());
        assertEquals(expect.getLogin(), actual.getLogin());
        assertEquals(expect.getPassword(), actual.getPassword());
        assertEquals(expect.getLastName(), actual.getLastName());
        assertEquals(expect.getPassport(), actual.getPassport());
        assertNotNull(expect);
    }

    private void comparisonAllField(Client expect, Client actual) {
        comparison(expect, actual);
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getCards().size(), actual.getCards().size());
    }

    private void modifyField(Client client) {
        client.setLogin(LOGIN);
        client.setPassword(PASSWORD);
        client.setName(NAME);
        client.setLastName(LAST_NAME);
        client.setPassport(PASSPORT);
        client.setPhone(PHONE);
    }
}
