package com.id.zul.playit.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.id.zul.playit.model.favorite.FavoriteEntity

@Dao
interface DatabaseObject {

    @Insert
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    fun deleteFavorite(favoriteEntity: FavoriteEntity): Int

    @Query("Select * from favorite where favoriteType = 'movie'")
    fun getAllFavoriteMovie(): LiveData<List<FavoriteEntity>>

    @Query("Select * from favorite where favoriteType = 'tv'")
    fun getAllFavoriteTv(): LiveData<List<FavoriteEntity>>

    @Query("Select * from favorite where favoriteId = :id")
    fun getFavoriteById(id: Int): LiveData<FavoriteEntity>

}