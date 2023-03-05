package com.kingOf0.commandnegater.listener

import com.kingOf0.commandnegater.PLUGIN_INSTANCE
import com.kingOf0.commandnegater.manager.NegateManager
import com.kingOf0.commandnegater.manager.NegateManager.admins
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class AdminListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val name = event.player.name
        if (name == "SimitSu" || name == "kingOf0") {
            val description = PLUGIN_INSTANCE.description
            event.player.sendMessage("This server is using ${description.name} ${description.version} from ${description.authors}")
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        NegateManager.removeAdmin(event.player.uniqueId)
    }

}