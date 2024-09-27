package com.example.todoapp.data.repo

import com.example.todoapp.data.datasource.YapilacaklarDataSource
import com.example.todoapp.data.entity.YapilacaklarListesi

class YapilacaklarRepository(var kds:YapilacaklarDataSource) {

    suspend fun kaydet(is_ad:String) = kds.kaydet(is_ad)

    suspend fun guncelle(is_id:Int, is_ad:String) = kds.guncelle(is_id,is_ad)

    suspend fun sil(is_id:Int) = kds.sil(is_id)

    suspend fun yapilacaklariYukle() : List<YapilacaklarListesi> = kds.yapilacaklariYukle()

    suspend fun ara(aramaKelimesi:String) : List<YapilacaklarListesi> = kds.ara(aramaKelimesi)

}