package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state

sealed class EpisodeDetailState

object EpisodeDetailProgress : EpisodeDetailState()
object EpisodeDetailError : EpisodeDetailState()
class EpisodeDetailResult(val result: EpisodeDetailPresentation) : EpisodeDetailState()
