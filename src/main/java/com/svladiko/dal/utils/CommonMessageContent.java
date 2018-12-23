package com.svladiko.dal.utils;

/**
 * Created by Влад on 07.10.2015.
 */
public interface CommonMessageContent {
    String ERROR_CARD_SENDER_ACCOUNT = "Error! Not card with this account sender";
    String ERROR_CARD_RECIPIENT_ACCOUNT = "Error! Not card with this account recipient";
    String ERROR_CARD_SENDER_AMOUNT = "Error! card sender little many";
    String ERROR_UPDATE_CARD = "Error update card";
    String FAILED_TRANSACTION = "Failed transaction";

    String MESSAGE_CREATE = "Create.";
    String MESSAGE_GET_ALL = "Get all. Size = ";
    String MESSAGE_GET_BY_ID = "Get by id. Id = ";
    String MESSAGE_REMOVE_ALL = "Remove all.";
    String MESSAGE_UPDATE_BY_ID = "Update by id. Id = ";
    String MESSAGE_REMOVE_BY_ID = "Remove by id. Id = ";
    String MESSAGE_GET_BY_LOGIN = "Get by login. Login = ";
    String MESSAGE_GET_BY_ACCOUNT = "Get by account. Account = ";
    String MESSAGE_CREATE_IDENTIFIED = "Create identified. Id = ";
    String MESSAGE_GET_UNLOCK_CARDS = "Get unlock cards. Size = ";
    String MESSAGE_GET_CARDS_BY_CLIENT_ID = "Get cards by client id. Id = ";

//            * getUnlockCards()    +
}
