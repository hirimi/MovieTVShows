package com.example.sub33.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sub33.databinding.ItemRowBinding
import com.example.sub33.entity.MovieEntity

class MovieFavoriteAdapter: PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavoriteViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class MovieFavoriteViewHolder(private val itemRowBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(movie: MovieEntity) {
            with(itemRowBinding) {
                tvTitle.text = movie.title

                Glide
                    .with(itemView.context)
                    .load("$BASE_URL${movie.poster}")
                    .transform(RoundedCorners(28))
                    .into(imgPoster)


                itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie.id.toString()) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }



    companion object {
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}