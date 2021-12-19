package com.example.sub33.tabmovie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub33.databinding.FragmentMovieBinding
import com.example.sub33.detail.DetailActivity
import com.example.sub33.detail.DetailViewModel.Companion.MOVIES
import com.example.sub33.viewmodel.ViewModelFactory
import com.example.sub33.vo.Status

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            viewModel.getMovie().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> showProgressBar(true)

                        Status.SUCCESS -> {
                            showProgressBar(false)
                            movieAdapter.submitList(movie.data)
                            movieAdapter.setOnItemClickCallback(this)
                        }

                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            with(fragmentMovieBinding.rvMovie) {
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = movieAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIES)

        context?.startActivity(intent)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressMovies.isVisible = state
        fragmentMovieBinding.rvMovie.isInvisible = state
    }
}