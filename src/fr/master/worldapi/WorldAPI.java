package fr.master.worldapi;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldAPI extends JavaPlugin{
	private static WorldAPI instance;
	private static String prefix;
	@Override
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new WorldChange(), this);
		prefix = "§8[§cWorld§6Server§8]";
		instance = this;
		if(getConfig().getString("Lobby") == null){
			Bukkit.getLogger().severe("The World Lobby isn't Defined The Server Gonna Shutdown");
			Bukkit.shutdown();
		}
	}
	public static String getPrefix(){
		return prefix;
	}
	public static WorldServer getWorldServer(World w){
		return new WorldServer(w);
	}
	public static WorldAPI getInstance(){
		return instance;
	}
}