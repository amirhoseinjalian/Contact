package com.jalian.contact.dao.dataBase;
import com.jalian.contact.dao.entities.Contacts;
import com.jalian.contact.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
public class DataBase implements Dao {
    private static DataBase dataBase = new DataBase();
    private final Logger logger = LoggerFactory.getLogger(DataBase.class.getName());
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ArrayList<Contacts> contacts = new ArrayList<>();
    private PreparedStatement preparedStatement;
    private DataBase() {
        try {
            logger.warn("connecting to database started");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/java apps/ContactsDB/contactsdb.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
        } catch (Exception throwables) {
            logger.error("coud'nt connect to database");
            throwables.printStackTrace();
            System.out.println("Message:" + throwables.getMessage());
        }
    }
    //singleton*******************
    public static DataBase getDataBase() {
        return dataBase;
    }
    @Override
    public ArrayList<Contacts> initializeContacts() {
        String name, ladeSt, subSt;
        int number;
        try {
            resultSet = statement.executeQuery("select contacts.name, contacts.number, adress.ladeSt, adress.subSt from contacts inner join adress on adress.contactId = contacts.id;");
            while (resultSet.next()) {
                name = resultSet.getString("name");
                number = resultSet.getInt("number");
                ladeSt = resultSet.getString("ladeSt");
                subSt = resultSet.getString("subSt");
                contacts.add(new Contacts(name, String.valueOf(number), ladeSt, subSt));
            }
        } catch (Exception throwables) {
            logger.error("cauth an exception while reading data from database");
            throwables.printStackTrace();
            System.out.println("Message:" + throwables.getMessage());
        }
        return contacts;
    }
   // @Override
    public void removeArrayList() {
        contacts.clear();
    }
   // @Override
    public void storeContact(String name, String number, String ladeSt, String subSt) {
        int contactId = 0;
        try {
            resultSet = statement.executeQuery("select count (id) from contacts;");
            contactId = resultSet.getInt("count (id)");
            preparedStatement = connection.prepareStatement("insert into contacts (name, number) values (?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(number));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("insert into adress (ladeSt, subSt, contactId) values (?,?,?);");
            preparedStatement.setString(1, ladeSt);
            preparedStatement.setString(2, subSt);
            preparedStatement.setInt(3, contactId+1);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Message:" + e.getMessage());
        }
    }
   // @Override
    public void exit() {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Message:" + e.getMessage());
        }
    }
}