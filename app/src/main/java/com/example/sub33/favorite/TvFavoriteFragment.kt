package com.example.sub33.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub33.databinding.FragmentTvFavoriteBinding
import com.example.sub33.detail.DetailActivity
import com.example.sub33.detail.DetailViewModel.Companion.TV_SHOWS
import com.example.sub33.viewmodel.ViewModelFactory

class TvFavoriteFragment : Fragment(), TvFavoriteAdapter.OnItemClickCallback {

    private var _fragmentTvFavoriteBinding: FragmentTvFavoriteBinding? = null
    private val binding get() = _fragmentTvFavoriteBinding

    private lateinit var viewModel: TvFavoriteViewModel
    private lateinit var adapter: TvFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTvFavoriteBinding = FragmentTvFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                adapter.submitList(favTvShow)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[TvFavoriteViewModel::class.java]

            adapter = TvFavoriteAdapter()
            adapter.setOnItemClickCallback(this)

            viewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow != null) {
                    adapter.submitList(favTvShow)
                }
            })
            with(binding?.rvTvFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.adapter = adapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOWS)

        context?.startActivity(intent)
    }
}