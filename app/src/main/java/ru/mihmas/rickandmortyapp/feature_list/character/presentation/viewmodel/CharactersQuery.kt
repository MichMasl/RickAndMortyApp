package ru.mihmas.rickandmortyapp.feature_list.character.presentation.viewmodel

import javax.inject.Inject

class CharactersQuery @Inject constructor() {
    var name = ""
    var species = ""
    var type = ""
    var status = ""
    var gender = ""

    fun setAllToBlank() {
        name = ""
        species = ""
        type = ""
        status = ""
        gender = ""
    }
}