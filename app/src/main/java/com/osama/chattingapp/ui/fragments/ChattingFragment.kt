package com.osama.chattingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osama.chattingapp.adapter.ChatMessageAdapter
import com.osama.chattingapp.data.local.ChatMassege
import com.osama.chattingapp.databinding.ChattingfragmentBinding
import com.osama.chattingapp.utils.Constants
import com.osama.chattingapp.viewmodels.ChatViewModels
import com.osama.chattingapp.websocket.WebSocketMessageListner
import com.osama.chattingapp.websocket.WebSocketServcies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

@AndroidEntryPoint
class ChattingFragment : Fragment(), WebSocketMessageListner {
    private lateinit var binding: ChattingfragmentBinding
    private val viewModel: ChatViewModels by viewModels()
    private val chatList= mutableListOf<ChatMassege>()
    private lateinit var chatMessageAdapter: ChatMessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ChattingfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewChat()
        setUpOkhttpClient()
        initButton()
        getChatData()
    }

    private fun initButton(){
        binding.layoutSend.setOnClickListener {
         val chatMassege=ChatMassege(Constants.KEy_SenderId,"",binding.etMessage.text.toString())
           viewModel.setChatMessage(chatMassege)
        }
    }
    private fun setUpRecyclerViewChat() {
        chatMessageAdapter=ChatMessageAdapter()
        binding.rvChat.apply {
            adapter = chatMessageAdapter
            chatMessageAdapter.submitList(chatList)
            chatMessageAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpOkhttpClient(){
        val url="ws://localhost:3000"
        val client= OkHttpClient()
        val request= Request.Builder().url(url).build()
        val wsListner= WebSocketServcies(this)
        val ws=client.newWebSocket(request,wsListner)

    }
    override fun onMessageReceived(message: String) {
       runBlocking {
           val chatMassege=ChatMassege("",Constants.KEy_SenderId,"ok")
           viewModel.setChatMessage(chatMassege)
           }
       }
    private fun getChatData(){
        viewModel.chatMessageResponse.observe(viewLifecycleOwner, Observer {
            chatList.add(it)
            chatMessageAdapter.submitList(chatList)
        })
    }

}