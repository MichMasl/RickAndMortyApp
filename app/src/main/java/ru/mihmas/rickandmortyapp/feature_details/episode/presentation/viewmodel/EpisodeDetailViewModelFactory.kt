package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase.GetEpisodeDetailsUseCase
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase.GetListOfEpisodeCharacterUseCase
import javax.inject.Inject

class EpisodeDetailViewModelFactory @Inject constructor(
    private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val getListOfEpisodeCharacterUseCase: GetListOfEpisodeCharacterUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == EpisodeDetailViewModel::class.java) {
            return EpisodeDetailViewModel(
                getEpisodeDetailsUseCase = getEpisodeDetailsUseCase,
                getListOfEpisodeCharacterUseCase = getListOfEpisodeCharacterUseCase
            ) as T
        } else throw RuntimeException("Unknown view-model $modelClass")
    }
}