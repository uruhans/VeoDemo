package com.example.veo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.veo.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.apply {
                lifecycleOwner = viewLifecycleOwner
            }.root
    }
}