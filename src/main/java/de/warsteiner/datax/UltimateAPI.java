package de.warsteiner.datax;
 
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
 
import de.warsteiner.datax.database.DatabaseInit;
import de.warsteiner.datax.utils.DataBaseAPI;
import de.warsteiner.datax.utils.YamlConfigFile;
import de.warsteiner.datax.utils.hikari.HikariAuthentication;
import de.warsteiner.datax.utils.statements.SQLStatementAPI; 

public class UltimateAPI extends JavaPlugin {
 
	private YamlConfigFile config; 
	private DatabaseInit init;
	
    public void onEnable() { 
    	
    	createFolders();
    	
    	setupConfigs();
    	
     
    	
    	setClass(); 
        registerCommands();
        
        YamlConfiguration cfg = this.getMainConfig().getConfig();
    	
    	String host = cfg.getString("Config.host");
		int port =  cfg.getInt("Config.port");
		String database = cfg.getString("Config.database");
		String username =  cfg.getString("Config.user");
		String password =  cfg.getString("Config.password");
		
		String type = cfg.getString("Database.type");
		int size = cfg.getInt("Database.timeout");
		int pool = cfg.getInt("Database.poolsize");
    	
		this.getLogger().info("host -> "+host);
		this.getLogger().info("port -> "+port);
		this.getLogger().info("database -> "+database);
		this.getLogger().info("user -> "+username);
		this.getLogger().info("password -> "+password);
		this.getLogger().info("size -> "+size);
		this.getLogger().info("pool -> "+pool);
        
        init  = this.getInstance().getInit();
		if (getInit().getDataSource() == null || getInit().isClosed()) {
			getInit().initDatabase(new HikariAuthentication(host, port, database, username, password), type, size, pool);
			this.getLogger().info("§bLoaded SQL of UltimateAPI with type: "+type);
		}
        
    }

    public void onDisable() { 
        if(!this.init.isClosed()) {
        	this.init.close();
        }
    }
 
    public void setClass() {
    	init = new DatabaseInit();
    }
    
    public DatabaseInit getInit() {
    	return init;
    }
    
    private void registerCommands() {
     //   getCommand("databasex").setExecutor(new DatabaseXCommand());
     //   SubCommandRegistry.registerCommands();
    }
 
	private void createFolders() {

		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
 
			this.getLogger().info("§6Created Folders for UltimateAPI");
		}

	}
 
	public void setupConfigs() {
		File file_config = new File("plugins/UltimateAPI/", "Config.yml"); 

		config = new YamlConfigFile(getInstance(), file_config);
		 
		try {
			config.load(); 
		} catch (IOException e) {
			this.getLogger().warning("§cFailed to create Config Files");
			e.printStackTrace();
		}
	}
	
	public YamlConfigFile getMainConfig() {
		return config;
	}
    
    public static UltimateAPI getInstance() {
        return UltimateAPI.getPlugin(UltimateAPI.class);
    }

    public SQLStatementAPI getSQLStatementAPI() {
        return DataBaseAPI.getSQLStatementAPI();
    }
}

