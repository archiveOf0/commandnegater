package com.kingOf0.commandnegater.listener

import com.kingOf0.commandnegater.manager.NegateManager
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandSendEvent

class TabListener : Listener {

    @EventHandler
    fun onTab(event: PlayerCommandSendEvent) {
        if (NegateManager.isAdmin(event.player.uniqueId)) return
        NegateManager.mapTabComplete(event.commands)
    }

}