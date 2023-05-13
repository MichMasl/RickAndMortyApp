package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel

object EpisodeDiffUtil : DiffUtil.ItemCallback<EpisodeModel>() {
    override fun areItemsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
        return oldItem == newItem
    }
}