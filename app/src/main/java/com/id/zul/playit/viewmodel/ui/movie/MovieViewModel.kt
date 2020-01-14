package com.id.zul.playit.viewmodel.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.CatalogRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    val dummyMovie = DummyData.dummyMovie()

    fun getNowPlaying(page: Int): LiveData<List<Movie>> =
        catalogRepository.getNowPlayingMovie(page)
}