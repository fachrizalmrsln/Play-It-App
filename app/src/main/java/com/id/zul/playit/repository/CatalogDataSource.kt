package com.id.zul.playit.repository

import androidx.lifecycle.LiveData
import com.id.zul.playit.model.tv.show.Tv
import com.id.zul.playit.model.movie.Movie

interface CatalogDataSource {

    fun getNowPlayingMovie(page: Int): LiveData<List<Movie>>

    fun getOnAirTv(page: Int): LiveData<List<Tv>>

    fun getMovieById(id: Int): LiveData<Movie>

    fun getTvById(id: Int): LiveData<Tv>

}