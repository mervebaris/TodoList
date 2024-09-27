package com.example.todoapp.uix.view


import android.graphics.Color.rgb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.uix.viewmodel.AnasayfaViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController,anasayfaViewModel: AnasayfaViewModel){

    val aramaYapiliyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val yapilacaklarListesi = anasayfaViewModel.yapilacaklarListesi.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
       anasayfaViewModel.yapilacaklariYukle()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyorMu.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                anasayfaViewModel.ara(it)
                            },
                            label = { Text(text = "Ara", color = Color(0xFFF680AB)) },
                            colors = TextFieldDefaults.textFieldColors(
                                // Arka plan rengini kutu rengine ayarlayalım
                                containerColor = Color.Transparent, // Kutu rengi
                                focusedLabelColor = Color.White,      // Odaklanıldığında etiket rengi
                                focusedIndicatorColor = Color.Transparent,   // Odaklanıldığında gösterge rengi
                                unfocusedLabelColor = Color.Black,     // Odaklanılmadığında etiket rengi
                                unfocusedIndicatorColor = Color.Transparent, // Odaklanılmadığında gösterge rengi

                            )
                        )
                    }else{
                      Column(modifier = Modifier.fillMaxSize(),
                          verticalArrangement = Arrangement.Center,
                          horizontalAlignment = Alignment.CenterHorizontally) {
                          Text(text = "TodoList",
                              fontSize = 24.sp,
                              fontWeight = FontWeight.Bold,
                              textAlign = TextAlign.Center,
                              color = Color(0xFF7BB4DA))
                      }
                    } },
                actions = {
                    if (aramaYapiliyorMu.value) {
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = false
                            tf.value = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.kapat_resim), tint = Color(0xFFF680AB),
                                contentDescription = "") }

                    }else{
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ara_resim), tint = Color(0xFFF680AB),
                                contentDescription = "") }
                    }
                }
            ) },
        floatingActionButton = {
            FloatingActionButton( onClick = {navController.navigate("IsKayitSayfa") },
                containerColor = Color(0xFF7BB4DA),
                content = {
                    Icon(painter = painterResource(id = R.drawable.ekle_resim), tint = Color.White,
                        contentDescription = "")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Farklı renkleri tutan bir liste oluştur
            val renkListesi = listOf(
                Color(0xFFC666E9),
                Color(0xFF7BB4DA),
                Color(0xFFF680AB),
            )

            items(
                count = yapilacaklarListesi.value.count(),
                itemContent = { index ->
                    val Is = yapilacaklarListesi.value[index]

                    // Renk listesinden index'e göre renk seç
                    val arkaplanRengi = renkListesi[index % renkListesi.size]

                    Card(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = arkaplanRengi // Dinamik arkaplan rengi
                        ),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val IsJson = Gson().toJson(Is)
                                    navController.navigate("IsDetaySayfa/$IsJson")
                                }
                                .padding(16.dp), // İçerik kenarlarından boşluk
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = Is.is_ad,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White // Koyu pembe yazı rengi
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    val sb = snackbarHostState.showSnackbar(
                                        "${Is.is_ad} silinsin mi?",
                                        actionLabel = "EVET"
                                    )
                                    if (sb == SnackbarResult.ActionPerformed) {
                                        anasayfaViewModel.sil(Is.is_id)
                                    }
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.kapat_resim),
                                    tint = Color.White,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
