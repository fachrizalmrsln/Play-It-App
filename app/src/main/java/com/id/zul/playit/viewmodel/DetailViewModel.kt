package com.id.zul.playit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.show.Tv
import com.id.zul.playit.repository.CatalogRepository

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieById(id: Int): LiveData<Movie> =
        catalogRepository.getMovieById(id)

    fun getTvById(id: Int): LiveData<Tv> =
        catalogRepository.getTvById(id)
}