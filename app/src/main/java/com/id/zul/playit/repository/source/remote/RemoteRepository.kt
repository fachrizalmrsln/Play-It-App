package com.id.zul.playit.repository.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.movie.MovieResponse
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.model.tv.TvResponse
import com.id.zul.playit.data.network.ApiClient
import com.id.zul.playit.data.network.ApiService
import com.id.zul.playit.utils.IdlingResource
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

        IdlingResource.increment()

        val movieResponseCall: Call<MovieResponse> =
            ApiClient.getClient().create(ApiService::class.java)
                .getNowPlayingMovies(page)
        movieResponseCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
                dataNowPlayingMovie.value = null
                IdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                movieResponse: Response<MovieResponse>
            ) {
                movieResponse.body()?.let {
                    dataNowPlayingMovie.value = it.results
                }
                IdlingResource.decrement()
            }

        })
        return dataNowPlayingMovie
    }

    fun getOnAirTv(page: Int): LiveData<List<Tv>> {
        val dataOnAirTv: MutableLiveData<List<Tv>> = MutableLiveData()

        IdlingResource.increment()

        val tvResponseCall: Call<TvResponse> =
            ApiClient.getClient().create(ApiService::class.java)
                .getOnTheAirTv(page)
        tvResponseCall.enqueue(object : Callback<TvResponse> {
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
                dataOnAirTv.value = null
                IdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<TvResponse>,
                movieResponse: Response<TvResponse>
            ) {
                movieResponse.body()?.let {
                    dataOnAirTv.value = it.results
                }
                IdlingResource.decrement()
            }

        })
        return dataOnAirTv
    }

    fun getMovieById(id: Int): LiveData<Movie> {
        val movieById: MutableLiveData<Movie> = MutableLiveData()

        IdlingResource.increment()

        val movieResponseCall: Call<Movie> =
            ApiClient.getClient().create(ApiService::class.java)
                .getMovieById(id)
        movieResponseCall.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("Fail", t.toString())
                movieById.value = null
                IdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<Movie>,
                movieResponse: Response<Movie>
            ) {
                movieResponse.body()?.let {
                    movieById.value = it
                    IdlingResource.decrement()
                }
            }

        })
        return movieById
    }

    fun getTvById(id: Int): LiveData<Tv> {
        val tvById: MutableLiveData<Tv> = MutableLiveData()

        IdlingResource.increment()

        val tvResponseCall: Call<Tv> =
            ApiClient.getClient().create(ApiService::class.java)
                .getTvById(id)
        tvResponseCall.enqueue(object : Callback<Tv> {
            override fun onFailure(call: Call<Tv>, t: Throwable) {
                Log.d("Fail", t.toString())
                tvById.value = null
                IdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<Tv>,
                movieResponse: Response<Tv>
            ) {
                movieResponse.body()?.let {
                    tvById.value = it
                    IdlingResource.decrement()
                }
            }

        })
        return tvById
    }

}