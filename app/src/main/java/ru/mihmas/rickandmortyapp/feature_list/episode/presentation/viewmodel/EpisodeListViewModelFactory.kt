package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase.GetListOfEpisodesUseCase
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase.RefreshListOfEpisodesUseCase
import javax.inject.Inject

class EpisodeListViewModelFactory @Inject constructor(
    private val getListOfEpisodesUseCase: GetListOfEpisodesUseCase,
    private val refreshListOfEpisodesUseCase: RefreshListOfEpisodesUseCase,
    private val episodeQuery: EpisodeQuery
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == EpisodeListViewModel::class.java) {
            return EpisodeListViewModel(
                getListOfEpisodesUseCase = getListOfEpisodesUseCase,
                refreshListOfEpisodesUseCase = refreshListOfEpisodesUseCase,
                episodeQuery = episodeQuery
            ) as T
        } else {
            throw RuntimeException("Unknown ViewModel class $modelClass")
        }
    }
}