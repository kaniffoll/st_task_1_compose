package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.data.dataclasses.Album
import com.example.domain.db.daos.base.BaseDao

@Dao
interface AlbumDao: BaseDao<Album> {
    @Query("SELECT * FROM Album")
    suspend fun getAllAlbums(): List<Album>

    @Query("SELECT * FROM Album WHERE id = :id")
    suspend fun getAlbumById(id: Int): Album
}