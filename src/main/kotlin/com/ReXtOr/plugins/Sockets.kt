package com.ReXtOr.plugins

import com.ReXtOr.Chatroomroutes.livesession.livesession
import com.ReXtOr.Database.DatabaseConnectiom.databaseConnection
import com.ReXtOr.Database.LiveRooms.model.liveRoomsNames
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    val database = databaseConnection.database.getCollection<liveRoomsNames>()
    routing {
        livesession()
        var sessionmembers = ConcurrentHashMap<WebSocketSession,Unit>()
        webSocket("/chatroom/{roomname}") { // websocketSession
            val roomname = call.parameters

//            for (frame in incoming) {
//                if (frame is Frame.Text) {
//                    val text = frame.readText()
//                    outgoing.send(Frame.Text("YOU SAID: $text"))
//                    if (text.equals("bye", ignoreCase = true)) {
//                        close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
//                    }
//                }
//            }
        }
    }
}
