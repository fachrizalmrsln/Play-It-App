package com.id.zul.playit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.CatalogRepository

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getNowPlaying(page: Int): LiveData<List<Movie>> {
        return catalogRepository.getNowPlayingMovie(page)
    }
}