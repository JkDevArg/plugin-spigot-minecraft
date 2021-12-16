package com.jkdev.plugin;
import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	public String ruta;
	
	@Override
	public void onEnable() {
		System.out.println("Mi primer plugin en Maincraft");
		insertConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin Desactivado Bye bye!");
	}
	
	
	@EventHandler
	public void OnMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("salud.mover")) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void JoinServer(PlayerJoinEvent e ) {
		Player player = e.getPlayer();
		String join = "Join";
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(join).replace("%player%", player.getName())));
		
		String messages = "Messages";
		List<String> mensajes= this.getConfig().getStringList(messages);
		
		for(int i=0;i<mensajes.size();i++) {
			String text = mensajes.get(i);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replace("%player%", player.getName())));
		}
	
	}
	
	public void leaveServer(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String leave = "Leave";
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(leave).replace("%player%", player.getName())));
		
	}
	
	public void insertConfig() {
		File config = new File(this.getDataFolder(),"config.yml");
		ruta = config.getPath();
		
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveDefaultConfig();	
		}
	}
		

}
