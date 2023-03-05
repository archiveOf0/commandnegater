package com.kingOf0.commandnegater.listener

import com.kingOf0.commandnegater.manager.FileManager
import com.kingOf0.commandnegater.manager.NegateManager
import com.kingOf0.commandnegater.manager.SettingsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import java.util.*

class CommandListener : Listener {

    @EventHandler
    fun onCommand(event: PlayerCommandPreprocessEvent) {
        if (NegateManager.isAdmin(event.player.uniqueId)) return

        val command = event.message.split(" ")[0].substring(1).lowercase(Locale.ENGLISH)
        if (NegateManager.isAllowedCommand(command)) return

        event.isCancelled = true
        event.player.sendMessage(*SettingsManager.message)

        if (SettingsManager.debug) FileManager.log(
            SettingsManager.debugMessage
            .replace("%player%", event.player.name)
            .replace("%command%", event.message))
    }
}