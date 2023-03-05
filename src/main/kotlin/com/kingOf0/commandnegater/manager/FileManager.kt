package com.kingOf0.commandnegater.manager

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.kingOf0.commandnegater.PLUGIN_INSTANCE
import com.kingOf0.commandnegater.util.file.ConfigFile
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat

const val LOGGER_TEMPLATE: String = "[%s}] [CommandNegater]: %s\n"

object FileManager : IManager("FileManager") {

    private val dateFormat = SimpleDateFormat("hh:mm:ss")

    lateinit var config: ConfigFile
    lateinit var password: ConfigFile
    lateinit var privateLogger: FileWriter

    override fun load(): Boolean {
        config = ConfigFile("config", PLUGIN_INSTANCE)
        password = ConfigFile("password", PLUGIN_INSTANCE)

        File(PLUGIN_INSTANCE.dataFolder, "log.txt").apply {
            if (!exists()) createNewFile()
            privateLogger = FileWriter(this, true)
        }
        return true
    }

    fun log(obj: Any) {
        CoroutineScope(Dispatchers.IO).launch {
            privateLogger.write(LOGGER_TEMPLATE.format(dateFormat.format(System.currentTimeMillis()), obj.toString()))
            privateLogger.flush()
        }.invokeOnCompletion {
            it?.printStackTrace()
        }
    }

    fun reload() {
        config.load()
    }

}
