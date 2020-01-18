package com.id.zul.playit.viewmodel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun dummyMovieById(id: Int) = DummyData.dummyMovieById(id)

    fun dummyTvById(id: Int) = DummyData.dummyTvById(id)

    fun getMovieById(id: Int): LiveData<Movie> =
        catalogRepository.getMovieById(id)

    fun getTvById(id: Int): LiveData<Tv> =
        catalogRepository.getTvById(id)

    fun insertFavorite(favoriteEntity: FavoriteEntity) =
        catalogRepository.insertFavorite(favoriteEntity)

    fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        catalogRepository.deleteFavorite(favoriteEntity)

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity> =
        catalogRepository.getFavoriteById(id)

}