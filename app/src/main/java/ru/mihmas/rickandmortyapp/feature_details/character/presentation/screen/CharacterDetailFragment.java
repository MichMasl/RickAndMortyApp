package ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import ru.mihmas.rickandmortyapp.R;
import ru.mihmas.rickandmortyapp.core.App;
import ru.mihmas.rickandmortyapp.core.di.ApplicationComponent;
import ru.mihmas.rickandmortyapp.databinding.FragmentCharacterDetailBinding;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.recyclerview.ListOfCharacterEpisodeAdapter;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.recyclerview.ListOfCharacterEpisodeDiffUtil;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.state.CharacterDetailPresentation;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.viewmodel.CharacterDetailViewModel;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.viewmodel.CharacterDetailViewModelFactory;
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.EpisodeDetailFragment;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.LocationDetailFragment;

public class CharacterDetailFragment extends Fragment {
    private static final String CHARACTER_ID = "CHARACTER_ID";

    @Inject
    public CharacterDetailViewModelFactory viewModelFactory;
    public ListOfCharacterEpisodeAdapter episodesAdapter;
    private CharacterDetailViewModel viewModel;
    private FragmentCharacterDetailBinding bind;
    private int characterId;

    @Override
    public void onAttach(@NonNull Context context) {
        App app = (App) requireActivity().getApplication();
        ApplicationComponent applicationComponent = app.getComponent();
        applicationComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            characterId = getArguments().getInt(CHARACTER_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bind = FragmentCharacterDetailBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        setupRecyclerView();
        setupObservers();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(CharacterDetailViewModel.class);
        viewModel.getCharacterDetails(characterId);
    }

    private void setupRecyclerView() {
        episodesAdapter = new ListOfCharacterEpisodeAdapter(
                ListOfCharacterEpisodeDiffUtil.getInstance()
        );
        bind.episodeInCharacterRecyclerView.setAdapter(episodesAdapter);
    }

    private void setupObservers() {
        viewModel.detailsStateLiveData().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case PROGRESS:
                    hideAllViews();
                    bind.errorCharacterDetail.setVisibility(View.GONE);
                    bind.progressBarCharacterDetail.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    hideAllViews();
                    bind.errorCharacterDetail.setVisibility(View.VISIBLE);
                    bind.progressBarCharacterDetail.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    showAllViews();
                    bind.errorCharacterDetail.setVisibility(View.GONE);
                    bind.progressBarCharacterDetail.setVisibility(View.GONE);
                    break;
            }
        });
        viewModel.characterDetailsLiveData().observe(
                getViewLifecycleOwner(),
                model -> {
                    setupViews(model);
                    setupListeners(model);
                }
        );
    }

    private void setupViews(CharacterDetailPresentation model) {
        Glide.with(requireActivity()).load(model.getImage()).into(bind.imageCharacterDetail);
        bind.nameCharacterDetail.setText(model.getName());
        bind.speciesCharacterDetail.setText(model.getSpecies());
        bind.genderCharacterDetail.setText(model.getGender());
        bind.statusCharacterDetail.setText(model.getStatus());
        bind.typeCharacterDetail.setText(model.getType());
        bind.originCharacterDetail.setText(model.getOriginName());
        bind.locationCharacterDetail.setText(model.getLocationName());
        episodesAdapter.submitList(model.getEpisode());
    }

    private void setupListeners(CharacterDetailPresentation model) {
        originClickListener(model);
        locationClickListener(model);
        episodeClickListener();
    }

    private void originClickListener(CharacterDetailPresentation model) {
        bind.originCharacterDetail.setOnClickListener(v ->  {
                    if (model.getOriginId() != -1) {
                        getParentFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(
                                        R.id.main_fragment_fragment_container_view,
                                        LocationDetailFragment.getInstance(model.getOriginId())
                                )
                                .commit();
                    } else {
                        Toast.makeText(requireContext(), "Unknown location", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void locationClickListener(CharacterDetailPresentation model) {
        bind.locationCharacterDetail.setOnClickListener(v -> {
                    if (model.getLocationId() != -1) {
                        getParentFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(
                                        R.id.main_fragment_fragment_container_view,
                                        LocationDetailFragment.getInstance(model.getLocationId())
                                )
                                .commit();
                    } else {
                        Toast.makeText(requireContext(), "Unknown location", Toast.LENGTH_SHORT).show();
                    }

                }
        );
    }

    private void episodeClickListener() {
        episodesAdapter.setOnEpisodeClickListener(episode -> getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(
                        R.id.main_fragment_fragment_container_view,
                        EpisodeDetailFragment.Companion.getInstance(episode.getId())
                )
                .commit()
        );
    }

    private void hideAllViews() {
        bind.imageCharacterDetail.setVisibility(View.GONE);
        bind.nameCharacterDetail.setVisibility(View.GONE);
        bind.speciesCharacterDetail.setVisibility(View.GONE);
        bind.genderCharacterDetail.setVisibility(View.GONE);
        bind.statusCharacterDetail.setVisibility(View.GONE);
        bind.typeCharacterDetail.setVisibility(View.GONE);
        bind.tvFromCharacterDetail.setVisibility(View.GONE);
        bind.originCharacterDetail.setVisibility(View.GONE);
        bind.tvLastLocationCharacterDetail.setVisibility(View.GONE);
        bind.tvSeenInCharacterDetail.setVisibility(View.GONE);
        bind.locationCharacterDetail.setVisibility(View.GONE);
        bind.episodeInCharacterRecyclerView.setVisibility(View.GONE);
    }

    private void showAllViews() {
        bind.imageCharacterDetail.setVisibility(View.VISIBLE);
        bind.nameCharacterDetail.setVisibility(View.VISIBLE);
        bind.speciesCharacterDetail.setVisibility(View.VISIBLE);
        bind.genderCharacterDetail.setVisibility(View.VISIBLE);
        bind.statusCharacterDetail.setVisibility(View.VISIBLE);
        bind.typeCharacterDetail.setVisibility(View.VISIBLE);
        bind.tvFromCharacterDetail.setVisibility(View.VISIBLE);
        bind.originCharacterDetail.setVisibility(View.VISIBLE);
        bind.tvLastLocationCharacterDetail.setVisibility(View.VISIBLE);
        bind.tvSeenInCharacterDetail.setVisibility(View.VISIBLE);
        bind.locationCharacterDetail.setVisibility(View.VISIBLE);
        bind.episodeInCharacterRecyclerView.setVisibility(View.VISIBLE);
    }

    public static CharacterDetailFragment getInstance(int characterId) {
        Bundle bundle = new Bundle();
        bundle.putInt(CHARACTER_ID, characterId);
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}