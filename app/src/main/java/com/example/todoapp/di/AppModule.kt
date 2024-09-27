package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.datasource.YapilacaklarDataSource
import com.example.todoapp.data.repo.YapilacaklarRepository
import com.example.todoapp.room.Veritabani
import com.example.todoapp.room.YapilacaklarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYapilacaklarRepository(kds:YapilacaklarDataSource) : YapilacaklarRepository{
        return YapilacaklarRepository(kds)
    }

    @Provides
    @Singleton
    fun provideYapilacaklarDataSource(kdao:YapilacaklarDao) : YapilacaklarDataSource{
        return YapilacaklarDataSource(kdao)
    }

    @Provides
    @Singleton
    fun provideYapilacaklarDao(@ApplicationContext context: Context) : YapilacaklarDao{
        val vt = Room.databaseBuilder(context,Veritabani::class.java,"yapilacaklarListesi.sqlite")
            .createFromAsset("yapilacaklarListesi.sqlite").build()
        return vt.getYapilacaklarDao()
    }
}