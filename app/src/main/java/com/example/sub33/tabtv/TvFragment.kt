package com.example.sub33.tabtv

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
import com.example.sub33.R
import com.example.sub33.databinding.FragmentTvBinding
import com.example.sub33.detail.DetailActivity
import com.example.sub33.detail.DetailViewModel.Companion.TV_SHOWS
import com.example.sub33.viewmodel.ViewModelFactory
import com.example.sub33.vo.Status

class TvFragment : Fragment(), TvAdapter.OnItemClickCallback {
    private lateinit var fragmentTvBinding: FragmentTvBinding
    private lateinit var viewModel: TvViewModel
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            tvAdapter = TvAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
                .also {
                    it.getTvShows().observe(viewLifecycleOwner, { tv ->
                        if (tv != null) {
                            when (tv.status) {
                                Status.LOADING -> {
                                    showProgressBar(true)
                                }
                                Status.SUCCESS -> {
                                    showProgressBar(false)
                                    tvAdapter.submitList(tv.data)
                                    tvAdapter.setOnItemClickCallback(this)
                                }
                                Status.ERROR -> {
                                    showProgressBar(false)
                                    Toast.makeText(
                                        context,
                                        resources.getString(R.string.failed_load),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
                }

            fragmentTvBinding.rvTv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = tvAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOWS)

        context?.startActivity(intent)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvBinding.progressTvShows.isVisible = state
        fragmentTvBinding.rvTv.isInvisible = state
    }
}