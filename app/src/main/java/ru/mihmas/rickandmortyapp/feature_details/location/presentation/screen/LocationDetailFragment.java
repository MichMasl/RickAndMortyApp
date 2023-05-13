package ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.mihmas.rickandmortyapp.R;
import ru.mihmas.rickandmortyapp.core.App;
import ru.mihmas.rickandmortyapp.core.di.ApplicationComponent;
import ru.mihmas.rickandmortyapp.databinding.FragmentLocationDetailBinding;
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.CharacterDetailFragment;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.recyclerview.ListOfLocationCharacterAdapter;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.recyclerview.ListOfLocationCharacterDiffUtil;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.state.LocationDetailPresentation;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.viewmodel.LocationDetailViewModel;
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.viewmodel.LocationDetailViewModelFactory;

public class LocationDetailFragment extends Fragment {
    private static final String LOCATION_ID = "LOCATION_ID";

    @Inject
    public LocationDetailViewModelFactory viewModelFactory;
    public ListOfLocationCharacterAdapter characterAdapter;
    private LocationDetailViewModel viewModel;
    private FragmentLocationDetailBinding bind;
    private int locationId;

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
            locationId = getArguments().getInt(LOCATION_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = FragmentLocationDetailBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        setupRecyclerView();
        setupObservers();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(LocationDetailViewModel.class);
        viewModel.getLocationDetails(locationId);
    }

    private void setupRecyclerView() {
        characterAdapter = new ListOfLocationCharacterAdapter(
                ListOfLocationCharacterDiffUtil.getInstance()
        );
        bind.charactersInLocationRecyclerView.setAdapter(characterAdapter);
    }

    private void setupObservers() {
        viewModel.state().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case PROGRESS:
                    hideAllViews();
                    bind.errorLocationDetail.setVisibility(View.GONE);
                    bind.progressBarLocationDetail.setVisibility(View.VISIBLE);
                    bind.noCharactersLocationDetail.setVisibility(View.GONE);
                    break;
                case ERROR:
                    hideAllViews();
                    bind.errorLocationDetail.setVisibility(View.VISIBLE);
                    bind.progressBarLocationDetail.setVisibility(View.GONE);
                    bind.noCharactersLocationDetail.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    showAllViews();
                    bind.errorLocationDetail.setVisibility(View.GONE);
                    bind.progressBarLocationDetail.setVisibility(View.GONE);
                    bind.noCharactersLocationDetail.setVisibility(View.GONE);
                    break;
                case NO_CHARACTERS:
                    showAllViews();
                    bind.errorLocationDetail.setVisibility(View.GONE);
                    bind.progressBarLocationDetail.setVisibility(View.GONE);
                    bind.charactersInLocationRecyclerView.setVisibility(View.GONE);
                    bind.noCharactersLocationDetail.setVisibility(View.VISIBLE);
                    break;
            }
        });
        viewModel.locationDetailLiveData().observe(
                getViewLifecycleOwner(),
                model -> {
                    setupViews(model);
                    setupListeners();
                }
        );
    }

    private void setupViews(LocationDetailPresentation model) {
        bind.nameLocationDetail.setText(model.getName());
        bind.typeLocationDetail.setText(model.getType());
        bind.dimensionLocationDetail.setText(model.getDimension());
        if (model.getResidents() != null) {
            characterAdapter.submitList(model.getResidents());
        }
    }

    private void setupListeners() {
        characterAdapter.setOnCharacterClickListener(character -> getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(
                        R.id.main_fragment_fragment_container_view,
                        CharacterDetailFragment.getInstance(character.getId())
                )
                .commit()
        );
    }

    private void hideAllViews() {
        bind.nameLocationDetail.setVisibility(View.GONE);
        bind.typeLocationDetail.setVisibility(View.GONE);
        bind.dimensionLocationDetail.setVisibility(View.GONE);
        bind.charactersInLocationRecyclerView.setVisibility(View.GONE);
    }

    private void showAllViews() {
        bind.nameLocationDetail.setVisibility(View.VISIBLE);
        bind.typeLocationDetail.setVisibility(View.VISIBLE);
        bind.dimensionLocationDetail.setVisibility(View.VISIBLE);
        bind.charactersInLocationRecyclerView.setVisibility(View.VISIBLE);
    }

    public static LocationDetailFragment getInstance(int locationId) {
        Bundle bundle = new Bundle();
        bundle.putInt(LOCATION_ID, locationId);
        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}