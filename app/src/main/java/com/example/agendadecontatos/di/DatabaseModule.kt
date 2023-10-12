package com.example.agendadecontatos.di

import android.content.Context
import androidx.room.Room
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.constantes.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constantes.DB_CONTATO
    ).build()

    @Singleton
    @Provides
    fun providesDao(appDatabase: AppDatabase) = appDatabase.contatodao()
}