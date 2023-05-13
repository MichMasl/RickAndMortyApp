package ru.mihmas.rickandmortyapp.feature_details.character.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail

interface CharacterDetailRepository {
    fun getCharacterDetails(id: Int) : Single<CharacterDetail>
    fun getMultipleEpisodes(list: String): Single<List<CharacterEpisodeDetail>>
}