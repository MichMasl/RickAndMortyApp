package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.screen

import ru.mihmas.rickandmortyapp.feature_list.episode.domain.model.EpisodeModel
import javax.inject.Inject

sealed class EpisodeState

object Progress : EpisodeState()
object Error : EpisodeState()
object NoResult : EpisodeState()
class Result @Inject constructor(val result: List<EpisodeModel>) : EpisodeState()