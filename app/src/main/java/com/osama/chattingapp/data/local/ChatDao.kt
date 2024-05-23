package com.osama.chattingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingMessage(chatMassege: ChatMassege)
    @Update
    suspend fun updateChattingMessage(chatMassege: ChatMassege)
    @Delete
    suspend fun deleteChattingMessage(chatMassege: ChatMassege)
    @Query(" select * from chat_table ")
    fun getChattingMessage(): LiveData<List<ChatMassege>>
}