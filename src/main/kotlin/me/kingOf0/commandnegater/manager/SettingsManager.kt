package me.kingOf0.commandnegater.manager

object SettingsManager : IManager("SettingsManager") {

    var debugMessage = "%player% tried to use '%command%'"
    var reloaded = "The plugin has reloaded."
    var wrongPassword = "You dont have permission to use this command!"
    var alreadyAdmin = "Admin mode is already enabled"
    var adminModeEnabled = "Admin mode is successfully enabled"
    var adminModeEnabledDebug = "%player% has enabled admin mode!"
    var message: Array<String> = arrayOf("")

    var debug: Boolean = false
    var isWhitelist: Boolean = false

    override fun load(): Boolean {
        FileManager.config.apply {
            getConfigurationSection("messages")?.apply {
                if (isString("debugMessage")) debugMessage = getString("debugMessage")!!
                if (isString("reloaded")) reloaded = getString("reloaded")!!
                if (isString("wrongPassword")) wrongPassword = getString("wrongPassword")!!
                if (isString("alreadyAdmin")) alreadyAdmin = getString("alreadyAdmin")!!
                if (isString("adminModeEnabled")) adminModeEnabled = getString("adminModeEnabled")!!
                if (isString("adminModeEnabledDebug")) adminModeEnabledDebug = getString("adminModeEnabledDebug")!!

                if (isString("message")) message = arrayOf(getString("message")!!)
                else if (isList("message")) message = getStringList("message").toTypedArray()
            }
            getConfigurationSection("settings")?.apply {
                if (isBoolean("debug")) debug = getBoolean("debug")
                if (isBoolean("isWhitelist")) isWhitelist = getBoolean("isWhitelist")
            }
        }
        return true
    }

    fun reload() {
        load()
    }

}