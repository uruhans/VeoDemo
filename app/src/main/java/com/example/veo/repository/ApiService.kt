package com.example.veo.repository

import com.example.veo.BuildConfig
import com.example.veo.model.MovieDetail
import com.example.veo.model.WeeklyItems
import retrofit2.http.GET

interface ApiService {
    @GET("trending/all/week?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovies(): WeeklyItems

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieDetails(): MovieDetail

}