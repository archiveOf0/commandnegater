package com.kingOf0.commandnegater.manager

import com.kingOf0.commandnegater.LOGGER
import com.kingOf0.commandnegater.util.KUtils.disable

abstract class IManager(private val name: String) {

    protected abstract fun load() : Boolean
    protected var failMessage: String? = null

    fun initialize() {
        runCatching { load() }
            .onSuccess {
                if (it) success() else failure()
            }.onFailure {
                it.printStackTrace()
                failure()
            }
    }

    private fun success() {
        LOGGER.info("+ $name | Successfully loaded.")
    }

    private fun failure() {
        LOGGER.warning("- $name | Couldn't loaded successfully!")
        disable(failMessage ?: "$name | Couldn't loaded successfully!") //todo: check appearance
    }


}