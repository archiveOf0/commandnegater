package com.kingOf0.commandnegater.listener.tab

import com.kingOf0.commandnegater.manager.NegateManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatTabCompleteEvent
import org.bukkit.event.player.PlayerCommandSendEvent

class LegacyTabListener : Listener {

    @EventHandler
    fun onTab(event: PlayerChatTabCompleteEvent) {
        if (NegateManager.isAdmin(event.player.uniqueId)) return
        NegateManager.mapTabComplete(event.tabCompletions)
    }

}