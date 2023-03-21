package com.kingOf0.commandnegater.manager

import com.kingOf0.commandnegater.LOGGER
import com.kingOf0.commandnegater.base.BlackCommandList
import com.kingOf0.commandnegater.base.CommandList
import com.kingOf0.commandnegater.base.WhiteCommandList
import java.util.*

object NegateManager : IManager("NegateManager") {

    private lateinit var commandList: CommandList

    val admins = HashSet<UUID>()
    val commands = HashSet<String>()

    var password = "default"

    override fun load(): Boolean {
        FileManager.config.apply {
            getConfigurationSection("settings")?.apply {
                for (command in getStringList("list")) {
                    commands.add(command.lowercase(Locale.ENGLISH))
                }
            }
        }
        FileManager.password.apply {
            password = getString("password", "default")!!
            if (password == "default") {
                password = kotlin.random.Random.nextInt(1000, 99999).toString()
                set("password", password)
                FileManager.log("Password has been reset due to 'default' password.")
                LOGGER.warning("Password has been reset due to 'default' password.")
                save()
                load()
            }
        }
        if (SettingsManager.isWhitelist) commands.add("commandnegater")

        commandList = if (SettingsManager.isWhitelist) WhiteCommandList() else BlackCommandList()
        return true
    }

    fun reload() {
        admins.clear()
        commands.clear()
        load()
    }

    fun isAllowedCommand(command: String): Boolean {
        return commandList.isCommandAllowed(command)
    }

    fun isAdmin(uuid: UUID): Boolean {
        return admins.contains(uuid)
    }

    fun mapTabComplete(commands: MutableCollection<String>) {
        for (command in ArrayList(commands)) {
            if (!commandList.isCommandAllowed(command.split(":").last().removePrefix("/"))) {
                commands.remove(command)
            }
        }
    }

    fun removeAdmin(uuid: UUID) {
        admins.remove(uuid)
    }

    fun addAdmin(uuid: UUID) {
        admins.add(uuid)
    }

}