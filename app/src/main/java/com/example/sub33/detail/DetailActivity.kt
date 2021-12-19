package com.example.sub33.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.example.sub33.R
import com.example.sub33.databinding.ActivityDetailBinding
import com.example.sub33.databinding.ActivityDetailBinding.inflate
import com.example.sub33.detail.DetailViewModel.Companion.MOVIES
import com.example.sub33.detail.DetailViewModel.Companion.TV_SHOWS
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.viewmodel.ViewModelFactory
import com.example.sub33.vo.Status

class DetailActivity: AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
        const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
    private var category: String? = null
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        activityDetailBinding.fabAddToFavorite.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            category = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && category != null) {
                detailViewModel.setAll(dataId, category.toString())

                if (category == MOVIES) {
                    detailViewModel.getDetailMovie().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    setDataMovieDetail(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else if (category == TV_SHOWS) {
                    detailViewModel.getDetailTvShow().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    setDataTvDetail(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }
        setupState()
    }
    private fun showProgressBar(state: Boolean) {
        activityDetailBinding.progressBar.isVisible = state
        activityDetailBinding.appbar.isInvisible = state
        activityDetailBinding.nestedScrollView.isInvisible = state
        activityDetailBinding.fabAddToFavorite.isInvisible = state
    }
    private fun setDataMovieDetail(entityOfDetails: MovieEntity) {
        activityDetailBinding.apply {
            entityOfDetails.apply {
                Glide
                    .with(this@DetailActivity)
                    .load("$BASE_URL$poster")
                    .transform(RoundedCorners(36))
                    .apply(placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(profileMovie)
                tvTitle.text = title
                tvRatingOverview.text = rating.toString()
                tvReleaseOverview.text = releaseDate
                tvDescriptionOverview.text = synopsis
            }
        }
    }
    private fun setDataTvDetail(entityOfDetails: TvShowsEntity) {
        activityDetailBinding.apply {
            entityOfDetails.apply {
                Glide
                    .with(this@DetailActivity)
                    .load("$BASE_URL$poster")
                    .transform(RoundedCorners(36))
                    .apply(placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(profileMovie)
                tvTitle.text = title
                tvRatingOverview.text = rating.toString()
                tvReleaseOverview.text = releaseDate
                tvDescriptionOverview.text = synopsis
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_add_to_favorite -> {
                onFabClicked()
            }
        }
    }

    private fun onFabClicked() {
        if (category == MOVIES) {
            detailViewModel.setFavoriteMovie()
        } else if (category == TV_SHOWS) {
            detailViewModel.setFavoriteTvShow()
        }
    }

    private fun setupState() {
        if (category == MOVIES) {
            detailViewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.movielist
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (category == TV_SHOWS) {
            detailViewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.tvlist
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
    private fun setFavoriteState(state: Boolean) {
        val fab = activityDetailBinding.fabAddToFavorite
        if (state) {
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}