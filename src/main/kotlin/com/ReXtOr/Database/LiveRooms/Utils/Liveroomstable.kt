package com.ReXtOr.Database.LiveRooms.Utils

import com.ReXtOr.Database.LiveRooms.model.listOfLvieRooms
import com.ReXtOr.Database.LiveRooms.model.liveRoomsNames

interface liveroomstable {
    suspend fun insertliveroom(liveRoomsNames: liveRoomsNames):Boolean
    suspend fun getliverooms(): listOfLvieRooms
}