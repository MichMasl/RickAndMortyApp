package ru.mihmas.rickandmortyapp.feature_list.episode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.screen.*
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase.GetListOfEpisodesUseCase
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.usecase.RefreshListOfEpisodesUseCase
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(
    private val getListOfEpisodesUseCase: GetListOfEpisodesUseCase,
    private val refreshListOfEpisodesUseCase: RefreshListOfEpisodesUseCase,
    private val episodeQuery: EpisodeQuery
) : ViewModel() {

    private val _episodeState = MutableLiveData<EpisodeState>()
    val episodeState: LiveData<EpisodeState> = _episodeState

    init {
        getListOfEpisodes()
    }

    fun getListOfEpisodes() {
        _episodeState.value = Progress
        viewModelScope.launch {
            try {
                val result = getListOfEpisodesUseCase.getListOfEpisodesUseCase(
                    name = episodeQuery.name,
                    episode = episodeQuery.episode
                )
                _episodeState.value = Result(result = result)
            } catch (e: HttpException) {
                _episodeState.value = NoResult
            } catch (e: Exception) {
                e.printStackTrace()
                _episodeState.value = Error
            }
        }
    }

    fun findEpisodesByName(name: String) {
        refreshListOfEpisodesUseCase.refreshListOfEpisodes()
        episodeQuery.setAllToBlank()
        episodeQuery.name = name.trim()
        getListOfEpisodes()
    }

    fun filterEpisodes(episode: String) {
        refreshListOfEpisodesUseCase.refreshListOfEpisodes()
        episodeQuery.setAllToBlank()
        episodeQuery.episode = episode.trim()
        getListOfEpisodes()
    }

    fun refreshBundleOfEpisodes() {
        _episodeState.value = Progress
        refreshListOfEpisodesUseCase.refreshListOfEpisodes()
        episodeQuery.setAllToBlank()
        getListOfEpisodes()
    }
}