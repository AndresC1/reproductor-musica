package com.example.spotifyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ActivityAddSongBinding
import com.example.spotifyclone.room.SongModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.time.LocalDate

class AddSong : AppCompatActivity() {
    private lateinit var binding: ActivityAddSongBinding
    private lateinit var viewModel: SongViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(SongViewModel::class.java)

        binding.btnSaveSong.setOnClickListener{
            GlobalScope.launch {
                InsertSongNew()
            }
        }
    }
    suspend fun InsertSongNew(){
        var anio: Int = 0
        try {
            anio =  binding.txtAnioSong.text.toString().toInt()
        }catch (ex: NumberFormatException){
            anio = 2023
        }
        var DataInit = SongModel(
            0,
            binding.txtName.text.toString(),
            binding.txtLinkImage.text.toString(),
            binding.txtLinkSong.text.toString(),
            anio,
            binding.txtGender.text.toString(),
            binding.txtArtist.text.toString(),
            "xx-xx-xxxx",
            "xx-xx-xxxx"
        )
        viewModel.InsertSong(DataInit)
        this.finish()
    }
}