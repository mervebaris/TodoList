package com.example.todoapp.uix.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.YapilacaklarListesi
import com.example.todoapp.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor (var krepo:YapilacaklarRepository) : ViewModel() {

    var yapilacaklarListesi = MutableLiveData<List<YapilacaklarListesi>>()

    init {
        yapilacaklariYukle()
    }

    fun yapilacaklariYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = krepo.yapilacaklariYukle()
        }
    }

    fun ara(aramaKelimesi:String) {
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = krepo.ara(aramaKelimesi)
        }
    }

    fun sil(is_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.sil(is_id)
            yapilacaklariYukle()
        }
    }
}