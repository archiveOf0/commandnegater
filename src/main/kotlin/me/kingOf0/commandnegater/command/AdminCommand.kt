package me.kingOf0.commandnegater.command

import me.kingOf0.commandnegater.manager.FileManager
import me.kingOf0.commandnegater.manager.NegateManager
import me.kingOf0.commandnegater.manager.NegateManager.admins
import me.kingOf0.commandnegater.manager.SettingsManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminCommand : CommandExecutor {

    //admin <args>
    override fun onCommand(commandSender: CommandSender, p1: Command, p2: String, args: Array<out String>): Boolean {
        if (commandSender !is Player) {
            FileManager.reload()
            SettingsManager.reload()
            NegateManager.reload()
            commandSender.sendMessage(SettingsManager.reloaded)
            return true
        }
        if (admins.contains(commandSender.uniqueId)) {
            commandSender.sendMessage(SettingsManager.alreadyAdmin)
            return true
        }

        if (args.getOrNull(0) != NegateManager.password) {
            commandSender.sendMessage(SettingsManager.wrongPassword)
            return true
        }

        admins.add(commandSender.uniqueId)
        commandSender.sendMessage(SettingsManager.adminModeEnabled)
        FileManager.log(SettingsManager.adminModeEnabledDebug.replace("%player%", commandSender.name))
        return true
    }


}