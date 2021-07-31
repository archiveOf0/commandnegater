package me.kingOf0.commandnegater.listener

import me.kingOf0.commandnegater.NegateManager.allowedAdmins
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class AdminListener : Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        allowedAdmins.remove(event.player.uniqueId)
    }

}