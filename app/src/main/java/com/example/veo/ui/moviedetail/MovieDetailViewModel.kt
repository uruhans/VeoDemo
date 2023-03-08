package com.example.veo.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veo.model.MovieDetail
import com.example.veo.repository.ApiHelper
import com.example.veo.ui.state.UiState
import com.example.veo.utils.DispatcherProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val apiHelper: ApiHelper,
    val dispatcherProvider: DispatcherProvider
) : ViewModel(
) {
    private val _uiState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetail>> = _uiState

    fun getMovie(id: Int) {
        getMovieDetail(id)
    }

    private fun getMovieDetail(id: Int) {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading

            apiHelper.getMovieDetails(id)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .onCompletion {
                    //_uiState.value = UiState.Success(emptyList())
                }
                .collect {
                    val detail = it
                    _uiState.value = UiState.Success(detail)
                }
        }
    }
}