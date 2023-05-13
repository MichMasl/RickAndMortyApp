package ru.mihmas.rickandmortyapp.feature_details.character.presentation.viewmodel;

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
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterDetail;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase.GetCharacterDetailsUseCase;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.usecase.GetListOfCharacterEpisodeUseCase;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.state.CharacterDetailPresentation;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.state.CharacterDetailsState;

public class CharacterDetailViewModel extends ViewModel {
    private final static int PARSED_EPISODE_ID = 40;
    private final static int UNDEFINED_ID = -1;

    private final GetListOfCharacterEpisodeUseCase getListOfCharacterEpisodeUseCase;
    private final GetCharacterDetailsUseCase getCharacterDetailsUseCase;
    private final MutableLiveData<CharacterDetailsState> _state = new MutableLiveData<>();
    private final MutableLiveData<CharacterDetailPresentation> _characterDetailsLiveData = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CharacterDetailViewModel(
            GetListOfCharacterEpisodeUseCase getListOfCharacterEpisodeUseCase,
            GetCharacterDetailsUseCase getCharacterDetailsUseCase
    ) {
        this.getListOfCharacterEpisodeUseCase = getListOfCharacterEpisodeUseCase;
        this.getCharacterDetailsUseCase = getCharacterDetailsUseCase;
    }

    public LiveData<CharacterDetailsState> detailsStateLiveData() {
        return _state;
    }

    public LiveData<CharacterDetailPresentation> characterDetailsLiveData() {
        return _characterDetailsLiveData;
    }

    public void getCharacterDetails(int characterId) {
        _state.setValue(CharacterDetailsState.PROGRESS);
        Disposable disposable = getCharacterDetailsUseCase.getCharacterDetails(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        characterDetail -> {
                            String ids = formStringOfEpisodeId(characterDetail.getEpisode());
                            provideCharacterDetails(ids, characterDetail);
                            _state.setValue(CharacterDetailsState.SUCCESS);
                        },
                        throwable -> _state.setValue(CharacterDetailsState.ERROR)
                );
        compositeDisposable.add(disposable);
    }

    private String formStringOfEpisodeId(List<String> listOfUrl) {
        final List<String> listOfId = new ArrayList<>();
        for (String url : listOfUrl) {
            String episodeId = url.substring(PARSED_EPISODE_ID);
            listOfId.add(episodeId);
        }
        String stringOfIds = listOfId.stream().collect(Collectors.joining(
                ",","",","
        ));
        return stringOfIds;
    }

    private void provideCharacterDetails(
            String listOfEpisodeId,
            CharacterDetail characterDetailWithoutListOfEpisodes
    ) {
        Disposable disposable = getListOfCharacterEpisodeUseCase.getListOfCharactersEpisodes(listOfEpisodeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listOfEpisodes -> _characterDetailsLiveData.setValue(
                                characterDetailPresentationAssembler(
                                        characterDetailWithoutListOfEpisodes,
                                        listOfEpisodes
                                )
                        )
                );
        compositeDisposable.add(disposable);
    }

    private CharacterDetailPresentation characterDetailPresentationAssembler(
            CharacterDetail characterDetail,
            List<CharacterEpisodeDetail> listOfEpisodes
    ) {
        return new CharacterDetailPresentation(
                characterDetail.getId(),
                characterDetail.getName(),
                characterDetail.getStatus(),
                characterDetail.getSpecies(),
                characterDetail.getType(),
                characterDetail.getGender(),
                characterDetail.getOriginName(),
                formIdFromUrl(characterDetail.getOriginUrl()),
                characterDetail.getLocationName(),
                formIdFromUrl(characterDetail.getLocationUrl()),
                characterDetail.getImage(),
                listOfEpisodes
        );
    }

    private int formIdFromUrl(String url) {
        if (!url.isBlank()) {
            String id = url.substring(41);
            return Integer.parseInt(id);
        } else {
            return UNDEFINED_ID;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}