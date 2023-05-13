package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.repository

import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter

interface CharacterCacheRepository {
    suspend fun getAllCharacters() : List<CachedCharacter>

    suspend fun findCharacter(name: String) : List<CachedCharacter>

    suspend fun filterCharacters(species: String, type: String, status: String, gender: String) : List<CachedCharacter>
}