package com.osama.chattingapp.websocket

interface WebSocketMessageListner {
    fun onMessageReceived(message:String)
}