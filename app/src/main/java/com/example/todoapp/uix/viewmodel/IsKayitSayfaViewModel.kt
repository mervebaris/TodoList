package com.example.todoapp.uix.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.YapilacaklarRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class IsKayitSayfaViewModel @Inject constructor (var krepo:YapilacaklarRepository): ViewModel() {

    fun kaydet(is_ad:String){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.kaydet(is_ad)
        }
    }

}