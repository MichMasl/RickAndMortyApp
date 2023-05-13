package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state

import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail

data class EpisodeDetailPresentation(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<EpisodeCharacterDetail>
)
