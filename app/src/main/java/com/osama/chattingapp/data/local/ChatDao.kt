package com.osama.chattingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChattingMessage(chatMassege: ChatMassege)
    @Delete
    suspend fun deleteChattingMessage(chatMassege: ChatMassege)
    @Query(" select * from chat_table ")
    fun getChattingMessage(): LiveData<List<ChatMassege>>
}