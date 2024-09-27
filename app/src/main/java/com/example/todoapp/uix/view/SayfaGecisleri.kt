package com.example.todoapp.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.data.entity.YapilacaklarListesi
import com.example.todoapp.uix.viewmodel.AnasayfaViewModel
import com.example.todoapp.uix.viewmodel.IsDetaySayfaViewModel
import com.example.todoapp.uix.viewmodel.IsKayitSayfaViewModel
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,
                   isKayitSayfaViewModel: IsKayitSayfaViewModel,
                   isDetaySayfaViewModel: IsDetaySayfaViewModel){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){

        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }

        composable("IsKayitSayfa"){
            IsKayitSayfa(isKayitSayfaViewModel)
        }

        composable(
            "IsDetaySayfa/{Is}",
            arguments = listOf(
                navArgument("Is"){type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("Is")
            val nesne = Gson().fromJson(json, YapilacaklarListesi::class.java)
            IsDetaySayfa(nesne,isDetaySayfaViewModel)
        }
    }

}