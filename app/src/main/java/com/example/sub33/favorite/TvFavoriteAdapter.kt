package com.example.sub33.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sub33.databinding.ItemRowBinding
import com.example.sub33.entity.TvShowsEntity

class TvFavoriteAdapter: PagedListAdapter<TvShowsEntity, TvFavoriteAdapter.TvFavoriteViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvFavoriteViewHolder {
        val itemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvFavoriteViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: TvFavoriteViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class TvFavoriteViewHolder(private val itemRowBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(tv: TvShowsEntity) {
            with(itemRowBinding) {
                tvTitle.text = tv.title

                Glide
                    .with(itemView.context)
                    .load("$BASE_URL${tv.poster}")
                    .transform(RoundedCorners(28))
                    .into(imgPoster)


                itemView.setOnClickListener { onItemClickCallback.onItemClicked(tv.id.toString()) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }



    companion object {
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}