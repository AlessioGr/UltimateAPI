package de.warsteiner.datax;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.warsteiner.datax.command.UltimateCommand;
import de.warsteiner.datax.command.UltimateTabComplete;
import de.warsteiner.datax.command.sub.CheckSub;
import de.warsteiner.datax.command.sub.HelpSub;
import de.warsteiner.datax.command.sub.PluginsSub;
import de.warsteiner.datax.command.sub.VersionSub;
import de.warsteiner.datax.database.DatabaseInit;
import de.warsteiner.datax.events.ClickAtConnectionStatus;
import de.warsteiner.datax.events.ClickAtPluginsGUI;
import de.warsteiner.datax.events.PlayerExistEvent;
import de.warsteiner.datax.module.ModuleRegistry; 
import de.warsteiner.datax.utils.DataBaseAPI;
import de.warsteiner.datax.utils.GUIManager;
import de.warsteiner.datax.utils.ItemAPI;
import de.warsteiner.datax.utils.PluginAPI;
import de.warsteiner.datax.utils.SQLManager;
import de.warsteiner.datax.utils.YamlConfigFile;
import de.warsteiner.datax.utils.command.UltimateSubCommandRegistry;
import de.warsteiner.datax.utils.hikari.HikariAuthentication;
import de.warsteiner.datax.utils.statements.SQLStatementAPI; 

public class UltimateAPI extends JavaPlugin {

	private YamlConfigFile config;
	private DatabaseInit init;
	private UltimateSubCommandRegistry cmd;
	private ExecutorService executor;
	private Statement con;
	private PluginAPI api;
	private ItemAPI i;
	private GUIManager mg;
	private ModuleRegistry mr;
	private SQLManager sql;
	
	public void onEnable() {

		createFolders();

		setupConfigs();

		setClass();
		registerCommands();

		connect();

		startCheck();

		setCon();
		
		getSQLManager().createtables();
		
		registerEvents();
		this.getLogger().info("§bLoaded  UltimateAPI  with Version : "+getDescription().getVersion());
	}

	public void connect() {

		YamlConfiguration cfg = getMainConfig().getConfig();

		String host = cfg.getString("Config.host");
		int port = cfg.getInt("Config.port");
		String database = cfg.getString("Config.database");
		String username = cfg.getString("Config.user");
		String password = cfg.getString("Config.password");

		String type = cfg.getString("Database.type");
		int size = cfg.getInt("Database.timeout");
		int pool = cfg.getInt("Database.poolsize");

		init = getInit();
		if (getInit().getDataSource() == null || getInit().isClosed()) {
			getInit().initDatabase(new HikariAuthentication(host, port, database, username, password), type, size,
					pool);
			this.getLogger().info("§bLoaded SQL of UltimateAPI with type: " + type);
		}
	 
	}
 
	public void onDisable() {
		if (!this.init.isClosed()) {
			this.init.close();
		}
	}

	public void setClass() {
		api = new PluginAPI();
		i = new ItemAPI();
		mg = new GUIManager();
		init = new DatabaseInit();
		mr = new ModuleRegistry();
		executor = Executors.newFixedThreadPool(config.getConfig().getInt("ExecutorServiceThreads"));
		cmd = new UltimateSubCommandRegistry();
		sql = new SQLManager();
	}
	
	public SQLManager getSQLManager() {
		return sql;
	}
	
	public ModuleRegistry getModuleRegistry() {
		return mr;
	}
	
	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new ClickAtConnectionStatus(), this);
		Bukkit.getPluginManager().registerEvents(new ClickAtPluginsGUI(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerExistEvent(), this);
	}

	public void setCon() {
		try {
			con = getInit().getDataSource().getConnection().createStatement();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public GUIManager getGUIManager() {
		return mg;
	}

	public ItemAPI getItemAPI() {
		return i;
	}
	
	public PluginAPI getAPI() {
		return api;
	}

	public Statement getConnection() {
		return con;
	}

	public DatabaseInit getInit() {
		return init;
	}

	public UltimateSubCommandRegistry getSubCommandRegistry() {
		return cmd;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void startCheck() {
		new BukkitRunnable() {

			@Override
			public void run() {

				getExecutor().execute(() -> {

					if (getInit().isClosed()) {

						connect();

						if (con == null) {
							setCon();
						}
					}
				});
			}

		}.runTaskTimer(getInstance(), 0, 20 * config.getConfig().getInt("CheckConnectionEvery"));
	}

	private void registerCommands() {
		getCommand("ultimateapi").setExecutor(new UltimateCommand());
		getCommand("ultimateapi").setTabCompleter(new UltimateTabComplete());

		getSubCommandRegistry().getSubCommandList().add(new HelpSub());
		getSubCommandRegistry().getSubCommandList().add(new VersionSub());
		getSubCommandRegistry().getSubCommandList().add(new CheckSub());
		getSubCommandRegistry().getSubCommandList().add(new PluginsSub());
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
	
	public static UltimateAPI getPlugin() {
		return UltimateAPI.getPlugin(UltimateAPI.class);
	}

	public SQLStatementAPI getSQLStatementAPI() {
		return DataBaseAPI.getSQLStatementAPI();
	}
}
