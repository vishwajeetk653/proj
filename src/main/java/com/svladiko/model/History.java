package com.svladiko.model;


import java.util.Date;

/**
 * POJO by work table history in MySQL database.
 *
 * @author Vladislav Serhiychuk
 *         Created on 5/4/2015.
 */
public class History {
    private int id;
    private int accountSender;
    private int amountRetelling;
    private int accountRetelling;
    private String operations;
    private Date date;

    public History() {
    }

    public History(int id, int accountSender, int amountRetelling, int accountRetelling, String operations, Date date) {
        this.id = id;
        this.accountSender = accountSender;
        this.amountRetelling = amountRetelling;
        this.accountRetelling = accountRetelling;
        this.operations = operations;
        this.date = date;
    }

    public History(int accountSender, int amountRetelling, int accountRetelling, String operations, Date date) {
        this.accountSender = accountSender;
        this.amountRetelling = amountRetelling;
        this.accountRetelling = accountRetelling;
        this.operations = operations;
        this.date = date;
    }

    public int getAccountSender() {
        return accountSender;
    }

    public void setAccountSender(int accountSender) {
        this.accountSender = accountSender;
    }

    public int getAmountRetelling() {
        return amountRetelling;
    }

    public void setAmountRetelling(int amountRetelling) {
        this.amountRetelling = amountRetelling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public int getAccountRetelling() {
        return accountRetelling;
    }

    public void setAccountRetelling(int accountRetelling) {
        this.accountRetelling = accountRetelling;
    }

    @Override
    public String toString() {
        return "SaveNewHistory{" +
                "id=" + id +
                ", accountSender=" + accountSender +
                ", amountRetelling=" + amountRetelling +
                ", accountRetelling=" + accountRetelling +
                ", operations='" + operations + '\'' +
                ", date=" + date +
                '}';
    }
}
