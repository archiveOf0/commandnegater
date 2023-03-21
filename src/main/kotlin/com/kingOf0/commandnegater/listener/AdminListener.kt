package com.kingOf0.commandnegater.listener

import com.kingOf0.commandnegater.manager.NegateManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class AdminListener : Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        NegateManager.removeAdmin(event.player.uniqueId)
    }

}