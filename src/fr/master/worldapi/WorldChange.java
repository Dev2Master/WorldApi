package fr.master.worldapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChange implements Listener{

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e){
		if(WorldAPI.getWorldServer(e.getPlayer().getWorld()).getState() == State.Shutdown){
			World w = Bukkit.getWorld(WorldAPI.getInstance().getConfig().getString("Lobby"));
			e.getPlayer().sendMessage(WorldAPI.getPrefix()+" §bDésole mais ce WorldServer est actuellement Down");
			e.getPlayer().teleport(new Location(w, w.getSpawnLocation().getX(), w.getSpawnLocation().getX(), w.getSpawnLocation().getX()));
		}
	}
}
