package ru.mihmas.rickandmortyapp.feature_list.episode.data.model

data class EpisodeRemoteResponse(
    val info: EpisodeResponseInfo,
    val results: List<EpisodeModelRemote>
)