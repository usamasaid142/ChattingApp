package com.osama.chattingapp.dependancyinjection


import android.content.Context
import androidx.room.Room
import com.osama.chattingapp.data.local.ChatDataBase
import com.osama.chattingapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDatabse(
        @ApplicationContext app: Context
    )= Room.databaseBuilder(app,ChatDataBase::class.java, Constants.Chat_Db).build()

    @Singleton
    @Provides
    fun provideRunDao(db:ChatDataBase)= db.chatDao()


}