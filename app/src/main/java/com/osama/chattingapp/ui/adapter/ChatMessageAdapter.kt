package com.osama.chattingapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osama.chattingapp.databinding.ItemContainerRecivedMesageBinding
import com.osama.chattingapp.databinding.ItemContainerSentMessageBinding
import com.osama.chattingapp.data.local.ChatMassege
import com.osama.chattingapp.utils.Constants
import com.osama.chattingapp.utils.Constants.ViewTypeReceived
import com.osama.chattingapp.utils.Constants.ViewTypeSent

class ChatMessageAdapter():ListAdapter<ChatMassege,RecyclerView.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return if (viewType==ViewTypeSent){
            val view= ItemContainerSentMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ViewHolderSent(view)
        }else{
            val view= ItemContainerRecivedMesageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ViewHolderReceived(view)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat=getItem(position)

        if (getItemViewType(position)==ViewTypeSent){
            (holder as ViewHolderSent).binding.apply {
                holder.binding.apply {
                    model=chat
                }
            }

        }else{
            (holder as ViewHolderReceived).binding.apply {
                holder.binding.apply {
                    model=chat
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat=getItem(position)
        return if (chat.senderId == Constants.KEy_SenderId){
            ViewTypeSent
        }else{
            ViewTypeReceived
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
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatMassege, newItem: ChatMassege): Boolean {
            return true
        }
    }
}