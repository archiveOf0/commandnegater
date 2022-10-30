package me.kingOf0.commandnegater.list

interface IListListener {

    fun isCommandAllowed(command: String): Boolean
}