package com.example.sub33.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub33.R
import com.example.sub33.databinding.FragmentMovieFavoriteBinding
import com.example.sub33.detail.DetailActivity
import com.example.sub33.detail.DetailViewModel.Companion.MOVIES
import com.example.sub33.viewmodel.ViewModelFactory

class MovieFavoriteFragment :Fragment(), MovieFavoriteAdapter.OnItemClickCallback {

    private var _fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _fragmentMovieFavoriteBinding

    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavMovies().observe(viewLifecycleOwner, { favMovieShow ->
            if (favMovieShow != null) {
                adapter.submitList(favMovieShow)
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

            adapter = MovieFavoriteAdapter()
            adapter.setOnItemClickCallback(this)

            viewModel.getFavMovies().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow != null) {
                    adapter.submitList(favTvShow)
                }
            })
            with(binding?.rvMovieFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.adapter = adapter
            }
        }
    }
    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIES)

        context?.startActivity(intent)
    }
}