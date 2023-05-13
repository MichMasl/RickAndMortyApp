package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import javax.inject.Inject

class EpisodeViewHolder @Inject constructor(
    private val bind: ItemEpisodeListBinding
) : RecyclerView.ViewHolder(bind.root) {
    private var id = EpisodeAdapter.UNKNOWN_ID

    fun initViewHolder(item: EpisodeModel) {
        id = item.id
        bind.nameEpisode.text = item.name
        bind.numberEpisode.text = item.episode
        bind.airDateEpisode.text = item.airDate
    }

    fun getId(): Int = id
}