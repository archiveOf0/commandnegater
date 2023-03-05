package com.kingOf0.commandnegater.base

import com.kingOf0.commandnegater.manager.NegateManager
import java.util.*

class BlackCommandList : CommandList {

    override fun isCommandAllowed(command: String): Boolean {
        return !NegateManager.commands.contains(command.lowercase(Locale.ENGLISH))
    }

}