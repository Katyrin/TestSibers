package com.katyrin.testsibers.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testsibers.databinding.ItemPokemonBinding
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.utils.loadPokemonImage

class HomeAdapter(
    private val onClick: (PokemonDTO) -> Unit
) : PagingDataAdapter<PokemonDTO, HomeAdapter.HomeViewHolder>(DiffUtilCallBack) {

    private object DiffUtilCallBack : DiffUtil.ItemCallback<PokemonDTO>() {
        override fun areItemsTheSame(oldItem: PokemonDTO, newItem: PokemonDTO): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PokemonDTO, newItem: PokemonDTO): Boolean =
            oldItem == newItem
    }

    inner class HomeViewHolder(
        private val itemBinding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pokemonDTO: PokemonDTO) {
            itemBinding.nameTextView.text = pokemonDTO.name
            itemBinding.previewImage.loadPokemonImage(pokemonDTO.imageUrl)
            itemBinding.root.setOnClickListener { onClick(pokemonDTO) }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
}