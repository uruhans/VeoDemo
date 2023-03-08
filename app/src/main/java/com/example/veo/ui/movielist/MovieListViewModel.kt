package com.example.veo.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veo.model.TmDbItem
import com.example.veo.repository.ApiHelper
import com.example.veo.ui.state.UiState
import com.example.veo.utils.DispatcherProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val apiHelper: ApiHelper,
    val dispatcherProvider: DispatcherProvider
) : ViewModel(
) {
    private val _uiState = MutableStateFlow<UiState<List<TmDbItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TmDbItem>>> = _uiState
    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading

            apiHelper.getMovies()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .onCompletion {
                    //_uiState.value = UiState.Success(emptyList())
                }
                .collect {
                    val weeklyItems = it
                    _uiState.value = UiState.Success(weeklyItems.results)
                }
        }
    }
}