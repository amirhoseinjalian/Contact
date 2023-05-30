package com.jalian.contact.dao.daoFactory;
import com.jalian.contact.dao.Dao;
import com.jalian.contact.config.Config;
import com.jalian.contact.dao.dataBase.DataBase;
import com.jalian.contact.dao.file.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DaoFactory {
    private Config config;
    private String storeModeProperty;
    private static DaoFactory daoFactory = new DaoFactory();
    private final Logger logger = LoggerFactory.getLogger(DaoFactory.class.getName());
    private DaoFactory() {
        config = Config.getInstance();
        logger.trace("getting store mode property_{}", DaoFactory.class.getSimpleName());
        storeModeProperty = config.getStoreModeProperty();
    }
    public Dao getDaoInctance() {
        if(storeModeProperty.equals("dataBase")) {
            return DataBase.getDataBase();
        }else if(storeModeProperty.equals("file")) {
            return File.getInstance();
        }
        return null;
    }
    public static DaoFactory getInstance() { return daoFactory; }
}
