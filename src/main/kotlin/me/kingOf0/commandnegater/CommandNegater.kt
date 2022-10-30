package me.kingOf0.commandnegater

import me.kingOf0.commandnegater.command.AdminCommand
import me.kingOf0.commandnegater.listener.AdminListener
import me.kingOf0.commandnegater.listener.CommandListener
import me.kingOf0.commandnegater.listener.TabListener
import me.kingOf0.commandnegater.manager.FileManager
import me.kingOf0.commandnegater.manager.NegateManager
import me.kingOf0.commandnegater.manager.SettingsManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

lateinit var PLUGIN_INSTANCE: CommandNegater
lateinit var LOGGER: Logger
class CommandNegater : JavaPlugin() {

    override fun onLoad() {
        PLUGIN_INSTANCE = this
        LOGGER = logger
    }

    override fun onEnable() {
        FileManager.initialize()
        SettingsManager.initialize()
        NegateManager.initialize()

        server.pluginManager.registerEvents(AdminListener(), this)
        server.pluginManager.registerEvents(CommandListener(), this)

        /*
        runCatching {
            server.pluginManager.registerEvents(TabListener(), this)
        }.onSuccess {
            logger.info("+ TabListener registered successfully")
        }.onFailure {
            logger.info("- TabListener failed to register")
        }
         */

        getCommand("commandnegater")?.setExecutor(AdminCommand())
    }


}