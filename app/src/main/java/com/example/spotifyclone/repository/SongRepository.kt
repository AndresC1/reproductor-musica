package com.example.spotifyclone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.spotifyclone.room.SongDao
import com.example.spotifyclone.room.SongModel

class SongRepository  constructor(
    private val SongDao: SongDao
){
    fun GetAllSong(): LiveData<List<SongModel>> = SongDao.getSong().asLiveData()

    suspend fun SaveSong(song: SongModel){
        SongDao.SaveSong(song)
    }

    suspend fun ClearAllSong(){
        SongDao.DeleteAllSong()
    }

    suspend fun UpdateSong(song: SongModel){
        SongDao.UpdateSong(song)
    }

    suspend fun DeleteSong(song: SongModel){
        SongDao.DeleteSong(song)
    }
}