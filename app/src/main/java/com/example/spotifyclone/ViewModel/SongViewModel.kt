package com.example.spotifyclone.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.repository.SongRepository
import com.example.spotifyclone.room.SongModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongViewModel (application: Application) : AndroidViewModel(application){
    val listSong: MutableLiveData<List<SongModel>> = MutableLiveData()
    val repository : SongRepository

    init {
        repository = SongRepository()
        GetSongFirestone()
    }

    fun InsertSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.SaveSong(song) }

    fun UpdateSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.UpdateSong(song) }

    fun DeleteSong(song: SongModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.DeleteSong(song) }

    fun GetSongFirestone(){
        viewModelScope.launch(Dispatchers.IO){
            var listData = repository.GetAllSongFirestone()
            listSong.postValue(listData)
        }
    }
}