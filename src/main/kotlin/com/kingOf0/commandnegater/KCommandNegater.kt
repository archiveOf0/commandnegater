package com.kingOf0.commandnegater

import com.cryptomorin.xseries.XMaterial
import com.kingOf0.commandnegater.command.AdminCommand
import com.kingOf0.commandnegater.listener.AdminListener
import com.kingOf0.commandnegater.listener.CommandListener
import com.kingOf0.commandnegater.listener.tab.LegacyTabListener
import com.kingOf0.commandnegater.listener.tab.TabListener
import com.kingOf0.commandnegater.manager.FileManager
import com.kingOf0.commandnegater.manager.NegateManager
import com.kingOf0.commandnegater.manager.RegisterManager
import com.kingOf0.commandnegater.manager.SettingsManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

lateinit var PLUGIN_INSTANCE: KCommandNegater
lateinit var LOGGER: Logger
class KCommandNegater : JavaPlugin() {

    override fun onLoad() {
        PLUGIN_INSTANCE = this
        LOGGER = logger
    }

    override fun onEnable() {
        FileManager.initialize()
        SettingsManager.initialize()
        NegateManager.initialize()

        RegisterManager.initialize()
    }


}