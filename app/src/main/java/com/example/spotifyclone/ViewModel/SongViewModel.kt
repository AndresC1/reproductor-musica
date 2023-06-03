package com.example.spotifyclone.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import com.example.spotifyclone.repository.SongRepository
import com.example.spotifyclone.room.RoomDabase
import com.example.spotifyclone.room.SongDao
import com.example.spotifyclone.room.SongModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongViewModel (application: Application) : AndroidViewModel(application){
    val listSong : LiveData<List<SongModel>>
    val repository : SongRepository

    init {
        val songDao = RoomDabase.getDatabase(application).SongDao()
        repository = SongRepository(songDao)
        listSong = repository.GetAllSong()
    }

    fun InsertSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.SaveSong(song) }

    fun ClearAllSong() =
        viewModelScope.launch(Dispatchers.IO) { repository.ClearAllSong() }

    fun UpdateSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.UpdateSong(song) }

    fun DeleteSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.DeleteSong(song) }
}