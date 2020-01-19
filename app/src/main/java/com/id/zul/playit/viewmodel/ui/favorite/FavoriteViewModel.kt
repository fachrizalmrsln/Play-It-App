package com.id.zul.playit.viewmodel.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.repository.CatalogueRepository

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<FavoriteEntity>> =
        catalogueRepository.getFavoriteMovie()

    fun getFavoriteTv(): LiveData<PagedList<FavoriteEntity>> =
        catalogueRepository.getFavoriteTv()

}