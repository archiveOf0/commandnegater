package me.kingOf0.commandnegater

import java.util.*
import java.util.logging.Logger

object NegateManager {

    val negatedCommands = HashSet<String>()
    val allowedAdmins = HashSet<UUID>()
    val logger: Logger = PLUGIN_INSTANCE!!.logger
    val config = PLUGIN_INSTANCE!!.config

}