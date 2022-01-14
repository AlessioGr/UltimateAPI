package de.warsteiner.datax.database;

import com.zaxxer.hikari.HikariDataSource;
 
import de.warsteiner.datax.utils.hikari.HikariAuthentication;
import de.warsteiner.datax.utils.hikari.HikariSetup;
import de.warsteiner.datax.utils.hikari.SQLTypes;

import java.sql.Connection;

public class DatabaseInit extends HikariSetup {
  
    private HikariAuthentication authentication;
 
    public void initDatabase(HikariAuthentication authentication, String type, int timeout, int ppolsize) {
        this.authentication = authentication;
        init(SQLTypes.fromName(type), authentication, timeout, ppolsize);
    }

    @Override
    public HikariDataSource getDataSource() {
        return super.getDataSource();
    }

    @Override
    public Connection getConnection() {
        return super.getConnection();
    }

    public HikariAuthentication getAuthentication() {
        return authentication;
    }
}
