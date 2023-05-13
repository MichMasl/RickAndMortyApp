package ru.mihmas.rickandmortyapp.feature_details.location.presentation.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail;

public class ListOfLocationCharacterDiffUtil extends DiffUtil.ItemCallback<LocationCharacterDetail> {
    private ListOfLocationCharacterDiffUtil() {}

    public static ListOfLocationCharacterDiffUtil getInstance() {
        return new ListOfLocationCharacterDiffUtil();
    }

    @Override
    public boolean areItemsTheSame(@NonNull LocationCharacterDetail oldItem, @NonNull LocationCharacterDetail newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull LocationCharacterDetail oldItem, @NonNull LocationCharacterDetail newItem) {
        return oldItem.equals(newItem);
    }
}
