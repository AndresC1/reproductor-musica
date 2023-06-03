package com.example.spotifyclone.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("Select * from music")
    fun getSong(): Flow<List<SongModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun SaveSong(songModel: SongModel)

    @Update
    fun UpdateSong(songModel: SongModel)

    @Query("Delete from music")
    suspend fun DeleteAllSong()

    @Delete
    suspend fun DeleteSong(songModel: SongModel)
}