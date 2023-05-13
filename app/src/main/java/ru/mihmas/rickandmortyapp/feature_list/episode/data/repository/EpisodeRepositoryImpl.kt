package ru.mihmas.rickandmortyapp.feature_list.episode.data.repository

import ru.mihmas.rickandmortyapp.feature_list.episode.data.datasource.EpisodeListProvider
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeListProvider: EpisodeListProvider
) : EpisodeRepository {
    override suspend fun getListOfEpisodes(
        name: String,
        episode: String
    ): List<EpisodeModel> {
        return episodeListProvider.getListOfEpisodes(
            name = name,
            episode = episode
        )
    }

    override fun refreshListOfEpisodes() {
        episodeListProvider.refreshListOfEpisodes()
    }
}