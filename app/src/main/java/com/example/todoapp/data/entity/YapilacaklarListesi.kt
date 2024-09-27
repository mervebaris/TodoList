package com.example.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "yapilacaklarListesi")
data class YapilacaklarListesi(@PrimaryKey(autoGenerate = true)
                               @ColumnInfo(name = "is_id") @NotNull var is_id:Int,
                               @ColumnInfo(name = "is_ad") @NotNull var is_ad:String) {

}