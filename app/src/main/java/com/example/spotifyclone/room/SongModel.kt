package com.example.spotifyclone.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class SongModel (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "imagen") val imagen:String,
    @ColumnInfo(name = "link_Cancion") val link_Cancion:String,
    @ColumnInfo(name = "anio") val anio:Int,
    @ColumnInfo(name = "genero") val genero:String,
    @ColumnInfo(name = "artistas") val artistas:String,
    @ColumnInfo(name = "created_at") val created_at:String,
    @ColumnInfo(name = "updated_at") val updated_at:String,
        )