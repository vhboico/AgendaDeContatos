package com.example.agendadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.agendadecontatos.model.Contato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {

    @Insert
    suspend fun gravar(listaContatos: MutableList<Contato>)

    @Query("SELECT * FROM  tabela_contato ORDER BY nome ASC")
    fun getContatos(): Flow<MutableList<Contato>>

    @Query("UPDATE tabela_contato SET nome = :novoNome, sobrenome = :novoSobrenome, idade = :novoIdade, celular = :novoCelular " +
            "WHERE uid = :id")
    suspend fun atualizar(id: Int, novoNome: String, novoSobrenome: String, novoIdade: String, novoCelular: String)

    @Query("DELETE FROM tabela_contato WHERE uid = :id ")
    suspend fun delete(id: Int)
}