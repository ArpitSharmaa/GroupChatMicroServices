package com.ReXtOr.Chatroomroutes.registerroute

import com.ReXtOr.Database.DatabaseConnectiom.databaseConnection
import com.ReXtOr.Database.LiveRooms.Utils.liveroomtableImp
import com.ReXtOr.Database.LiveRooms.model.liveRoomsNames
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*

fun Route.registerroom(){
    val roomstable = liveroomtableImp()
    val scope = CoroutineScope(Dispatchers.IO)
    post ("registerroom"){
        val roomname = call.receive<liveRoomsNames>()
        val inserted = scope.async {
            roomstable.insertliveroom(roomname)
        }
       val z =  inserted.await()
        if (z){
            call.respond(HttpStatusCode.OK,"Entered Data Successfully")
        }else{
            call.respond(HttpStatusCode.BadRequest,"Couldot Insert Data")
        }
    }
}