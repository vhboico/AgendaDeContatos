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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.agendadecontatos.R
import com.example.agendadecontatos.itemlista.ContatoItem
import com.example.agendadecontatos.ui.theme.Purple40
import com.example.agendadecontatos.ui.theme.white
import com.example.agendadecontatos.viewmodel.ContatoViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ListaContatos(navController: NavController, viewModel: ContatoViewModel = hiltViewModel()){

    val context = LocalContext.current

    val listaContatos = viewModel.getContato().collectAsState(mutableListOf()).value

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
            itemsIndexed(listaContatos){position, _ ->
                ContatoItem(navController, position, listaContatos, context, viewModel)
            }
        }

    }
}

//@Preview
//@Composable
//fun ListaContatosPreview(){
//    ListaContatos(navController = rememberNavController())
//}