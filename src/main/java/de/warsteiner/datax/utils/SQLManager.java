package de.warsteiner.datax.utils;
 
import java.util.concurrent.atomic.AtomicReference; 
 

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.utils.statements.SQLStatementAPI; 

public class SQLManager {

	private SQLStatementAPI mg = UltimateAPI.getInstance().getSQLStatementAPI();
	
	public void createtables() {
		SQLStatementAPI s = UltimateAPI.getInstance().getSQLStatementAPI();
		UltimateAPI.getPlugin().getExecutor().execute(() -> {

			s.executeUpdate("CREATE TABLE IF NOT EXISTS playerlist (UUID varchar(200), NAME varchar(200))");
			
		});
	}
	
	public void updateName(String UUID, String value) { 
		final String insertQuery = "UPDATE `playerlist` SET `NAME`='" + value + "' WHERE UUID='" +UUID + "'";
		mg.executeUpdate(insertQuery); 
	}

	public void updateUUID(String NAME, String u) { 
		final String insertQuery = "UPDATE `playerlist` SET `UUID`='" + u + "' WHERE NAME='" +NAME + "'";
		mg.executeUpdate(insertQuery); 
	}
	
	public void createPlayerInList(String UUID, String name) { 
		final String insertQuery = "INSERT INTO playerlist(UUID,NAME) VALUES(?,?)";
		mg.executeUpdate(insertQuery, ps -> {
			ps.setString(1, UUID);
			ps.setString(2, name);
		
		});
	}
 
	public String getUUIDFromName(String NAME) { 
        AtomicReference<String> a = new AtomicReference<String>();
 
        mg.executeQuery("SELECT * FROM playerlist WHERE NAME= '" + NAME + "'", rs -> {
              if (rs.next()) { 
            	  a.set(rs.getString("UUID"));
             }  
              return 0;
         }); 
         return a.get();
    }
	
	public String getNameFromUUID(String UUID) { 
        AtomicReference<String> a = new AtomicReference<String>();
 
        mg.executeQuery("SELECT * FROM playerlist WHERE UUID= '" + UUID + "'", rs -> {
              if (rs.next()) { 
            	  a.set(rs.getString("NAME"));
             }  
              return 0;
         }); 
         return a.get();
    }
 
	public boolean ExistPlayer(String UUID) { 
        AtomicReference<String> a = new AtomicReference<String>();
 
        mg.executeQuery("SELECT * FROM playerlist WHERE UUID= '" + UUID + "'", rs -> {
              if (rs.next()) { 
            	  a.set(rs.getString("NAME"));
             }  
              return 1;
         }); 
         return a.get() != null;
    }
 

}
