package com.katyrin.testsibers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.katyrin.testsibers.R
import com.katyrin.testsibers.bus.EventBus
import com.katyrin.testsibers.databinding.FragmentHomeBinding
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.utils.FIRST_ITEM
import com.katyrin.testsibers.utils.toast
import com.katyrin.testsibers.view.adapter.HomeAdapter
import com.katyrin.testsibers.view.adapter.LoadingStateAdapter
import com.katyrin.testsibers.viewmodel.AppState
import com.katyrin.testsibers.viewmodel.ErrorState
import com.katyrin.testsibers.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var isChecked: Boolean = false
    private val viewModel: HomeViewModel by viewModel()
    private var binding: FragmentHomeBinding? = null
    private var navController: NavController? = null
    private val adapter by lazy {
        HomeAdapter { pokemonDTO ->
            val navDirections = HomeFragmentDirections.actionHomeFragmentToInfoFragment(pokemonDTO)
            navController?.navigate(navDirections)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.main_container)
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        initViews()
        observeBus()
    }

    private fun initViews() {
        binding?.apply {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolBar)
            fab.setOnClickListener { viewModel.randomStart() }
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter(adapter),
                footer = LoadingStateAdapter(adapter)
            )
            adapter.addLoadStateListener { state ->
                progressBar.isVisible = state.refresh == LoadState.Loading
                recyclerView.isVisible = state.refresh != LoadState.Loading
            }
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> updateList(appState.pokemonDTOList)
            is AppState.Error -> setErrorState(appState.errorState)
        }
    }

    private fun setErrorState(errorState: ErrorState) {
        when (errorState) {
            is ErrorState.TimOut -> toast(R.string.timeout_error_message)
            is ErrorState.UnknownHost -> toast(R.string.unknown_host_error_message)
            is ErrorState.Connection -> toast(R.string.connection_error_message)
            is ErrorState.Server -> toast(R.string.server_error_message)
            is ErrorState.Unknown -> toast(errorState.message)
        }
    }

    private fun updateList(pokemonDTOList: PagingData<PokemonDTO>) {
        adapter.submitData(lifecycle, pokemonDTOList)
        if (isChecked) binding?.recyclerView?.scrollToPosition(FIRST_ITEM)
        isChecked = false
    }

    // Reaction to filter selection
    private fun observeBus() {
        lifecycleScope.launch {
            EventBus.events.collectLatest {
                viewModel.getListPokemon()
                isChecked = true
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        navController = null
        super.onDestroyView()
    }
}