package ru.mihmas.rickandmortyapp.feature_caching.character.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model.CachedCharacter
import javax.inject.Inject

class CharacterLocalMapper @Inject constructor(private val localList: List<CharacterLocal>) {
    fun map(): List<CachedCharacter> {
        val list = mutableListOf<CachedCharacter>()
        for (item in localList) {
            list.add(
                CachedCharacter(
                    id = item.id,
                    name = item.name,
                    species = item.species,
                    type = item.type,
                    status = item.status,
                    gender = item.gender,
                    image = item.image
                )
            )
        }
        return list
    }
}