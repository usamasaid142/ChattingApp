package com.osama.chattingapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osama.chattingapp.databinding.ItemContainerRecivedMesageBinding
import com.osama.chattingapp.databinding.ItemContainerSentMessageBinding
import com.osama.chattingapp.data.local.ChatMassege
import com.osama.chattingapp.utils.Constants
import com.osama.chattingapp.utils.Constants.viewTypeReceived
import com.osama.chattingapp.utils.Constants.viewTypeSent

class ChatMessageAdapter():ListAdapter<ChatMassege,RecyclerView.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return if (viewType==viewTypeSent){
            val view= ItemContainerSentMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ViewHolderSent(view)
        }else{
            val view= ItemContainerRecivedMesageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ViewHolderReceived(view)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat=getItem(position)

        if (getItemViewType(position)==viewTypeSent){
            (holder as ViewHolderSent).binding.apply {
                textmessage.text=chat.message
            }

        }else{
            (holder as ViewHolderReceived).binding.apply {
                textmessage.text=chat.message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat=getItem(position)
        return if (chat.senderId == Constants.KEy_SenderId){
            viewTypeSent
        }else{
            viewTypeReceived
        }
    }

    class ViewHolderSent(itemBinding: ItemContainerSentMessageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemContainerSentMessageBinding = itemBinding
    }

    class ViewHolderReceived(itemBinding: ItemContainerRecivedMesageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemContainerRecivedMesageBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<ChatMassege>() {
        override fun areItemsTheSame(oldItem: ChatMassege, newItem: ChatMassege): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ChatMassege, newItem: ChatMassege): Boolean {
            return true
        }
    }
}