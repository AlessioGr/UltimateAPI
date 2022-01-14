package de.warsteiner.datax.utils;
  
import de.warsteiner.datax.utils.statements.SQLStatementAPI;
 
public class DataBaseAPI {
 
    private static SQLStatementAPI sqlStatementAPI = null;
 
    public static SQLStatementAPI getSQLStatementAPI() {
        if (sqlStatementAPI == null) sqlStatementAPI = new SQLStatementAPI();
        return sqlStatementAPI;
    }

}
