package com.katyrin.testsibers.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import com.katyrin.testsibers.viewmodel.HomeViewModel
import com.katyrin.testsibers.databinding.FragmentHomeBinding
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.view.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding?.recyclerView?.adapter = HomeAdapter()
    }

    private fun updateList(pokemonList: PagingData<Pokemon>): Unit =
        (binding?.recyclerView?.adapter as HomeAdapter).submitData(lifecycle, pokemonList)

    companion object {
        fun newInstance() = HomeFragment()
    }
}