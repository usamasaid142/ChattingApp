package com.osama.chattingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {

    @Insert
    suspend fun insertChattingMessage(chatMassege: ChatMassege)
    @Delete
    suspend fun deleteChattingMessage(chatMassege: ChatMassege)
    @Query(" select * from chat_table ")
    fun getChattingMessage(): LiveData<List<ChatMassege>>
}