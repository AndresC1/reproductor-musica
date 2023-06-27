package com.example.spotifyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ActivityListSongBinding
import com.example.spotifyclone.room.SongModel

class ListSong : AppCompatActivity() {
    private lateinit var binding: ActivityListSongBinding
    private lateinit var viewModel: SongViewModel
    private lateinit var adapterSong: SongAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.RVlistSong.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(SongViewModel::class.java)

        observeEvents()

        binding.btnAddSong.setOnClickListener{
            val intent = Intent(this, AddSong::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{logout()}
    }
    companion object{
        var isEdit = false
        lateinit var songSelect: SongModel
    }
    private fun observeEvents(){
        viewModel.listSong.observe(this, Observer { list ->
            list?.let{
                adapterSong = SongAdapter(it, viewModel)
                binding.RVlistSong.adapter = adapterSong
                adapterSong.notifyDataSetChanged()
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
    }
}