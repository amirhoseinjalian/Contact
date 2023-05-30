package com.jalian.contact.dao.entities;
public class Contacts implements Comparable<Contacts> {
    private String name, number ,adress1, adress2 ;
    public Contacts(String name, String number, String adress1, String adress2) {
        this.name = name;
        this.number = number;
        this.adress1 = adress1;
        this.adress2 = adress2;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return this.number;
    }
    public String getAdress1() {
        return adress1;
    }
    public void setAdress1(String adress1) {
        this.adress1 = adress1;
    }
    public String getAdress2() {
        return adress2;
    }
    public void setAdress2(String adress2) {
        this.adress2 = adress2;
    }
    public String display () {
        return (this.getName () + " " + this.getNumber () + adress1 + " " + adress2);
    }
    public int compareTo(Contacts c) {
        String Information = this.name + " " + this.number;
        String Information2 = c.name + " " + c.number;
        if (Information.compareTo (Information2) == 1) {
            return 1;
        }
        if (Information.compareTo (Information2) == -1) {
            return -1;
        }
        return 0 ;
    }
}