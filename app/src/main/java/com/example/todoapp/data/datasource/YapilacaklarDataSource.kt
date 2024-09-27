package com.example.todoapp.data.datasource

import android.util.Log
import com.example.todoapp.data.entity.YapilacaklarListesi
import com.example.todoapp.room.YapilacaklarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YapilacaklarDataSource(var kdao: YapilacaklarDao) {

    suspend fun kaydet(is_ad:String){
        val yeniYapilacak = YapilacaklarListesi(0, is_ad)
        kdao.kaydet(yeniYapilacak)
    }

    suspend fun guncelle(is_id:Int,is_ad:String){
        val guncellenenYapilacak = YapilacaklarListesi(is_id,is_ad)
        kdao.guncelle(guncellenenYapilacak)
    }


    suspend fun sil(is_id:Int){
        val silinenYapilacak = YapilacaklarListesi(is_id,"")
        kdao.sil(silinenYapilacak)
    }

    suspend fun yapilacaklariYukle() : List<YapilacaklarListesi> = withContext(Dispatchers.IO) {
        return@withContext kdao.yapilacaklariYukle()
    }

    suspend fun ara(aramaKelimesi:String) : List<YapilacaklarListesi> = withContext(Dispatchers.IO) {
        return@withContext kdao.ara(aramaKelimesi)

    }
}