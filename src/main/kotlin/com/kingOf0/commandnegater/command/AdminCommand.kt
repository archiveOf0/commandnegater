package com.kingOf0.commandnegater.command

import com.kingOf0.commandnegater.manager.FileManager
import com.kingOf0.commandnegater.manager.NegateManager
import com.kingOf0.commandnegater.manager.SettingsManager
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
        if (NegateManager.isAdmin(commandSender.uniqueId)) {
            commandSender.sendMessage(SettingsManager.alreadyAdmin)
            return true
        }

        if (args.getOrNull(0) != NegateManager.password) {
            commandSender.sendMessage(SettingsManager.wrongPassword)
            return true
        }

        NegateManager.addAdmin(commandSender.uniqueId)
        commandSender.sendMessage(SettingsManager.adminModeEnabled)
        FileManager.log(SettingsManager.adminModeEnabledDebug.replace("%player%", commandSender.name))
        return true
    }


}