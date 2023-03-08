package com.example.veo.repository

import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override fun getMovies() = flow { emit(apiService.getMovies()) }
    override fun getMovieDetails() = flow { emit(apiService.getMovieDetails()) }
}