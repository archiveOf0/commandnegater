package me.kingOf0.commandnegater.list

import me.kingOf0.commandnegater.manager.NegateManager
import java.util.*

class BlackListListener : IListListener {

    override fun isCommandAllowed(command: String): Boolean {
        return !NegateManager.commands.contains(command.toLowerCase(Locale.ENGLISH))
    }

}