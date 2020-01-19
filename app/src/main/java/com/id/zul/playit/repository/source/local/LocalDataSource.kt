package com.id.zul.playit.repository.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.id.zul.playit.model.favorite.FavoriteEntity

interface LocalDataSource {

    fun insertFavorite(favoriteEntity: FavoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity)

    fun getFavoriteMovie(): LiveData<PagedList<FavoriteEntity>>

    fun getFavoriteTv(): LiveData<PagedList<FavoriteEntity>>

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity>

}