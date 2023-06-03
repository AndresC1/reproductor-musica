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

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(SongViewModel::class.java)

        val id = intent.getIntExtra("id", 0).toInt()
        val nombre = intent.getStringExtra("nombre").toString()
        val artistas = intent.getStringExtra("artistas").toString()
        val anio = intent.getIntExtra("anio", 2023).toString()
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
            var anio: Int = 0
            try {
                anio =  binding.txtAnioSongEdit.text.toString().toInt()
            }catch (ex: NumberFormatException){
                anio = 2023
            }
            GlobalScope.launch {
                EditInfoSong(
                    SongModel(
                        id,
                        binding.txtNameEdit.text.toString(),
                        binding.txtLinkImageEdit.text.toString(),
                        binding.txtLinkSongEdit.text.toString(),
                        anio,
                        binding.txtGenderEdit.text.toString(),
                        binding.txtArtistEdit.text.toString(),
                        "xx-xx-xxx",
                        "xx-xx-xxxx"
                    )
                )
            }
        }
    }

    suspend fun EditInfoSong(song: SongModel){
        viewModel.UpdateSong(song)
        this.finish()
    }
}