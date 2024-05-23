package com.osama.chattingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.osama.chattingapp.ui.adapter.ChatMessageAdapter
import com.osama.chattingapp.data.local.ChatMassege
import com.osama.chattingapp.databinding.ChattingfragmentBinding
import com.osama.chattingapp.utils.Constants
import com.osama.chattingapp.viewmodels.ChatViewModels
import com.osama.chattingapp.viewmodels.ChatViewmodel
import com.osama.chattingapp.websocket.WebSocketMessageListner
import com.osama.chattingapp.websocket.WebSocketServcies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.json.JSONObject

@AndroidEntryPoint
class ChattingFragment : Fragment(), WebSocketMessageListner {
    private lateinit var binding: ChattingfragmentBinding
    private val viewModel: ChatViewModels by viewModels()
    private val chatViewModel: ChatViewmodel by viewModels()
    private val chatList= mutableListOf<ChatMassege>()
    private lateinit var chatMessageAdapter: ChatMessageAdapter
    private lateinit var ws: WebSocket
    private var id=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ChattingfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getAllChatting()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewChat()
        setUpOkhttpClient()
        initButton()
        getChatData()
        deleteChatItem(view)
    }

    private fun initButton(){
        binding.layoutSend.setOnClickListener {
            id++
            val jsonObject=JSONObject()
            jsonObject.put("message",binding.etMessage.text.toString())
            ws.send(jsonObject.toString())
         val chatMassege=ChatMassege(id,senderId = Constants.KEy_SenderId, receiverId = "", message = binding.etMessage.text.toString())
            viewModel.setChatMessage(chatMassege)
            chatViewModel.insertChattingMessage(chatMassege)
            binding.etMessage.setText("")
        }
    }
    private fun setUpRecyclerViewChat() {
        chatMessageAdapter= ChatMessageAdapter()
        binding.rvChat.apply {
            adapter = chatMessageAdapter
            this.itemAnimator=null
            chatMessageAdapter.notifyDataSetChanged()

        }
    }

    private fun setUpOkhttpClient(){
        val client= OkHttpClient()
        val request= Request.Builder().url(Constants.URL).build()
        val wsListner= WebSocketServcies(this)
         ws=client.newWebSocket(request,wsListner)

    }
    override fun onMessageReceived(message: String) {
       runBlocking {
           val jsonObject=JSONObject(message)
           jsonObject.put("message",message)
           android.util.Log.e("TAG", "onMessageReceived: ${jsonObject.toString()}")
           val chatMassege=ChatMassege(id,senderId = "", receiverId = Constants.KEy_ReceiverId, jsonObject.toString())
           chatViewModel.insertChattingMessage(chatMassege)
           viewModel.setChatMessage(chatMassege)
           }
       }
    private fun getChatData(){
        viewModel.chatMessageResponse.observe(viewLifecycleOwner, Observer {
            chatList.add(it)
            chatMessageAdapter.submitList(chatList)
            chatMessageAdapter.notifyItemRangeInserted(
                chatList.size,
                chatList.size
            )
           binding.rvChat.smoothScrollToPosition(chatList.size - 1)
        })
    }

    private fun getAllChatting() {
        chatViewModel.allChatting.observe(viewLifecycleOwner, Observer {
           if (!it.isNullOrEmpty()){
               id = it[it.size-1].id!!
           }
            chatMessageAdapter.submitList(it)
            chatMessageAdapter.notifyDataSetChanged()
        })
    }

    fun deleteChatItem(view: View) {
        val itemtouchelper = object : ItemTouchHelper.SimpleCallback(

            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val chatItem = chatMessageAdapter.currentList[position]
                chatViewModel.deleteChattingMessage(chatItem)
                Snackbar.make(view, "chat deleted successfully", Snackbar.LENGTH_SHORT).apply {
                    setAction("undo") {
                        chatViewModel.insertChattingMessage(chatItem)
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemtouchelper).apply {
            attachToRecyclerView(binding.rvChat)
        }
    }

}