package me.kingOf0.commandnegater.util

import me.kingOf0.commandnegater.PLUGIN_INSTANCE
import org.bukkit.Bukkit

object KUtils {


    fun disable(msg: String = "Plugin Disabling...") {
        Bukkit.getPluginManager().disablePlugin(PLUGIN_INSTANCE)
        throw IllegalStateException("\n" +
                "\t-----------------------\n" +
                "\t-----------------------\n" +
                "\t-----------------------\n" +
                "\t-----------------------\n" +
                "\t\t$msg...\n" +
                "\t-----------------------\n" +
                "\t-----------------------\n" +
                "\t-----------------------\n").apply {
            stackTrace = stackTrace.take(5).toTypedArray()
        }
    }

}