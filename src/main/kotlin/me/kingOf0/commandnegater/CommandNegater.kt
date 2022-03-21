package me.kingOf0.commandnegater

import me.kingOf0.commandnegater.NegateManager.negatedCommands
import me.kingOf0.commandnegater.command.AdminCommand
import me.kingOf0.commandnegater.file.ConfigFile
import me.kingOf0.commandnegater.listener.AdminListener
import me.kingOf0.commandnegater.listener.CommandListener
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

var PLUGIN_INSTANCE: CommandNegater? = null

class CommandNegater : JavaPlugin() {

    private lateinit var configFile: ConfigFile

    override fun onEnable() {
        PLUGIN_INSTANCE = this
        configFile = ConfigFile("config", this)
        if (configFile.getString("password", "default").equals("default")) {
            val pass: String = Random.nextInt(100_000, 999_999).toString()
            configFile.set("password", pass)
            configFile.save()
            logger.warning(configFile.getString("passwordChanged", "Warning! New password generated and config has been reloaded check the new one!"))
        }
        reload()

        server.pluginManager.registerEvents(AdminListener(), this)
        server.pluginManager.registerEvents(CommandListener(), this)

        getCommand("admin")?.setExecutor(AdminCommand())
    }

    override fun getConfig(): ConfigFile {
        return configFile
    }

    fun reload() {
        configFile.load()
        negatedCommands.clear()
        for (command in configFile.getStringList("list")) {
            negatedCommands.add("/$command")
        }
        negatedCommands.add("/admin")
    }

}