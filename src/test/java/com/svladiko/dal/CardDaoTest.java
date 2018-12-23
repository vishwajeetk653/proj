package com.svladiko.dal;//package com.svladiko.dal;

import com.svladiko.dal.impl.CardDao;
import com.svladiko.dal.impl.ClientDao;
import com.svladiko.model.Card;
import com.svladiko.model.Client;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * getById()            +
 * getByAccount()       +
 * getCardsById()       +
 * getAll()             +
 * removeById()         +
 * removeAll()          +
 * updateById()         +
 * create()             +
 * createIdentified()     +
 * getUnlockCards()     +
 *
 * paymentTransaction() -
 */
public class CardDaoTest {
    private final ICardDao CARD_DAO = new CardDao();
    private final IClientDao CLIENT_DAO = new ClientDao();
    private List<Card> databaseCardsData;
    private List<Client> databaseClientsData;

    private Card actualCardOne;
    private Card actualCardTwo;
    private Card actualCardThree;
    private Client actualClientOne;
    private Client actualClientTwo;

    private final int ID = 4;
    private final int ACCOUNT = 777;
    private final int AMOUNT = 777;
    private final boolean BLOCKED = false;
    private final boolean UNLOCK_CARD = false;

    @Before
    public void setUp() {
        databaseCardsData = CARD_DAO.getAll();
        databaseClientsData = CLIENT_DAO.getAll();

        CARD_DAO.removeAll();
        CLIENT_DAO.removeAll();


        CARD_DAO.getAll();

        actualClientOne = new Client(1, "login_1", "pass_1", "name_1", "last_name_1", "passport_1", "phone_1");
        actualClientTwo = new Client(2, "login_2", "pass_2", "name_2", "last_name_2", "passport_2", "phone_2");

        CLIENT_DAO.createIdentified(actualClientOne);
        CLIENT_DAO.createIdentified(actualClientTwo);

        actualCardOne = new Card(1, 1, AMOUNT, BLOCKED, actualClientOne, UNLOCK_CARD);
        actualCardTwo = new Card(2, 2, AMOUNT, BLOCKED, actualClientTwo, UNLOCK_CARD);
        actualCardThree = new Card(3, 3, AMOUNT, BLOCKED, actualClientOne, true);

        CARD_DAO.createIdentified(actualCardOne);
        CARD_DAO.getAll();
        CARD_DAO.createIdentified(actualCardTwo);
        CARD_DAO.getAll();
        CARD_DAO.createIdentified(actualCardThree);

        CARD_DAO.getAll();
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
        comparisonAllField(CARD_DAO.getById(actualCardOne), actualCardOne);
        comparisonAllField(CARD_DAO.getById(actualCardThree), actualCardThree);
    }

    @Test
    public void getByAccount() {
        comparisonAllField(CARD_DAO.getByAccount(actualCardOne), actualCardOne);
        comparisonAllField(CARD_DAO.getByAccount(actualCardTwo), actualCardTwo);
    }

    @Test
    public void getCardsById() {
        TestCase.assertEquals(CARD_DAO.getCardsById(actualClientOne).size(), CLIENT_DAO.getById(actualClientOne).getCards().size());
    }

    @Test
    public void getAll() {
        assertEquals(3, CARD_DAO.getAll().size());
    }

    @Test
    public void removeById() {
        assertEquals(3, CARD_DAO.getAll().size());
        CARD_DAO.removeById(actualCardOne);
        assertEquals(2, CARD_DAO.getAll().size());
    }

    @Test
    public void removeAll() {
        assertEquals(3, CARD_DAO.getAll().size());
        CARD_DAO.removeAll();
        assertEquals(0, CARD_DAO.getAll().size());
    }

    @Test
    public void updateById() {

        modifyCard(actualCardOne);
        comparisonAllField(CARD_DAO.updateById(actualCardOne), actualCardOne);
    }

    @Test
    public void save() {
        modifyCard(actualCardOne);
        comparison(CARD_DAO.create(actualCardOne), actualCardOne);
    }

    @Test
    public void saveIdentified() {
        modifyCard(actualCardOne);
        actualCardOne.setId(ID);
        comparison(CARD_DAO.createIdentified(actualCardOne), actualCardOne);
        assertEquals(4, CARD_DAO.getAll().size());
    }

    @Test
    public void getUnlockCards() {
        comparisonAllField(CARD_DAO.getUnlockCards().get(0), actualCardThree);
    }

    private void comparison(Card expect, Card actual) {
        assertEquals(expect.getAccount(), actual.getAccount());
        assertEquals(expect.getAmount(), actual.getAmount());
        assertEquals(expect.getBlocked(), actual.getBlocked());
        assertEquals(expect.getUnlockCard(), actual.getUnlockCard());
        assertEquals(expect.getClient().getId(), actual.getClient().getId());
    }

    private void comparisonAllField(Card expect, Card actual) {
        assertEquals(expect.getId(), actual.getId());
        comparison(expect, actual);
    }

    private void modifyCard(Card card) {
        card.setAccount(ACCOUNT);
        card.setAmount(AMOUNT);
        card.setBlocked(BLOCKED);
        card.setUnlockCard(UNLOCK_CARD);
    }

}
