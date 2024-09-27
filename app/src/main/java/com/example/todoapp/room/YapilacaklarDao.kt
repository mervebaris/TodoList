package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.YapilacaklarListesi

@Dao
interface YapilacaklarDao {
    @Query("SELECT * FROM yapilacaklarListesi")
    suspend fun yapilacaklariYukle() : List<YapilacaklarListesi>

    @Query("SELECT * FROM yapilacaklarListesi WHERE is_ad like '%' || :aramaKelimesi || '%' ")
    suspend fun ara(aramaKelimesi:String) : List<YapilacaklarListesi>

    @Insert
    suspend fun kaydet(yapilacak:YapilacaklarListesi)

    @Update
    suspend fun guncelle(yapilacak:YapilacaklarListesi)

    @Delete
    suspend fun sil(yapilacak:YapilacaklarListesi)



}