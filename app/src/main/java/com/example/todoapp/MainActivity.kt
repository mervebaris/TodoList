package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.uix.view.SayfaGecisleri
import com.example.todoapp.uix.viewmodel.AnasayfaViewModel
import com.example.todoapp.uix.viewmodel.IsDetaySayfaViewModel
import com.example.todoapp.uix.viewmodel.IsKayitSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anasayfaViewModel : AnasayfaViewModel by viewModels()
    val isKayitSayfaViewModel : IsKayitSayfaViewModel by viewModels()
    val isDetaySayfaViewModel : IsDetaySayfaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                SayfaGecisleri(anasayfaViewModel,isKayitSayfaViewModel,isDetaySayfaViewModel)
            }
        }
    }
}
