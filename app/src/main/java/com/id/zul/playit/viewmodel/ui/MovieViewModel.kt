package com.id.zul.playit.viewmodel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.dummy.DummyData

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val dummyMovie = DummyData.dataMovie()

    fun getNowPlaying(page: Int): LiveData<List<Movie>> =
        catalogRepository.getNowPlayingMovie(page)
}