package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.repository

import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode

interface EpisodeCacheRepository {
    suspend fun getAllEpisodes() : List<CachedEpisode>

    suspend fun findEpisode(name: String) : List<CachedEpisode>

    suspend fun filterEpisodes(episode: String) : List<CachedEpisode>
}