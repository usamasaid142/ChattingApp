package com.osama.chattingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osama.chattingapp.data.local.ChatMassege
import com.osama.chattingapp.data.repository.ChattingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewmodel @Inject constructor(private val repo:ChattingRepo):ViewModel() {

    fun insertChattingMessage(chatMassege: ChatMassege)=viewModelScope.launch(Dispatchers.IO) {
        repo.insertChattingMessage(chatMassege)
    }
    fun deleteChattingMessage(chatMassege: ChatMassege)=viewModelScope.launch(Dispatchers.IO) {
        repo.deleteChattingMessage(chatMassege)
    }
    val allChatting: LiveData<List<ChatMassege>> = repo.getChattingMessage()

}