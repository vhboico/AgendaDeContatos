package com.example.agendadecontatos.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agendadecontatos.ui.theme.Purple40
import com.example.agendadecontatos.ui.theme.white

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    text: String){
    Button(
        onClick
        ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple40,
            contentColor = white
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
    {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}