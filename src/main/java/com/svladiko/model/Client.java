package com.svladiko.model;

import java.util.List;

/**
 * This is POJO.
 * <p>
 * In the class there may be fields, constructors,
 * get, set method and @Override toString method
 * </p>
 *
 * @author Vladislav Serhiychuk
 */
public class Client {
    private int id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String passport;
    private String phone;
    private List<Card> cards;

    public Client() {
    }

    /**
     * This constructor for  method CardDaoImpl.
     *
     * @param id
     */
    public Client(int id) {
        this.id = id;
    }

    public Client(int idClient, String login, String password, String name,
                  String lastName, String passport, String phone, List<Card> cards) {
        this.id = idClient;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
        this.phone = phone;
        this.cards = cards;
    }

    public Client(int idClient, String login, String password, String name,
                  String lastName, String passport, String phone) {
        this.id = idClient;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", phone='" + phone + '\'' +
                ", cards=" + cards +
                '}';
    }
}
