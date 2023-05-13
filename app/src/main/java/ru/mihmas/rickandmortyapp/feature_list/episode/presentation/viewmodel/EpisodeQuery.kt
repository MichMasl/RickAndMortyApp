package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.viewmodel

import javax.inject.Inject

class EpisodeQuery @Inject constructor() {
    var name = ""
    var episode = ""

    fun setAllToBlank() {
        name = ""
        episode = ""
    }
}