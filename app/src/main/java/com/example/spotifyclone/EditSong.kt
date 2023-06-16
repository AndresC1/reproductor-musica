package com.example.spotifyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ActivityAddSongBinding
import com.example.spotifyclone.databinding.ActivityEditSongBinding
import com.example.spotifyclone.room.SongModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.util.zip.Inflater

class EditSong : AppCompatActivity() {
    private lateinit var binding: ActivityEditSongBinding
    private lateinit var viewModel: SongViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dataSongSelect = ListSong.songSelect

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(SongViewModel::class.java)

        val nombre = intent.getStringExtra("nombre").toString()
        val artistas = intent.getStringExtra("artistas").toString()
        val anio = intent.getStringExtra("anio").toString()
        val genero = intent.getStringExtra("genero").toString()
        val imagen = intent.getStringExtra("imagen").toString()
        val link_Cancion = intent.getStringExtra("link_Cancion").toString()

        binding.txtNameEdit.text = Editable.Factory.getInstance().newEditable(nombre)
        binding.txtArtistEdit.text = Editable.Factory.getInstance().newEditable(artistas)
        binding.txtAnioSongEdit.text = Editable.Factory.getInstance().newEditable(anio)
        binding.txtGenderEdit.text = Editable.Factory.getInstance().newEditable(genero)
        binding.txtLinkImageEdit.text = Editable.Factory.getInstance().newEditable(imagen)
        binding.txtLinkSongEdit.text = Editable.Factory.getInstance().newEditable(link_Cancion)

        binding.btnEditInfoSong.setOnClickListener{
            GlobalScope.launch {
                dataSongSelect.nombre = binding.txtNameEdit.text.toString()
                dataSongSelect.imagen = binding.txtLinkImageEdit.text.toString()
                dataSongSelect.link_Cancion = binding.txtLinkSongEdit.text.toString()
                dataSongSelect.anio = binding.txtAnioSongEdit.text.toString()
                dataSongSelect.genero = binding.txtGenderEdit.text.toString()
                dataSongSelect.artistas = binding.txtArtistEdit.text.toString()
                EditInfoSong(dataSongSelect)
            }
        }
    }

    suspend fun EditInfoSong(song: SongModel){
        viewModel.UpdateSong(song)
        this.finish()
    }
}