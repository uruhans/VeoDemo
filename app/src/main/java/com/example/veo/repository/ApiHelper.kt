package com.example.veo.repository

import com.example.veo.model.MovieDetail
import com.example.veo.model.WeeklyItems
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getMovies(): Flow<WeeklyItems>
    fun getMovieDetails(): Flow<MovieDetail>
}