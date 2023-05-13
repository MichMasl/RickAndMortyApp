package ru.mihmas.rickandmortyapp.feature_details.character.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase.GetCharacterDetailsUseCase;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase.GetListOfCharacterEpisodeUseCase;

public class CharacterDetailViewModelFactory implements ViewModelProvider.Factory {

    private final GetCharacterDetailsUseCase getCharacterDetailsUseCase;
    private final GetListOfCharacterEpisodeUseCase getListOfCharacterEpisodeUseCase;

    @Inject
    public CharacterDetailViewModelFactory(
            GetCharacterDetailsUseCase getCharacterDetailsUseCase,
            GetListOfCharacterEpisodeUseCase getListOfCharacterEpisodeUseCase) {
        this.getCharacterDetailsUseCase = getCharacterDetailsUseCase;
        this.getListOfCharacterEpisodeUseCase = getListOfCharacterEpisodeUseCase;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CharacterDetailViewModel.class) {
            return (T) new CharacterDetailViewModel(
                    getListOfCharacterEpisodeUseCase,
                    getCharacterDetailsUseCase
            );
        } else {
            throw new RuntimeException("Unknown view-model class " + modelClass);
        }
    }
}
