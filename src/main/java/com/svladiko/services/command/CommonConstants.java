package com.svladiko.services.command;

import com.svladiko.dal.ICardDao;
import com.svladiko.dal.IClientDao;
import com.svladiko.dal.IHistoryDao;
import com.svladiko.dal.factory.DAOFactory;
import static com.svladiko.services.utils.Generate.getUrl;


/**
 *
 */
public interface CommonConstants {
    String NAME = "name";
    String PHONE = "phone";
    String LOGIN = "login";
    String PASSWORD = "password";
    String PASSPORT = "passport";
    String LAST_NAME = "lastName";

    String ACCOUNT = "account";
    String AMOUNT_SENT = "amountSent";
    String MESSAGE = "errorMessage";
    String ACCOUNT_SENDER = "accountSender";
    String AMOUNT_RECIPIENT = "amountRecipient";
    String ACCOUNT_RECIPIENT = "accountRecipient";

    String PAGE_ERROR = getUrl("error");
    String PAGE_LOGIN = getUrl("login");
    String PAGE_ADMIN = getUrl("adminPage");
    String PAGE_PAYMENT = getUrl("payment");
    String PAGE_REGISTER = getUrl("register");
    String PAGE_MENU_CARD = getUrl("menuCard");
    String MENU_CARDS_PAGE = getUrl("menuCards");
    String PAGE_HISTORY_CARD = getUrl("history");
    String PAGE_ERROR_REGISTER = getUrl("errorPageFromGuest");
    String PAGE_REPLENISHMENT = getUrl("replenishment");

    String ERROR_CARD_BLOCKED = "This card is blocked";
    String ERROR_CARD_NOT_BLOCKED = "The card is not locked";
    String DEPOSIT_ERROR_MESSAGE = "Enter number more 0";
    String ERROR_NOT_DATA = "You not enter data by operations";
    String ERROR_LOGIN = "Incorrect login or password <a href='http://localhost:8080/'> Return to login page </a>";
    String ERROR_REGISTER = "If you have filled in all the fields and see this message, change the login. This busy <form method='post' action='/clientController'><input type='submit' name='command' value='Register page'></form>";
    String ERROR_CONTROLLER = "Please log in again";

    ICardDao CARD_DAO = DAOFactory.getInstance().createCardDao();
    IClientDao CLIENT_DAO = DAOFactory.getInstance().createClientDao();
    IHistoryDao HISTORY_DAO = DAOFactory.getInstance().createHistoryDao();
}
