package com.id.zul.playit.repository.source.remote

import androidx.lifecycle.LiveData
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv

interface RemoteDataSource {

    fun getNowPlayingMovie(page: Int): LiveData<List<Movie>>

    fun getOnAirTv(page: Int): LiveData<List<Tv>>

    fun getMovieById(id: Int): LiveData<Movie>

    fun getTvById(id: Int): LiveData<Tv>

}