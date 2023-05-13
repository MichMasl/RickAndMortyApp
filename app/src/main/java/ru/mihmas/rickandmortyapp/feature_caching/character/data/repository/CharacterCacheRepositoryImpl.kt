package ru.mihmas.rickandmortyapp.feature_caching.character.data.repository

import ru.mihmas.rickandmortyapp.feature_caching.character.data.local.CharacterDao
import ru.mihmas.rickandmortyapp.feature_caching.character.data.mapper.CharacterLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.repository.CharacterCacheRepository
import javax.inject.Inject

class CharacterCacheRepositoryImpl @Inject constructor(
    private val dao: CharacterDao
): CharacterCacheRepository {
    override suspend fun getAllCharacters(): List<CachedCharacter> {
        val localList = dao.getAllCharacters()
        return CharacterLocalMapper(localList = localList).map()
    }

    override suspend fun findCharacter(name: String): List<CachedCharacter> {
        val localList = dao.findCharacter(name = name)
        return CharacterLocalMapper(localList = localList).map()
    }

    override suspend fun filterCharacters(
        species: String,
        type: String,
        status: String,
        gender: String
    ): List<CachedCharacter> {
        val localList = dao.filterCharacters(
            species = species,
            type = type,
            status = status,
            gender = gender
        )
        return CharacterLocalMapper(localList = localList).map()
    }
}