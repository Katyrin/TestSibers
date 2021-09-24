package com.katyrin.testsibers.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testsibers.R
import com.katyrin.testsibers.databinding.ItemLoadingStateBinding


class LoadingStateAdapter(
    private val adapter: HomeAdapter
) : LoadStateAdapter<LoadingStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateAdapter.NetworkStateItemViewHolder =
        NetworkStateItemViewHolder(
            ItemLoadingStateBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading_state, parent, false)
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState): Unit =
        holder.bind(loadState)

    inner class NetworkStateItemViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState): Unit = with(binding) {
            retryButton.setOnClickListener { retryCallback() }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }
}