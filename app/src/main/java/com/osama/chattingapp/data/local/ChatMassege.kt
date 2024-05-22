package com.osama.chattingapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "chat_table")
data class ChatMassege(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val senderId:String,
    val receiverId:String,
    val message:String,
)
