package com.example.agendadecontatos.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.agendadecontatos.R
import com.example.agendadecontatos.model.Contato
import com.example.agendadecontatos.ui.theme.ShapesCardView
import com.example.agendadecontatos.ui.theme.white
import com.example.agendadecontatos.viewmodel.ContatoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
fun ContatoItem(
    navController: NavController,
    position: Int,
    listaContato: MutableList<Contato>,
    context: Context,
    viewModel: ContatoViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    val nome = listaContato[position].nome
    val sobrenome = listaContato[position].sobrenome
    val idade = listaContato[position].idade
    val celular = listaContato[position].celular
    val uid = listaContato[position].uid

    fun alertDialog() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deseja excluir?")
            .setMessage("Tem certeza?")
            .setPositiveButton("Ok"){_,_ ->
                scope.launch(Dispatchers.IO){
                    viewModel.deletar(uid)
                }

                scope.launch(Dispatchers.Main){
                    navController.navigate("listaContato")
                    Toast.makeText(context, "Contato removido com sucesso", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar"){_,_->

            }
        alertDialog.show()
    }

    Card(
        backgroundColor = white,
        contentColor = white,
        elevation = 8.dp,
        shape = ShapesCardView.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp, 10.dp, 10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (txtName, txtIdade, txtCelular, btnAtualizar, btnDeletar) = createRefs()

            Text(
                text = "Contato: $nome $sobrenome",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtName) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start, 10.dp)
                }
            )

            Text(
                text = "Idade: $idade",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtIdade) {
                    top.linkTo(txtName.bottom, 3.dp)
                    start.linkTo(parent.start, 10.dp)
                }
            )

            Text(
                text = "Telefone: $celular",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtCelular) {
                    top.linkTo(txtIdade.bottom, 3.dp)
                    start.linkTo(parent.start, 10.dp)
                }
            )

            Button(
                onClick = {
                    navController.navigate("atualizarContato/{$uid}")
                },
                modifier = Modifier.constrainAs(btnAtualizar) {
                    start.linkTo(txtCelular.end, 30.dp)
                    top.linkTo(parent.top, 50.dp)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = white
                ),
                elevation = ButtonDefaults.elevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                )
            )
            {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_edit_24),
                    contentDescription = "Botão de editar"
                )
            }

            Button(
                onClick = {
                    alertDialog()
                },
                modifier = Modifier.constrainAs(btnDeletar) {
                    start.linkTo(btnAtualizar.end, 10.dp)
                    top.linkTo(parent.top, 50.dp)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = white
                ),
                elevation = ButtonDefaults.elevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                )
            )
            {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Botão de deletar"
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContatoItemPreview() {

}