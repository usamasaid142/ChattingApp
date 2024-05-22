package com.osama.chattingapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.chattingapp.data.local.ChatMassege
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ChatViewModels:ViewModel() {

    val chatMessageResponse= MutableLiveData<ChatMassege>()

    private fun setChatMessage(chatMessage: ChatMassege){
        chatMessageResponse.postValue(chatMessage)
    }
}