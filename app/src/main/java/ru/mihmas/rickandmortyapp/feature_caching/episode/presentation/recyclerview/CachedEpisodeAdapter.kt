package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode
import javax.inject.Inject

class CachedEpisodeAdapter @Inject constructor(): ListAdapter<CachedEpisode, CachedEpisodeViewHolder>(
    CachedEpisodeDiffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CachedEpisodeViewHolder {
        val bind = ItemEpisodeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CachedEpisodeViewHolder(bind = bind)
    }

    override fun onBindViewHolder(holder: CachedEpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.initViewHolder(item = item)
    }
}