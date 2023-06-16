package com.example.spotifyclone.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class SongModel (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "nombre") var nombre:String,
    @ColumnInfo(name = "imagen") var imagen:String,
    @ColumnInfo(name = "link_Cancion") var link_Cancion:String,
    @ColumnInfo(name = "anio") var anio:String,
    @ColumnInfo(name = "genero") var genero:String,
    @ColumnInfo(name = "artistas") var artistas:String,
    @ColumnInfo(name = "created_at") var created_at:String,
    @ColumnInfo(name = "updated_at") var updated_at:String,
    val idFirestore: String ?
        )