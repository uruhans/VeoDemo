package com.example.veo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.veo.repository.ApiHelper
import com.example.veo.ui.moviedetail.MovieDetailViewModel
import com.example.veo.ui.movielist.MovieListViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(apiHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(apiHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}