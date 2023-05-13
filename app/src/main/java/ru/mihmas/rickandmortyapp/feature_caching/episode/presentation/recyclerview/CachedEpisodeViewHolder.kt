package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode
import javax.inject.Inject

class CachedEpisodeViewHolder @Inject constructor(private val bind: ItemEpisodeListBinding) : RecyclerView.ViewHolder(bind.root) {

    fun initViewHolder(item: CachedEpisode) {
        bind.nameEpisode.text = item.name
        bind.numberEpisode.text = item.episode
        bind.airDateEpisode.text = item.airDate
    }
}