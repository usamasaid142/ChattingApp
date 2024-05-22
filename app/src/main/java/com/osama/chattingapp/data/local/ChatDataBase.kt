package com.osama.chattingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChatMassege::class], version = 1)
abstract class ChatDataBase:RoomDatabase() {
    abstract fun chatDao():ChatDao
}