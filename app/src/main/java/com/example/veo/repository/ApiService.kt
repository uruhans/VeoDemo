package com.example.veo.repository

import com.example.veo.BuildConfig
import com.example.veo.model.WeeklyItems
import retrofit2.http.GET

interface ApiService {

    //To save time, because I could not find documentation for : https://developers.themoviedb.org/3/trending/get-trending
    @GET("list/1?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovies(): WeeklyItems

}