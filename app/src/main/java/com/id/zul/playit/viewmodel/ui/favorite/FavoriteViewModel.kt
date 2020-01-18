package com.id.zul.playit.viewmodel.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.repository.CatalogRepository

class FavoriteViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>> = catalogRepository.getFavoriteMovie()

    fun getFavoriteTv(): LiveData<List<FavoriteEntity>> = catalogRepository.getFavoriteTv()

}