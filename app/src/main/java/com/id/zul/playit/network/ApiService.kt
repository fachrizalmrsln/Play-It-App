package com.id.zul.playit.network

import com.id.zul.mtv.data.model.tv.Tv
import com.id.zul.mtv.data.model.tv.TvResponse
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.movie.MovieResponse
import com.id.zul.playit.utilis.Network
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?api_key=${Network.API_KEY}")
    fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{id}?api_key=${Network.API_KEY}")
    fun getMovieById(
        @Path("id") id: Int
    ): Call<Movie>

    @GET("tv/on_the_air?api_key=${Network.API_KEY}")
    fun getOnAirTv(
        @Query("page") page: Int
    ): Call<TvResponse>

    @GET("tv/{id}?api_key=${Network.API_KEY}")
    fun getTvById(
        @Path("id") id: Int
    ): Call<Tv>

}