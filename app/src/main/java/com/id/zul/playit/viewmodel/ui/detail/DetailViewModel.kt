package com.id.zul.playit.viewmodel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogueRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun dummyMovieById(id: Int) = DummyData.dummyMovieById(id)

    fun dummyTvById(id: Int) = DummyData.dummyTvById(id)

    fun getMovieById(id: Int): LiveData<Movie> =
        catalogueRepository.getMovieById(id)

    fun getTvById(id: Int): LiveData<Tv> =
        catalogueRepository.getTvById(id)

    fun insertFavorite(favoriteEntity: FavoriteEntity) =
        catalogueRepository.insertFavorite(favoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        catalogueRepository.deleteFavorite(favoriteEntity)

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity> =
        catalogueRepository.getFavoriteById(id)

}