package com.example.agendadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendadecontatos.viewmodel.ContatoViewModel
import com.example.agendadecontatos.views.AtualizarContatos
import com.example.agendadecontatos.views.ListaContatos
import com.example.agendadecontatos.views.SalvarContatos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: ContatoViewModel = hiltViewModel()

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "listaContato"){
                composable("listaContato"){
                    ListaContatos(navController, viewModel)
                }

                composable("salvarContato"){
                    SalvarContatos(navController, viewModel)
                }
                composable(
                    "atualizarContato/{uid}",
                    arguments = listOf(navArgument("uid"){})
                    ){
                    AtualizarContatos(navController, viewModel ,it.arguments?.getString("uid").toString())
                }
            }
        }
    }
}
