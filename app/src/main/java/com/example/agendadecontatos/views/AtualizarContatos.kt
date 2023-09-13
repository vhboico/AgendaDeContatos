package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.componentes.ButtonCustom
import com.example.agendadecontatos.componentes.OutlineTextFieldCustom
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.ui.theme.Purple40
import com.example.agendadecontatos.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AtualizarContatos(navController: NavController, uid: String) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var nome by remember {
        mutableStateOf("")
    }

    var sobrenome by remember {
        mutableStateOf("")
    }

    var idade by remember {
        mutableStateOf("")
    }

    var celular by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple40,
                contentColor = white,
                title = {
                    Text(text = "Atualizar Contato", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            OutlineTextFieldCustom(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "Nome") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 80.dp, 20.dp, 10.dp)
            )

            OutlineTextFieldCustom(
                value = sobrenome,
                onValueChange = { sobrenome = it },
                label = { Text(text = "Sobrenome") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlineTextFieldCustom(
                value = idade,
                onValueChange = { idade = it },
                label = { Text(text = "Idade") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlineTextFieldCustom(
                value = celular,
                onValueChange = { celular = it },
                label = { Text(text = "Sobrenome") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            ButtonCustom(
                onClick = {

                    var mensagem = false

                    scope.launch(Dispatchers.IO) {
                        if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()) {
                            mensagem = false
                        } else {
                            mensagem = true
                            contatoDao = AppDatabase.getInstance(context).contatodao()
                            contatoDao.atualizar(uid.toInt(), nome, sobrenome, idade, celular)
                        }
                    }

                    scope.launch(Dispatchers.IO){
                        if (mensagem){
                            Toast.makeText(context, "Atualizado com sucesso", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Erro ao atualizar", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                text = "Atualizar Contato"
            )
        }
    }
}