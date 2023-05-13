package ru.mihmas.rickandmortyapp.feature_list.episode.domain.repository

import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel

interface EpisodeRepository {
    suspend fun getListOfEpisodes(
        name: String,
        episode: String
    ) : List<EpisodeModel>

    fun refreshListOfEpisodes()
}