package fr.master.worldapi.event;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.master.worldapi.WorldServer;

public class WorldShutdownEvent extends Event{

	public static final HandlerList handlerlist = new HandlerList();
	private WorldServer ws;
	private World bukkitworld;
	private String reason;
	public WorldShutdownEvent(WorldServer ws, World bukkitworld, String reason) {
		this.ws = ws;
		this.bukkitworld = bukkitworld;
		this.reason = reason;
	}
	@Override
	public HandlerList getHandlers() {
		return handlerlist;
	}
	public WorldServer getWorldServer(){
		return ws;
	}
	public World getBukkitWorld(){
		return bukkitworld;
	}
	public String getReason(){
		return reason;
	}
}
