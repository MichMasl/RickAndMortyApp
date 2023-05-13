package ru.mihmas.rickandmortyapp.feature_list.character.data.datasource

import ru.mihmas.rickandmortyapp.feature_caching.character.data.local.CharacterDao
import ru.mihmas.rickandmortyapp.feature_list.character.data.mapper.CharacterRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.character.data.mapper.CharacterRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterModelRemote
import ru.mihmas.rickandmortyapp.feature_list.character.data.remote.CharacterListApi
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterListProvider @Inject constructor(
    private val api: CharacterListApi,
    private val dao: CharacterDao
) {
    private val characterList = mutableListOf<CharacterModelRemote>()
    private var nextPage: String? = DEFAULT_VALUE
    private var page = FIRST_PAGE

    suspend fun getListOfCharacters(
        name: String,
        species: String,
        type: String,
        status: String,
        gender: String
    ) : List<CharacterModel> {
        if (nextPage != null) {
            val response = api.getListOfCharacters(
                page = page,
                name = name,
                species = species,
                type = type,
                status = status,
                gender = gender
            )
            nextPage = response.info.next
            characterList.addAll(response.results)
            page++
        }
        dao.putListOfCharacters(CharacterRemoteToLocalMapper(characterList).map())
        return CharacterRemoteToDomainMapper(characterList).map()
    }

    fun refreshListOfCharacters() {
        characterList.clear()
        nextPage = DEFAULT_VALUE
        page = FIRST_PAGE
    }

    companion object {
        private const val DEFAULT_VALUE = "defaultValue"
        private const val FIRST_PAGE = 1
    }
}