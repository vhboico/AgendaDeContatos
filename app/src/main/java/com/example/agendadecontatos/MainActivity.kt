package com.example.agendadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendadecontatos.views.AtualizarContatos
import com.example.agendadecontatos.views.ListaContatos
import com.example.agendadecontatos.views.SalvarContatos


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "listaContato"){
                composable("listaContato"){
                    ListaContatos(navController)
                }

                composable("salvarContato"){
                    SalvarContatos(navController)
                }
                composable(
                    "atualizarContato/{uid}",
                    arguments = listOf(navArgument("uid"){})
                    ){
                    AtualizarContatos(navController, it.arguments?.getString("uid").toString())
                }
            }
        }
    }
}
