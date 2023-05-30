package com.jalian.contact.dao.file;
import com.jalian.contact.dao.entities.Contacts;
import com.jalian.contact.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
public class File implements Dao {
    private final Logger logger = LoggerFactory.getLogger(File.class.getName());
    private FileReader fr = null ;
    private BufferedReader br = null ;
    private FileOutputStream fos = null ;
    private BufferedWriter bw = null ;
    private static File fileStore = new File();
    private ArrayList<Contacts> contacts = new ArrayList<Contacts>();
    private File() {
        try {
            fr = new FileReader ("D:/java apps/contactsWithDBAndMVC/src/dao/file/contacts") ;
            br = new BufferedReader (fr) ;
            fos = new FileOutputStream ("D:/java apps/contactsWithDBAndMVC/src/dao/file/contacts", true) ;
            bw = new BufferedWriter (new OutputStreamWriter(fos)) ;
        }catch(Exception e) {
            logger.error("can not read the file");
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static File getInstance() { return fileStore; }
    @Override
    public ArrayList<Contacts> initializeContacts() {
        String line;
        try {
            while((line = br.readLine ()) != null) {
                if(line == null) {
                    break ;
                }
                String s [] = line.split (",");
                contacts.add (new Contacts (s [0] , s [1] , s [2], s [3]));
            }
        }catch(IOException ioe) {
            System.out.println ("Cauth An Exception While Creating Contacts /n" + ioe.getMessage ());
        }
        return contacts;
    }
    @Override
    public void storeContact(String name, String number, String ladeSt, String subSt) {
        String totalInformation = name + "," + number + "," + ladeSt + "," + subSt ;
        try {
            bw.append (totalInformation);
            bw.newLine ();
            bw.flush ();
        }catch(Exception e) {
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void removeArrayList() {
        contacts.clear();
    }
    @Override
    public void exit() {
        try {
            if(fos != null) {
                fos.close();
            }
            if(bw != null) {
                bw.close();
            }
            if(fr != null) {
                fr.close();
            }
            if(br != null) {
                br.close();
            }
        } catch (IOException ioe) {
            System.out.println ("Cauth An Exception While Closing Resources") ;
        }
    }
}