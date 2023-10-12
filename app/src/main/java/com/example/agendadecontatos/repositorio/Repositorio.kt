package com.example.agendadecontatos.repositorio

import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.model.Contato
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class Repositorio @Inject constructor(private val contatoDao: ContatoDao) {

    val getContatos: Flow<MutableList<Contato>> = contatoDao.getContatos()

    suspend fun salvarContato(listaContato: MutableList<Contato>){
        contatoDao.gravar(listaContato)
    }

    suspend fun atualizarContato(id: Int, novoNome: String, novoSobrenome: String, novoIdade: String, novoCelular: String){
        contatoDao.atualizar(id, novoNome, novoSobrenome, novoIdade, novoCelular)
    }

    suspend fun deletar(id: Int){
        contatoDao.delete(id)
    }
}