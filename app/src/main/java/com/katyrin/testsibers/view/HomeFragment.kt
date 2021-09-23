package com.katyrin.testsibers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingData
import com.katyrin.testsibers.R
import com.katyrin.testsibers.databinding.FragmentHomeBinding
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.utils.toast
import com.katyrin.testsibers.view.adapter.HomeAdapter
import com.katyrin.testsibers.viewmodel.AppState
import com.katyrin.testsibers.viewmodel.ErrorState
import com.katyrin.testsibers.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var binding: FragmentHomeBinding? = null
    private var navController: NavController? = null

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
        binding?.recyclerView?.adapter = HomeAdapter()
        viewModel.getListPokemon()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> updateList(appState.pokemonList)
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

    private fun updateList(pokemonList: PagingData<Pokemon>): Unit =
        (binding?.recyclerView?.adapter as HomeAdapter).submitData(lifecycle, pokemonList)

    override fun onDestroyView() {
        binding = null
        navController = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}