package com.id.zul.playit.repository.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.id.zul.mtv.data.model.tv.Tv
import com.id.zul.mtv.data.model.tv.TvResponse
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.movie.MovieResponse
import com.id.zul.playit.network.ApiClient
import com.id.zul.playit.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    companion object {
        fun getInstance(): RemoteRepository {
            return RemoteRepository()
        }
    }

    fun getNowPlayingMovie(page: Int): LiveData<List<Movie>> {
        val dataNowPlayingMovie: MutableLiveData<List<Movie>> = MutableLiveData()

        val movieResponseCall: Call<MovieResponse> =
            ApiClient.getClient().create(ApiService::class.java)
                .getNowPlayingMovies(page)
        movieResponseCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
                dataNowPlayingMovie.value = null
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                movieResponse: Response<MovieResponse>
            ) {
                movieResponse.body()?.let {
                    dataNowPlayingMovie.value = it.results
                }
            }

        })
        return dataNowPlayingMovie
    }

    fun getOnAirTv(page: Int): LiveData<List<Tv>> {
        val dataOnAirTv: MutableLiveData<List<Tv>> = MutableLiveData()

        val movieResponseCall: Call<TvResponse> =
            ApiClient.getClient().create(ApiService::class.java)
                .getOnAirTv(page)
        movieResponseCall.enqueue(object : Callback<TvResponse> {
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
                dataOnAirTv.value = null
            }

            override fun onResponse(
                call: Call<TvResponse>,
                movieResponse: Response<TvResponse>
            ) {
                movieResponse.body()?.let {
                    dataOnAirTv.value = it.results
                }
            }

        })
        return dataOnAirTv
    }

}