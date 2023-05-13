package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode

object CachedEpisodeDiffUtil : DiffUtil.ItemCallback<CachedEpisode>() {
    override fun areItemsTheSame(oldItem: CachedEpisode, newItem: CachedEpisode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CachedEpisode, newItem: CachedEpisode): Boolean {
        return oldItem == newItem
    }
}