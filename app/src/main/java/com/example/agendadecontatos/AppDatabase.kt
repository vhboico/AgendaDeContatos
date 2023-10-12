package com.example.agendadecontatos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contatodao(): ContatoDao
}