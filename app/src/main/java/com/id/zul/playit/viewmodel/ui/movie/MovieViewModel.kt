package com.id.zul.playit.viewmodel.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.repository.CatalogueRepository
import com.id.zul.playit.repository.source.dummy.DummyData

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    val dummyMovie = DummyData.dummyMovie()

    fun getNowPlaying(page: Int): LiveData<List<Movie>> =
        catalogueRepository.getNowPlayingMovie(page)
}