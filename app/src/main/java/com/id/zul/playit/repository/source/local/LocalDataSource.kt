package com.id.zul.playit.repository.source.local

import androidx.lifecycle.LiveData
import com.id.zul.playit.model.favorite.FavoriteEntity

interface LocalDataSource {

    fun insertFavorite(favoriteEntity: FavoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity)

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>>

    fun getFavoriteTv(): LiveData<List<FavoriteEntity>>

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity>

}