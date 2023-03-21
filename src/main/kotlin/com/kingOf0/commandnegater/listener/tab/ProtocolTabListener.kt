package com.kingOf0.commandnegater.listener.tab

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.kingOf0.commandnegater.LOGGER
import com.kingOf0.commandnegater.PLUGIN_INSTANCE
import com.kingOf0.commandnegater.manager.NegateManager


object ProtocolTabListener : PacketAdapter(PLUGIN_INSTANCE, PacketType.Play.Server.TAB_COMPLETE) {

    private val protocolManager: ProtocolManager = ProtocolLibrary.getProtocolManager()

    override fun onPacketSending(event: PacketEvent) {
        if (NegateManager.isAdmin(event.player.uniqueId)) return
        val packet = event.packet
        if (packet.strings.size() > 0) {
            val raw = packet.strings.read(0)
            if (!raw.startsWith("/")) return
        }

        val list = packet.stringArrays.read(0).toMutableList()
        list.firstOrNull()?.let {
            if (!it.startsWith("/")) return
        }

        NegateManager.mapTabComplete(list)

        packet.stringArrays.write(0, list.toTypedArray())

        /*

        event.isCancelled = true
        runCatching {
            protocolManager.createPacket(PacketType.Play.Server.TAB_COMPLETE).apply {
                stringArrays.write(0, list.toTypedArray())
                protocolManager.sendServerPacket(event.player, this)
            }
        }.onFailure {
            it.printStackTrace()
        }
         */

    }

    fun register() {
        protocolManager.addPacketListener(this)
    }

    fun unregister() {
        protocolManager.removePacketListener(this)
    }


}