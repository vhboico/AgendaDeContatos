package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.R
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.itemlista.ContatoItem
import com.example.agendadecontatos.model.Contato
import com.example.agendadecontatos.ui.theme.Purple40
import com.example.agendadecontatos.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ListaContatos(navController: NavController){

    val context = LocalContext.current
    val listaContatos: MutableList<Contato> = mutableListOf()
    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO){
        contatoDao = AppDatabase.getInstance(context).contatodao()
        val contatos = contatoDao.getContatos()

        for (contato in contatos){
            listaContatos.add(contato)
        }
    }

    Scaffold (
        topBar = {
            TopAppBar (
                backgroundColor = Purple40,
                contentColor = white,
                title = {
                    Text(
                        text = "Agenda de Contatos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                        )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarContato")
                },
                backgroundColor = Purple40,
                contentColor = white
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Botão de adicionar")
            }
        }

    ){

        LazyColumn{
            itemsIndexed(listaContatos){position, item ->
                ContatoItem(navController, position, listaContatos, context)
            }
        }

    }
}

@Preview
@Composable
fun ListaContatosPreview(){
    ListaContatos(navController = rememberNavController())
}