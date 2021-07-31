package me.kingOf0.commandnegater.listener

import me.kingOf0.commandnegater.NegateManager.allowedAdmins
import me.kingOf0.commandnegater.NegateManager.config
import me.kingOf0.commandnegater.NegateManager.logger
import me.kingOf0.commandnegater.NegateManager.negatedCommands
import me.kingOf0.commandnegater.PLUGIN_INSTANCE
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent

class CommandListener : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onCommand(event: PlayerCommandPreprocessEvent) {
        val uuid = event.player.uniqueId
        if (allowedAdmins.contains(uuid)) return

        if (config.getBoolean("isWhitelist")) {
            var any = false
            for (command in negatedCommands) {
                if (event.message.startsWith(command, true)) {
                    any = true
                    break
                }
            }
            if (any) return
            action(event)
        } else {
            for (command in negatedCommands) {
                if (event.message.startsWith(command, true)) {
                    action(event)
                    break
                }
            }
        }

    }


    private fun action(event: PlayerCommandPreprocessEvent) {
        event.isCancelled = true
        if (config.getBoolean("debug")) {
            logger.warning(config.getString("debugMessage", "%player% tried to use '%command%'")
                .replace("%player%", event.player.name)
                .replace("%command%", event.message))
        }
        event.player.sendMessage(config.getStringList("message").toTypedArray())
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val name = event.player.name
        if (name.equals("SimitSu") || name.equals("kingOf0")) {
            val description = PLUGIN_INSTANCE!!.description
            event.player.sendMessage("This server is using ${description.name} ${description.version} from ${description.authors}")
        }
    }

}