package com.katyrin.testsibers.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testsibers.databinding.ItemPokemonBinding
import com.katyrin.testsibers.model.entities.Pokemon
import com.squareup.picasso.Picasso

class HomeAdapter : PagingDataAdapter<Pokemon, HomeAdapter.HomeViewHolder>(DiffUtilCallBack) {

    private object DiffUtilCallBack : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem
    }

    inner class HomeViewHolder(
        private val itemBinding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pokemon: Pokemon) {
            itemBinding.nameTextView.text = pokemon.name
            val image = BASE_URL + pokemon.url + PNG
            Picasso.get().load(image).into(itemBinding.previewImage)
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    private companion object {
        const val BASE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val PNG = ".png"
    }
}