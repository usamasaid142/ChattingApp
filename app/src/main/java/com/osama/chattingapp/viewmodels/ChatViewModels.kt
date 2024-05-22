package com.osama.chattingapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osama.chattingapp.data.local.ChatMassege
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModels @Inject constructor():ViewModel() {

    val chatMessageResponse= MutableLiveData<ChatMassege>()

    fun setChatMessage(chatMessage: ChatMassege){
        chatMessageResponse.postValue(chatMessage)
    }
}