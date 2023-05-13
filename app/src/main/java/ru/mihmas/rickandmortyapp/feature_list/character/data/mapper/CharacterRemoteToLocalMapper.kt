package ru.mihmas.rickandmortyapp.feature_list.character.data.mapper

import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterModelRemote
import javax.inject.Inject

class CharacterRemoteToLocalMapper @Inject constructor(private val remoteList: List<CharacterModelRemote>) {
    fun map(): List<CharacterLocal> {
        val list = mutableListOf<CharacterLocal>()
        for (item in remoteList) {
            list.add(
                CharacterLocal(
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