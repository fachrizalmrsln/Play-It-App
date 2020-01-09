package com.id.zul.playit.repository

import androidx.lifecycle.LiveData
import com.id.zul.mtv.data.model.tv.Tv
import com.id.zul.playit.model.movie.Movie

interface CatalogDataSource {

    fun getNowPlayingMovie(page: Int): LiveData<List<Movie>>

    fun getOnAirTv(page: Int): LiveData<List<Tv>>

}