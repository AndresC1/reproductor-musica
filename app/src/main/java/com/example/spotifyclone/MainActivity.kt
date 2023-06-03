package com.example.spotifyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ActivityListSongBinding
import com.example.spotifyclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SongViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenWindow.setOnClickListener{
            val intent = Intent(this, ListSong::class.java)
            startActivity(intent)
        }
    }
}