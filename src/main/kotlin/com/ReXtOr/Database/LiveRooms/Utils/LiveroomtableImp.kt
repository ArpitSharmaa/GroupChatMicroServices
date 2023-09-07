package com.ReXtOr.Database.LiveRooms.Utils

import com.ReXtOr.Database.DatabaseConnectiom.databaseConnection
import com.ReXtOr.Database.LiveRooms.Utils.liveroomstable
import com.ReXtOr.Database.LiveRooms.model.listOfLvieRooms
import com.ReXtOr.Database.LiveRooms.model.liveRoomsNames

class liveroomtableImp: liveroomstable {
    val rooms = databaseConnection.database.getCollection<liveRoomsNames>()
    override suspend fun insertliveroom(liveRoomsNames: liveRoomsNames): Boolean {
        return rooms.insertOne(liveRoomsNames).wasAcknowledged()
    }

    override suspend fun getliverooms(): listOfLvieRooms {
       return listOfLvieRooms(
           listOfRooms = rooms.find().toList()
       )
    }
}