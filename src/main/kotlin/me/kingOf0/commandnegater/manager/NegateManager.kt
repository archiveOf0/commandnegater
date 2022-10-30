package me.kingOf0.commandnegater.manager

import me.kingOf0.commandnegater.LOGGER
import me.kingOf0.commandnegater.list.BlackListListener
import me.kingOf0.commandnegater.list.IListListener
import me.kingOf0.commandnegater.list.WhiteListListener
import org.bukkit.entity.Player
import java.util.*

object NegateManager : IManager("NegateManager") {

    private lateinit var listener: IListListener

    val admins = HashSet<UUID>()
    val commands = HashSet<String>()

    var password = "default"

    override fun load(): Boolean {
        FileManager.config.apply {
            getConfigurationSection("settings")?.apply {
                for (command in getStringList("list")) {
                    commands.add(command.toLowerCase(Locale.ENGLISH))
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

        listener = if (SettingsManager.isWhitelist) WhiteListListener() else BlackListListener()
        return true
    }

    fun reload() {
        admins.clear()
        commands.clear()
        load()
    }

    fun isAllowedCommand(command: String): Boolean {
        return listener.isCommandAllowed(command)
    }

    fun isAdmin(player: Player): Boolean {
        return admins.contains(player.uniqueId)
    }

    fun mapTabComplete(commands: MutableCollection<String>) {
        for (command in ArrayList(commands)) {
            if (!listener.isCommandAllowed(command.split(":").last()))
                commands.remove(command)
        }
    }

}