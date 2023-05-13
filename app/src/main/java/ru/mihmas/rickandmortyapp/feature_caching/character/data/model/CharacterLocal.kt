package ru.mihmas.rickandmortyapp.feature_caching.character.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val species: String,
    val type: String,
    val status: String,
    val gender: String,
    val image: String
)