package ru.mihmas.rickandmortyapp.feature_details.location.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase.GetListOfLocationCharacterUseCase;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.usecase.GetLocationDetailsUseCase;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.state.LocationDetailPresentation;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.state.LocationDetailState;

public class LocationDetailViewModel extends ViewModel {
    private final static int PARSED_EPISODE_ID = 42;

    private final GetListOfLocationCharacterUseCase getListOfLocationCharacterUseCase;
    private final GetLocationDetailsUseCase getLocationDetailsUseCase;
    private final MutableLiveData<LocationDetailState> _state = new MutableLiveData<>();
    private final MutableLiveData<LocationDetailPresentation> _locationDetailLiveData = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LocationDetailViewModel(
            GetListOfLocationCharacterUseCase getListOfLocationCharacterUseCase,
            GetLocationDetailsUseCase getLocationDetailsUseCase
    ) {
        this.getListOfLocationCharacterUseCase = getListOfLocationCharacterUseCase;
        this.getLocationDetailsUseCase = getLocationDetailsUseCase;
    }

    public LiveData<LocationDetailState> state() {
        return _state;
    }

    public LiveData<LocationDetailPresentation> locationDetailLiveData() {
        return _locationDetailLiveData;
    }

    public void getLocationDetails(int locationId) {
        _state.setValue(LocationDetailState.PROGRESS);
        Disposable disposable = getLocationDetailsUseCase.getLocationDetails(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        locationDetail -> {
                            if (locationDetail.getResidents().size() == 0) {
                                _state.setValue(LocationDetailState.NO_CHARACTERS);
                                _locationDetailLiveData.setValue(
                                        locationDetailPresentationAssembler(
                                                locationDetail,
                                                null
                                        )
                                );
                            } else {
                                String ids = formStringOfCharacterId(locationDetail.getResidents());
                                provideLocationDetailsWithListOfCharacters(ids, locationDetail);
                                _state.setValue(LocationDetailState.SUCCESS);
                            }
                        },
                        throwable -> _state.setValue(LocationDetailState.ERROR)
                );
        compositeDisposable.add(disposable);
    }

    private String formStringOfCharacterId(List<String> listOfUrl) {
        final List<String> listOfId = new ArrayList<>();
        for (String url : listOfUrl) {
            String characterId = url.substring(PARSED_EPISODE_ID);
            listOfId.add(characterId);
        }
        String stringOfIds = listOfId.stream().collect(Collectors.joining(
                ",","",","
        ));
        return stringOfIds;
    }

    private void provideLocationDetailsWithListOfCharacters(
            String listOfCharacterId,
            LocationDetail locationDetailWithoutListOfCharacters
    ) {
        Disposable disposable = getListOfLocationCharacterUseCase.getListOfLocationCharacter(listOfCharacterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listOfCharacters -> _locationDetailLiveData.setValue(
                                locationDetailPresentationAssembler(
                                    locationDetailWithoutListOfCharacters,
                                    listOfCharacters
                                )
                        )
                );
        compositeDisposable.add(disposable);
    }

    private LocationDetailPresentation locationDetailPresentationAssembler(
            LocationDetail locationDetail,
            List<LocationCharacterDetail> listOfCharacters
    ) {
        return new LocationDetailPresentation(
                locationDetail.getId(),
                locationDetail.getName(),
                locationDetail.getType(),
                locationDetail.getDimension(),
                listOfCharacters
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}