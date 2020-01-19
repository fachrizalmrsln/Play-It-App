package com.id.zul.playit.repository.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.id.zul.playit.data.local.DatabaseInstance
import com.id.zul.playit.data.local.DatabaseObject
import com.id.zul.playit.model.favorite.FavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRepository(context: Context) {

    private val databaseObject: DatabaseObject

    init {
        val database = DatabaseInstance.getDatabase(context)
        databaseObject = database.dataBaseObject()
    }

    companion object {
        fun getInstance(context: Context): LocalRepository = LocalRepository(context)
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            databaseObject.insertFavorite(favoriteEntity)
        }
    }

    fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            databaseObject.deleteFavorite(favoriteEntity)
        }
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, FavoriteEntity> =
        databaseObject.getAllFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, FavoriteEntity> = databaseObject.getAllFavoriteTv()

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity> = databaseObject.getFavoriteById(id)

}