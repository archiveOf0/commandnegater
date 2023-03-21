package com.kingOf0.commandnegater.manager

import com.cryptomorin.xseries.XMaterial
import com.kingOf0.commandnegater.LOGGER
import com.kingOf0.commandnegater.PLUGIN_INSTANCE
import com.kingOf0.commandnegater.command.AdminCommand
import com.kingOf0.commandnegater.listener.AdminListener
import com.kingOf0.commandnegater.listener.CommandListener
import com.kingOf0.commandnegater.listener.tab.LegacyTabListener
import com.kingOf0.commandnegater.listener.tab.TabListener
import org.bukkit.Bukkit

object RegisterManager : IManager("RegisterManager") {

    override fun load(): Boolean {
        register()
        return true
    }

    fun register() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(AdminListener(), PLUGIN_INSTANCE)
        pluginManager.registerEvents(CommandListener(), PLUGIN_INSTANCE)

        if (XMaterial.supports(13)) {
            pluginManager.registerEvents(TabListener(), PLUGIN_INSTANCE)
            LOGGER.info("+ TabListener registered successfully")
        } else {
            pluginManager.registerEvents(LegacyTabListener(), PLUGIN_INSTANCE)
            LOGGER.info("+ LegacyTabListener registered successfully")
        }

        PLUGIN_INSTANCE.getCommand("commandnegater")?.setExecutor(AdminCommand())
    }

}
