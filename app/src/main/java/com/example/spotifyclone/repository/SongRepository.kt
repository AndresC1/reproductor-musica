package com.example.spotifyclone.repository

import com.example.spotifyclone.FirebaseSong.SongFirebase
import com.example.spotifyclone.room.SongModel
import com.example.spotifyclone.FirebaseSong.SongFirebase.CreateSong
import com.example.spotifyclone.FirebaseSong.SongFirebase.getSong

class SongRepository{
    suspend fun GetAllSongFirestone(): List<SongModel> = getSong()

    suspend fun SaveSong(song: SongModel){
        CreateSong(song)
    }

    suspend fun UpdateSong(song: SongModel){
        SongFirebase.UpdateSong(song)
    }

    suspend fun DeleteSong(song: SongModel){
        SongFirebase.DeleteSong(song)
    }
}