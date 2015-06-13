package fr.master.worldapi;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import fr.master.worldapi.event.WorldShutdownEvent;

public class WorldServer {
	private WorldAPI wa = WorldAPI.getInstance();
	private World BukkitWorld;
	private State worldstate;
	
	public WorldServer(World BukkitWorld){
		this.BukkitWorld = BukkitWorld;
		this.worldstate = State.Started;
	}
	public String toString(){
		String s = BukkitWorld.getName();
		return s;
	}
	public State getState(){
		return worldstate;
	}
	public void setState(State worldstate){
		this.worldstate = worldstate;
	}
	public void StartWorld(){
		setState(State.Started);
	}
	public void TeleportPlayerWorld(Location loc){
		for(Player pls : getWorldPlayer()){
			pls.teleport(loc);
		}
	}
	@SuppressWarnings("deprecation")
	public void ShutdownWorld(){
		if(BukkitWorld.getName() != wa.getConfig().getString("Lobby")){
			setState(State.Shutdown);
			WorldShutdownEvent ev = new WorldShutdownEvent(this, BukkitWorld, null);
			Bukkit.getPluginManager().callEvent(ev);
			Bukkit.unloadWorld(BukkitWorld, true);
			for(LivingEntity le  : BukkitWorld.getLivingEntities()){
				if(!(le instanceof Player)){
					le.setHealth(0);
				} else {
					Player p = (Player) le;
					World w = Bukkit.getWorld(WorldAPI.getInstance().getConfig().getString("Lobby"));
					p.sendMessage(WorldAPI.getPrefix()+" §bDésole mais ce WorldServer vient de s'éteindre");
					le.teleport(new Location(w, w.getSpawnLocation().getX(), w.getSpawnLocation().getX(), w.getSpawnLocation().getX()));
				}
			}
		} else {
			throw new NullPointerException("You can't shutdown the lobby world");
		}
	}
	public void ShutdownWorld(String reason){
		if(BukkitWorld.getName() != wa.getConfig().getString("Lobby")){
			setState(State.Shutdown);
			WorldShutdownEvent ev = new WorldShutdownEvent(this, BukkitWorld, reason);
			Bukkit.getPluginManager().callEvent(ev);
		} else {
			throw new NullPointerException("You can't shutdown the lobby world");
		}
	}
	public List<Player> getWorldPlayer(){
		return BukkitWorld.getPlayers();
	}
	/**
	 * @return the bukkitWorld
	 */
	public World getBukkitWorld() {
		return BukkitWorld;
	}
}
