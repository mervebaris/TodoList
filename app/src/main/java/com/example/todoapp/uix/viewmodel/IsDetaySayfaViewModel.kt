package com.example.todoapp.uix.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.YapilacaklarRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class IsDetaySayfaViewModel @Inject constructor (var krepo:YapilacaklarRepository): ViewModel() {

    fun guncelle(is_id:Int, is_ad:String){
       CoroutineScope(Dispatchers.Main).launch {
           krepo.guncelle(is_id,is_ad)
       }
    }

}