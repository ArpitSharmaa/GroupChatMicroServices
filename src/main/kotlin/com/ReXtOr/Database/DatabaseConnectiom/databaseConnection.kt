package com.ReXtOr.Database.DatabaseConnectiom

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object databaseConnection {
    val mongopw= System.getenv("MongoDBPass")
    val dbname ="UsersInfo"
    val dbconnection = KMongo.createClient(
        connectionString = "mongodb+srv://arpitskv:$mongopw@cluster0.jvalzci.mongodb.net/$dbname?retryWrites=true&w=majority"
    ).coroutine
    val database = dbconnection.getDatabase(dbname)
}