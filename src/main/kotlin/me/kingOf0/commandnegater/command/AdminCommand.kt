package me.kingOf0.commandnegater.command

import me.kingOf0.commandnegater.NegateManager.allowedAdmins
import me.kingOf0.commandnegater.NegateManager.config
import me.kingOf0.commandnegater.NegateManager.logger
import me.kingOf0.commandnegater.PLUGIN_INSTANCE
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminCommand : CommandExecutor {

    //admin <args>
    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<String>): Boolean {
        if (sender !is Player) {
            if (args.size == 1 && args[0] == "reload") {
                PLUGIN_INSTANCE!!.reload()
                sender.sendMessage(config.getString("reloadMessage", "The plugin has reloaded."))
                return true
            }
            sender.sendMessage(config.getString("noConsole", "This command cannot be used from console."))
            return true
        }

        if (args.size != 1) {
            sender.sendMessage(config.getString("unsuccessfulEnabledAdminMode", "You dont have permission to use this command!"))
            return true
        }

        if (args[0] == "reload") {
            sender.sendMessage(config.getString("reloadOnlyConsole", "You can only reload from console!"))
            return true
        }
        if (allowedAdmins.contains(sender.uniqueId)) {
            sender.sendMessage(config.getString("alreadyEnabledAdminMode", "Admin mode is already enabled"))
            return true
        }

        if (args[0] != config.getString("password")) {
            sender.sendMessage(config.getString("unsuccessfulEnabledAdminMode", "You dont have permission to use this command!"))
            return true
        }

        allowedAdmins.add(sender.uniqueId)
        sender.sendMessage(config.getString("successfullyEnabledAdminMode", "Admin mode is successfully enabled"))
        logger.info(config.getString("adminEnabledMessage", "%player% has enabled admin mode!").replace("%player%", sender.name))
        return true
    }


}