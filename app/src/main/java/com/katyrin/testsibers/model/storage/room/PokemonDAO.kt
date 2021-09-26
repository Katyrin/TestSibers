package com.katyrin.testsibers.model.storage.room

 import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.katyrin.testsibers.model.entities.PokemonEntity

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM PokemonEntity ORDER BY id")
    suspend fun getPokemonList(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity ORDER BY attack")
    suspend fun getPokemonAttackList(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity ORDER BY defense")
    suspend fun getPokemonDefenseList(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity ORDER BY hp")
    suspend fun getPokemonHPList(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putPokemonList(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE name LIKE :name")
    suspend fun getPokemonByName(name: String): PokemonEntity?
}