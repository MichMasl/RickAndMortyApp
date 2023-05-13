package ru.mihmas.rickandmortyapp.feature_details.location.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase.GetListOfLocationCharacterUseCase;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase.GetLocationDetailsUseCase;

public class LocationDetailViewModelFactory implements ViewModelProvider.Factory {
    private final GetListOfLocationCharacterUseCase getListOfLocationCharacterUseCase;
    private final GetLocationDetailsUseCase getLocationDetailsUseCase;

    @Inject
    public LocationDetailViewModelFactory(
            GetListOfLocationCharacterUseCase getListOfLocationCharacterUseCase,
            GetLocationDetailsUseCase getLocationDetailsUseCase
    ) {
        this.getListOfLocationCharacterUseCase = getListOfLocationCharacterUseCase;
        this.getLocationDetailsUseCase = getLocationDetailsUseCase;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LocationDetailViewModel.class) {
            return (T) new LocationDetailViewModel(
                    getListOfLocationCharacterUseCase,
                    getLocationDetailsUseCase
            );
        } else {
            throw new RuntimeException("Unknown view-model class " + modelClass);
        }
    }
}
