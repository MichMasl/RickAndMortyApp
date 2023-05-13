package ru.mihmas.rickandmortyapp.feature_caching.episode.data.repository

import ru.mihmas.rickandmortyapp.feature_caching.episode.data.local.EpisodeDao
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.mapper.EpisodeLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.repository.EpisodeCacheRepository
import javax.inject.Inject

class EpisodeCacheRepositoryImpl @Inject constructor(
    private val dao: EpisodeDao
): EpisodeCacheRepository {
    override suspend fun getAllEpisodes(): List<CachedEpisode> {
        val localList = dao.getAllEpisodes()
        return EpisodeLocalMapper(localList).map()
    }

    override suspend fun findEpisode(name: String): List<CachedEpisode> {
        val localList = dao.findEpisode(name)
        return EpisodeLocalMapper(localList).map()
    }

    override suspend fun filterEpisodes(episode: String): List<CachedEpisode> {
        val localList = dao.filterEpisodes(episode)
        return EpisodeLocalMapper(localList).map()
    }
}