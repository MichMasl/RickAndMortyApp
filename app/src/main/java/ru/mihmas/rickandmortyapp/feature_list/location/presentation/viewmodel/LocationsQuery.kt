package ru.mihmas.rickandmortyapp.feature_list.location.presentation.viewmodel

import javax.inject.Inject

class LocationsQuery @Inject constructor() {
    var name = ""
    var type = ""
    var dimension = ""

    fun setAllToBlank() {
        name = ""
        type = ""
        dimension = ""
    }
}