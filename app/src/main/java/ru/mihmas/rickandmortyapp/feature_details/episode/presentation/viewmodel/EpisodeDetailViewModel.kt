package ru.mihmas.rickandmortyapp.feature_details.episode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.model.EpisodeDetail
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase.GetEpisodeDetailsUseCase
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.usecase.GetListOfEpisodeCharacterUseCase
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.state.*
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val getListOfEpisodeCharacterUseCase: GetListOfEpisodeCharacterUseCase
): ViewModel() {

    private val _state = MutableLiveData<EpisodeDetailState>()
    val state: LiveData<EpisodeDetailState> = _state

    fun getEpisodeDetails(episodeId: Int) {
        _state.value = EpisodeDetailProgress
        viewModelScope.launch {
            try {
                val episodeDetail = withContext(Dispatchers.IO) {
                    getEpisodeDetailsUseCase.getEpisodeDetails(id = episodeId)
                }
                val listOfId = formStringOfCharacterId(listOfUrl = episodeDetail.characters)
                val listOfEpisodeCharacter = withContext(Dispatchers.IO) {
                    getListOfEpisodeCharacterUseCase.getListOfEpisodeCharacter(list = listOfId)
                }
                val result = episodeDetailPresentationAssembler(
                    episodeDetail = episodeDetail,
                    listOfEpisodeCharacter = listOfEpisodeCharacter
                )
                _state.value = EpisodeDetailResult(result = result)
            } catch (e: Exception) {
                _state.value = EpisodeDetailError
            }
        }
    }

    private fun formStringOfCharacterId(listOfUrl: List<String>) : String {
        val listOfId = mutableListOf<String>()
        for (url in listOfUrl) {
            val id = url.substring(PARSED_ID)
            listOfId.add(id)
        }
        return listOfId.joinToString(separator = ",", postfix = ",")
    }

    private fun episodeDetailPresentationAssembler(
        episodeDetail: EpisodeDetail,
        listOfEpisodeCharacter: List<EpisodeCharacterDetail>
    ) : EpisodeDetailPresentation = EpisodeDetailPresentation(
        id = episodeDetail.id,
        name = episodeDetail.name,
        airDate = episodeDetail.airDate,
        episode = episodeDetail.episode,
        characters = listOfEpisodeCharacter
    )

    companion object {
        private const val PARSED_ID = 42
    }
}