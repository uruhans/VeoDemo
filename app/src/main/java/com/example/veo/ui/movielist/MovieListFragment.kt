package com.example.veo.ui.movielist

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veo.databinding.FragmentMovieListBinding
import com.example.veo.model.TmDbItem
import com.example.veo.repository.ApiHelperImpl
import com.example.veo.repository.RetrofitBuilder
import com.example.veo.ui.state.UiState
import com.example.veo.utils.DefaultDispatcherProvider
import com.example.veo.utils.ViewModelFactory
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.apply {
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        binding.moviesView.layoutManager = LinearLayoutManager(requireContext())
        adapter =
            MovieAdapter(
                arrayListOf()
            ) { movie -> adapterOnClick(movie) }
        binding.moviesView.addItemDecoration(
            DividerItemDecoration(
                binding.moviesView.context,
                (binding.moviesView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.moviesView.adapter = adapter
    }

    private fun adapterOnClick(movie: TmDbItem) {
        Log.d("xxx", "Pressed ${movie.id}")
        movie.id?.let {
            findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(it))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showList(items: List<TmDbItem>) {
        adapter.addData(items)
        adapter.notifyDataSetChanged()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            showList(it.data)
                            binding.moviesView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.moviesView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
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
        )[MovieListViewModel::class.java]
    }
}