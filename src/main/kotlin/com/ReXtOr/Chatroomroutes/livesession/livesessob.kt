package com.ReXtOr.Chatroomroutes.livesession

import com.ReXtOr.Database.DatabaseConnectiom.databaseConnection
import com.ReXtOr.Database.LiveRooms.Utils.liveroomtableImp
import com.ReXtOr.Database.LiveRooms.model.liveRoomsNames
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.Socket
import java.net.http.WebSocket
import java.util.concurrent.ConcurrentHashMap

fun Route.livesession(){
    val roomstable = liveroomtableImp()
//    val room = liveroomtableImp()
//    val scope = CoroutineScope(Dispatchers.IO)
//    val rooms = scope.async {
//        room.getliverooms()
//    }
//
//    scope.launch {
//        val livesessions = rooms.await()
        val members = ConcurrentHashMap<WebSocketSession, Unit>()
//        for (session in livesessions.listOfRooms){
        webSocket("chatroom/hi") {
            val nameofRoom = call.parameters["roomName"]
            nameofRoom?.let {
                liveRoomsNames(
                    nameOfRoom = it
                )
            }?.let { roomstable.insertliveroom(it) }
            members[this] = Unit
            this.send("Connected to the room be respectful")
            for (frame in this.incoming) {
                frame as Frame.Text
                val recieved = frame.readText()
                val others = members.filter {
                    it.key != this
                }
                others.forEach {
                    it.key.send(recieved)
                }
            }
        }

//    }
//    }

}