package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.screen

import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model.CachedEpisode
import javax.inject.Inject

sealed class EpisodeCacheState

object Progress : EpisodeCacheState()
object Error : EpisodeCacheState()
object NoResult : EpisodeCacheState()
class Result @Inject constructor(val result: List<CachedEpisode>) : EpisodeCacheState()