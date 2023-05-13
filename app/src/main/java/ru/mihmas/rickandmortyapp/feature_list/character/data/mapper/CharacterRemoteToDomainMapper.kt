package ru.mihmas.rickandmortyapp.feature_list.character.data.mapper

import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterModelRemote
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import javax.inject.Inject

class CharacterRemoteToDomainMapper @Inject constructor(private val remoteList: List<CharacterModelRemote>) {
    fun map(): List<CharacterModel> {
        val list = mutableListOf<CharacterModel>()
        for (item in remoteList) {
            list.add(
                CharacterModel(
                    id = item.id,
                    name = item.name,
                    species = item.species,
                    status = item.status,
                    gender = item.gender,
                    image = item.image
                )
            )
        }
        return list
    }
}