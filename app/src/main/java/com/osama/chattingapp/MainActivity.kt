package com.osama.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.osama.chattingapp.websocket.WebSocketMessageListner
import com.osama.chattingapp.websocket.WebSocketServcies
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity(),WebSocketMessageListner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpOkhttpClient()
    }

    private fun setUpOkhttpClient(){
        val url="ws://localhost:3000"
        val client=OkHttpClient()
        val request=Request.Builder().url(url).build()
        val wsListner=WebSocketServcies(this)
        client.newWebSocket(request,wsListner)
    }

    override fun onMessageReceived(message: String) {
       runOnUiThread{
           Toast.makeText(this,message,Toast.LENGTH_LONG).show()
       }
    }
}