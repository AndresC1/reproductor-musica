package com.example.spotifyclone.FirebaseSong

import android.util.Log
import com.example.spotifyclone.room.SongModel
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

object SongFirebase {
    suspend fun getSong(): List<SongModel> = coroutineScope {
        val db = Firebase.firestore
        val listaSong = mutableListOf<SongModel>()

        try {
            val querySnapshot = db.collection("canciones").get().await()
            listaSong.addAll(GetAllListSong(querySnapshot))
        }catch (e: Exception){
            Log.e("Error", "Error fetching documents", e)
        }
        listaSong
    }

    suspend fun CreateSong(song: SongModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("canciones").add(song).await()
    }

    suspend fun UpdateSong(song: SongModel) = coroutineScope {
        val db = Firebase.firestore
        val newDataSong = hashMapOf<String, Any>(
            "nombre" to song.nombre,
            "imagen" to song.imagen,
            "link_Cancion" to song.link_Cancion,
            "anio" to song.anio,
            "genero" to song.genero,
            "artistas" to song.artistas,
            "created_at" to song.created_at,
            "updated_at" to song.updated_at
        )
        db.collection("canciones").document(song.idFirestore!!).update(newDataSong).await()
    }

    suspend fun DeleteSong(song: SongModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("canciones").document(song.idFirestore!!).delete()
    }

    suspend fun GetAllListSong(querySnapshot: QuerySnapshot): List<SongModel>{
        val listSong = mutableListOf<SongModel>()

        for(document in querySnapshot.documents){
            val songData = document.data
            val songModel = songData?.let {
                ConvertSongModel(it, document.id)
            }
            listSong.add(songModel!!)
        }
        Log.i("test", querySnapshot.size().toString())
        return listSong
    }

    fun ConvertSongModel(songData: Map<String, Any>, id: String): SongModel{
        val nombre = songData["nombre"] as String
        val imagen = songData["imagen"] as String
        val link_Cancion = songData["link_Cancion"] as String
        val anio = songData["anio"] as String
        val genero = songData["genero"] as String
        val artistas = songData["artistas"] as String
        val created_at = songData["created_at"] as String
        val updated_at = songData["updated_at"] as String

        return SongModel(
            0,
            nombre,
            imagen,
            link_Cancion,
            anio,
            genero,
            artistas,
            created_at,
            updated_at,
            id
        )
    }
}