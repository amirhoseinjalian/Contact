package com.jalian.contact.logic;
import com.jalian.contact.dao.entities.Contacts;
import com.jalian.contact.dao.Dao;
import com.jalian.contact.dao.daoFactory.DaoFactory;
import java.util.ArrayList;
import java.util.Collections;
public class ContactController {
    private static ContactController contactController = new ContactController();
    private ArrayList<Contacts> contacts;
    private DaoFactory daoFactory;
    private Dao dao;
    private ContactController() {
        daoFactory = DaoFactory.getInstance();
        dao = daoFactory.getDaoInctance();
        Object o = dao.initializeContacts().clone();
        contacts = (ArrayList<Contacts>) o;
        dao.removeArrayList();
    }
    //singleton************
    public static ContactController getInstance() {
        return contactController;
    }
    public String show() {
        StringBuilder all = new StringBuilder();
        Collections.sort(contacts);
        for (Contacts c : contacts) {
            all.append(c.display() + "\n");
        }
        return all.toString();
    }
    public void addContact(String name, String number, String ladeSt, String subSt) {
        //validation*****************
        contacts.add(new Contacts (name, number, ladeSt, subSt));
        sotreContact(name, number, ladeSt, subSt);
    }
    public void sotreContact(String name, String number, String ladeSt, String subSt) {
        dao.storeContact(name, number, ladeSt, subSt);
    }
    public void exit() {
        dao.exit();
    }
}
