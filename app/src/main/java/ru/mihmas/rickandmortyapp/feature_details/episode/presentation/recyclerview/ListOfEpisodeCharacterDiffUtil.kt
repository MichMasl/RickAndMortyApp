package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail

object ListOfEpisodeCharacterDiffUtil : DiffUtil.ItemCallback<EpisodeCharacterDetail>() {
    override fun areItemsTheSame(
        oldItem: EpisodeCharacterDetail,
        newItem: EpisodeCharacterDetail
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EpisodeCharacterDetail,
        newItem: EpisodeCharacterDetail
    ): Boolean {
        return oldItem == newItem
    }
}