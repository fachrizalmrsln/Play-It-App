package com.id.zul.playit.viewmodel.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.repository.CatalogRepository

class FavoriteViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<FavoriteEntity>> =
        LivePagedListBuilder(catalogRepository.getFavoriteMovie(), 10).build()

    fun getFavoriteTv(): LiveData<PagedList<FavoriteEntity>> =
        LivePagedListBuilder(catalogRepository.getFavoriteTv(), 10).build()

}