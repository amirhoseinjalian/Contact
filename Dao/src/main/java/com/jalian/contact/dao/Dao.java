package com.jalian.contact.dao;
import com.jalian.contact.dao.entities.Contacts;

import java.util.ArrayList;
public interface Dao {
    public abstract void exit();
    public abstract ArrayList<Contacts> initializeContacts();
    public abstract void storeContact(String name, String number, String ladeSt, String subSt);
    public abstract void removeArrayList();
}
