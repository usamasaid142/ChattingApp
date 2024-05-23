package com.osama.chattingapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity(tableName = "chat_table")
@Serializable
data class ChatMassege(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val senderId:String,
    val receiverId:String,
    val message:String,
)
