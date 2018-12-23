package com.svladiko.dal;//package com.svladiko.dal;

import com.svladiko.dal.impl.CardDao;
import com.svladiko.dal.impl.ClientDao;
import com.svladiko.dal.impl.HistoryDao;
import com.svladiko.model.Card;
import com.svladiko.model.Client;
import com.svladiko.model.History;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * getByAccountSender()   +
 * getAll()               +
 * removeAll()            +
 * create()               +
 * createIdentified()       +
 *
 * Created by Vladislav on 5/4/2015.
 */
public class HistoryDaoTest {
    private final IHistoryDao HISTORY_DAO = new HistoryDao();
    private final ICardDao CARD_DAO = new CardDao();
    private final IClientDao CLIENT_DAO = new ClientDao();

    private final int AMOUNT = 1000;
    private final int AMOUNT_RECIPENT = 100;
    private final boolean BLOCKED = false;
    private final boolean UNLOCK_CARD = false;

    private Card actualCardOne;
    private Card actualCardTwo;
    private Client actualClient;

    private History actualHistoryOne;
    private History actualHistoryTwo;

    private List<Card> databaseCardsData;
    private List<Client> databaseClientsData;
    private List<History> databaseHistoryData;

    @Before
    public void setUp() {
        databaseCardsData = CARD_DAO.getAll();
        databaseClientsData = CLIENT_DAO.getAll();
        databaseHistoryData = HISTORY_DAO.getAll();

        CLIENT_DAO.removeAll();
        CARD_DAO.removeAll();
        HISTORY_DAO.removeAll();

        actualClient = new Client(1, "login_1", "pass_1", "name_1", "last_name_1", "passport_1", "phone_1");
        CLIENT_DAO.createIdentified(actualClient);


        actualCardOne = new Card(1, 1, AMOUNT, BLOCKED, actualClient, UNLOCK_CARD);
        actualCardTwo = new Card(3, 3, AMOUNT, BLOCKED, actualClient, true);

        CARD_DAO.createIdentified(actualCardOne);
        CARD_DAO.createIdentified(actualCardTwo);

        actualHistoryOne = new History(1, actualCardOne.getAccount(), AMOUNT_RECIPENT, actualCardTwo.getAccount(), "deposit", new Date(2014, 10, 4));
        actualHistoryTwo = new History(2, actualCardTwo.getAccount(), AMOUNT_RECIPENT, actualCardOne.getAccount(), "deposit", new Date(2015, 10, 4));

        HISTORY_DAO.saveIdentified(actualHistoryOne);
        HISTORY_DAO.saveIdentified(actualHistoryTwo);
    }

    @After
    public void tearDown() {
        CLIENT_DAO.removeAll();
        CARD_DAO.removeAll();
        HISTORY_DAO.removeAll();

        for (Client client : databaseClientsData) {
            CLIENT_DAO.createIdentified(client);
        }

        for (Card card : databaseCardsData) {
            CARD_DAO.createIdentified(card);
        }

        for (History history : databaseHistoryData) {
            HISTORY_DAO.saveIdentified(history);
        }
    }

    @Test
    public void getByAccountSender() {
        List<History> historyList = HISTORY_DAO.getByAccountSender(actualCardOne);
        assertEquals(historyList.size(), 1);
    }

    @Test
    public void getAll() {
        assertEquals(HISTORY_DAO.getAll().size(), 2);
    }

    @Test
    public void removeAll() {
        assertEquals(HISTORY_DAO.getAll().size(), 2);
        HISTORY_DAO.removeAll();
        assertEquals(HISTORY_DAO.getAll().size(), 0);
    }

    @Test
    public void save() {
        assertEquals(HISTORY_DAO.getAll().size(), 2);
        HISTORY_DAO.create(new History(actualCardOne.getAccount(), AMOUNT_RECIPENT, actualCardTwo.getAccount(), "deposit", new Date(2015, 10, 4)));
        assertEquals(HISTORY_DAO.getAll().size(), 3);
    }

    @Test
    public void saveIdentified() {
        assertEquals(HISTORY_DAO.getAll().size(), 2);
        HISTORY_DAO.saveIdentified(new History(4, actualCardOne.getAccount(), AMOUNT_RECIPENT, actualCardTwo.getAccount(), "deposit", new Date(2015, 10, 4)));
        assertEquals(HISTORY_DAO.getAll().size(), 3);
    }
}
