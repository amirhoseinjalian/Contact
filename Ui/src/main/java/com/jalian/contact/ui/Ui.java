package com.jalian.contact.ui;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.FileUtil;
import com.jalian.contact.config.Config;
import com.jalian.contact.logic.ContactController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
public class Ui {
    public static void main(String[] args) throws JoranException, IOException {
        Config config = Config.getInstance();
        String logPath = config.getLogPathProperty();
        System.out.println(logPath);
        //System.setProperty("logback.configurationFile", "D:/log path/logback.xml");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        InputStream configStream = new FileInputStream(logPath);
        configurator.setContext(loggerContext);
        configurator.doConfigure(configStream); // loads logback file
        configStream.close();
        Logger logger = LoggerFactory.getLogger(Ui.class.getName());
        logger.info("Main started {} *****************************************", Ui.class.getSimpleName());
        ContactController contactController = ContactController.getInstance();
        String option, name, number, ladeSt, subSt;
        System.out.println("1)Show Your Contacts\n2)Add A New Contact\n3)Exit");
        Scanner input = new Scanner(System.in);
        option = input.nextLine();
        if(option.equals("1")) {
            System.out.println(contactController.show());
        }else if(option.equals("2")) {
            System.out.println ("Please Insert the Name :");
            name = input.nextLine ();
            System.out.println ("Please Insert the Number :");
            number = input.nextLine ();
            System.out.println ("Please Insert the Lade Street :");
            ladeSt = input.nextLine ();
            System.out.println("Sub Street");
            subSt = input.nextLine();
            contactController.addContact(name, number, ladeSt, subSt);
        }else if(option.equals("3")) {
            contactController.exit();
            System.out.println("Bye Bye!!!!!!");
            logger.info("Main finished********************66666666***********************");
        }else {
            System.out.println("What???");
        }
    }
}