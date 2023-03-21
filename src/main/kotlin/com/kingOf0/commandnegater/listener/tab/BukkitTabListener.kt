package com.kingOf0.commandnegater.listener.tab

import com.kingOf0.commandnegater.manager.NegateManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandSendEvent

class BukkitTabListener : Listener {

    @EventHandler
    fun onTab(event: PlayerCommandSendEvent) {
        if (NegateManager.isAdmin(event.player.uniqueId)) return
        NegateManager.mapTabComplete(event.commands)
    }

}