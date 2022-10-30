package me.kingOf0.commandnegater.list

import me.kingOf0.commandnegater.manager.NegateManager

class WhiteListListener : IListListener {

    override fun isCommandAllowed(command: String): Boolean {
        return NegateManager.commands.contains(command)
    }

}