package com.example.veo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.veo.repository.ApiHelper
import com.example.veo.ui.MovieListViewModel

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
        throw IllegalArgumentException("Unknown class name")
    }

}