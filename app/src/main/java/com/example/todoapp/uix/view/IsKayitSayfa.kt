package com.example.todoapp.uix.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.uix.viewmodel.IsKayitSayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsKayitSayfa(isKayitSayfaViewModel: IsKayitSayfaViewModel){

    val tfIsAd = remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        color = Color(0xFF7BB4DA) // Başlık rengi
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .border(2.dp, Color(0xFFF680AB), RoundedCornerShape(16.dp)) // Çerçeve ayarları
                    .padding(8.dp) // Çerçevenin iç kenar boşluğu
            ) {
                TextField(
                    value = tfIsAd.value,
                    onValueChange = { tfIsAd.value = it },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color(0xFF7BB4DA)),
                            label = {
                        Text(
                            text = "Yapılacak İş",
                            color = Color(0xFFF680AB) // Etiket rengi
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent, // Arka plan rengi
                        focusedLabelColor = Color(0xFFF680AB), // Odaklandığında etiket rengi
                        focusedIndicatorColor = Color.Transparent, // Odaklandığında gösterge rengi
                        unfocusedLabelColor = Color.Transparent, // Odaklanmadığında etiket rengi
                        unfocusedIndicatorColor = Color.Transparent // Odaklanmadığında gösterge rengi
                    )
                )
            }

            Button(
                modifier = Modifier.size(250.dp, 50.dp),
                onClick = {
                    isKayitSayfaViewModel.kaydet(tfIsAd.value)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF7BB4DA)) // Buton arka plan rengi
            ) {
                Text(
                    text = "KAYDET",
                    color = Color.White // Buton üzerindeki yazı rengi
                )
            }
        }
    }

}