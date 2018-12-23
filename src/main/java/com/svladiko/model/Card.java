package com.svladiko.model;

/**
 * This is POJO.
 * <p>
 * In the class there may be fields, constructors,
 * get, set method and @Override toString method
 * </p>
 *
 * @author Vladislav Serhiychuk
 */
public class Card {
    private int id;
    private int account;
    private int amount;
    private boolean blocked;
    private Client client;
    private boolean unlockCard;

    public Card() {
    }

    public Card(int id, int account, int amount, boolean blocked, Client client) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.blocked = blocked;
        this.client = client;
    }

    public Card(int id, int account, int amount, boolean blocked, Client client, boolean unlockCard) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.blocked = blocked;
        this.client = client;
        this.unlockCard = unlockCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean getUnlockCard() {
        return unlockCard;
    }

    public void setUnlockCard(boolean unlockCard) {
        this.unlockCard = unlockCard;
    }

    @Override
    public String toString() {
        return "Card{" +
                " id=" + id +
                ", account=" + account +
                ", amount=" + amount +
                ", blocked=" + blocked +
                ", unlockCard=" + unlockCard +
                ", client=" + client +
                '}';
    }
}
