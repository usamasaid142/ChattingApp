package com.osama.chattingapp.data.repository


import com.osama.chattingapp.data.local.ChatDao
import com.osama.chattingapp.data.local.ChatMassege
import javax.inject.Inject

class ChattingRepo @Inject constructor(private val dao:ChatDao) {
    suspend fun insertChattingMessage(chatMassege: ChatMassege)=dao.insertChattingMessage(chatMassege)
    suspend fun updateChattingMessage(chatMassege: ChatMassege)=dao.updateChattingMessage(chatMassege)
    suspend fun deleteChattingMessage(chatMassege: ChatMassege)=dao.deleteChattingMessage(chatMassege)
    fun getChattingMessage()=dao.getChattingMessage()

}
