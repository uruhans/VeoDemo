package com.example.veo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.veo.databinding.FragmentMovieListBinding
import com.example.veo.repository.ApiHelperImpl
import com.example.veo.repository.RetrofitBuilder
import com.example.veo.utils.DefaultDispatcherProvider
import com.example.veo.utils.ViewModelFactory

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        //viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.apply {
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DefaultDispatcherProvider()
            )
        )[MovieListViewModel::class.java]
    }
}