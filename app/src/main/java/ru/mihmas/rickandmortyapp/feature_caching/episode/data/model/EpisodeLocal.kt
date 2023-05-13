package ru.mihmas.rickandmortyapp.feature_caching.episode.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EpisodeLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String
)