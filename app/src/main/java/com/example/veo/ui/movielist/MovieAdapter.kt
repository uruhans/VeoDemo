package com.example.veo.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.veo.R
import com.example.veo.databinding.MovieItemLayoutBinding
import com.example.veo.model.TmDbItem

class MovieAdapter(
    private val movies: ArrayList<TmDbItem>,
    private val onClick: (TmDbItem) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: MovieItemLayoutBinding, val onClick: (TmDbItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentMovie: TmDbItem? = null

        init {
            itemView.setOnClickListener {
                currentMovie?.let {
                    onClick(it)
                }
            }
        }

        fun bind(tmDbItem: TmDbItem) {
            currentMovie = tmDbItem
            binding.textViewTitle.text =
                if (tmDbItem.originalTitle.isNullOrEmpty()) binding.imageViewBackDrop.context.getString(
                    R.string.empty_title
                ) else tmDbItem.originalTitle
            tmDbItem.backdropPath?.let {
                Glide.with(binding.imageViewBackDrop.context)
                    .load("$BASE_IMAGE_URL${tmDbItem.backdropPath}")
                    .into(binding.imageViewBackDrop)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position])

    fun addData(list: List<TmDbItem>) {
        movies.addAll(list)
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }
}
