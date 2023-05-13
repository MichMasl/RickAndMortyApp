package ru.mihmas.rickandmortyapp.feature_list.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_list.character.domain.usecase.*
import javax.inject.Inject

class CharacterListViewModelFactory @Inject constructor(
    private val refreshListOfCharactersUseCase: RefreshListOfCharactersUseCase,
    private val getListOfCharactersUseCase: GetListOfCharactersUseCase,
    private val charactersQuery: CharactersQuery
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == CharacterListViewModel::class.java) {
            return CharacterListViewModel(
                refreshListOfCharactersUseCase = refreshListOfCharactersUseCase,
                getListOfCharactersUseCase = getListOfCharactersUseCase,
                charactersQuery = charactersQuery
            ) as T
        } else {
            throw RuntimeException("Unknown ViewModel class $modelClass")
        }
    }
}