package com.example.spotifyclone

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ItemBinding
import com.example.spotifyclone.room.SongModel

class SongAdapter(var datalist: List<SongModel>, var viewModel: SongViewModel): RecyclerView.Adapter<SongAdapter.SongHolder>() {
    inner class SongHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        var song = datalist[position]
        holder.binding.nameSong.text = song.nombre.toString()
        holder.binding.artistsSong.text = song.artistas.toString()

        holder.binding.btnEditSong.setOnClickListener{
            var intent = Intent(holder.itemView.context, EditSong::class.java)
            intent.putExtra("id", song.id.toInt())
            intent.putExtra("nombre", song.nombre.toString())
            intent.putExtra("artistas", song.artistas.toString())
            intent.putExtra("anio", song.anio.toInt())
            intent.putExtra("genero", song.genero.toString())
            intent.putExtra("imagen", song.imagen.toString())
            intent.putExtra("link_Cancion", song.link_Cancion.toString())
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.btnDeleteSong.setOnClickListener{
            viewModel.DeleteSong(song)
        }
    }
}