package com.example.veo.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.veo.databinding.FragmentMovieDetailBinding
import com.example.veo.repository.ApiHelperImpl
import com.example.veo.repository.RetrofitBuilder
import com.example.veo.ui.state.UiState
import com.example.veo.utils.DefaultDispatcherProvider
import com.example.veo.utils.ViewModelFactory
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel
    private var movieId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        val bundle = arguments
        bundle?.let {
            val args = MovieDetailFragmentArgs.fromBundle(bundle)
            movieId = args.bundleId
            Log.d("xxx", "args: $args")
        }
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getMovie(movieId)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        //hiding-showing should be as in MovieListFragment
                        is UiState.Success -> {
                            binding.textViewUrl.text = it.data.homepage
                        }
                        is UiState.Loading -> {
                        }
                        is UiState.Error -> {
                            Toast.makeText(
                                requireActivity(),
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DefaultDispatcherProvider()
            )
        )[MovieDetailViewModel::class.java]
    }

}