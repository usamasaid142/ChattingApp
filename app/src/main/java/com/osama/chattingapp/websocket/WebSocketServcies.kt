package com.osama.chattingapp.websocket

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


open class WebSocketServcies (val webSocketMessageListner: WebSocketMessageListner): WebSocketListener() {

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        printResponse("connection closed ${reason}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
     //   super.onClosing(webSocket, code, reason)
        printResponse(reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
       // super.onFailure(webSocket, t, response)
       printResponse("Failure${response?.message}")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
       // super.onMessage(webSocket, text)
        webSocketMessageListner.onMessageReceived(text)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        printResponse("connected")
    }
    
    fun printResponse(msg:Any){
        Log.e("ws", msg.toString() )
    }
}