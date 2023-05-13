package ru.mihmas.rickandmortyapp.feature_details.character.presentation.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;

public class ListOfCharacterEpisodeDiffUtil extends DiffUtil.ItemCallback<CharacterEpisodeDetail> {
    private ListOfCharacterEpisodeDiffUtil() {}

    public static ListOfCharacterEpisodeDiffUtil getInstance() {
        return new ListOfCharacterEpisodeDiffUtil();
    }

    @Override
    public boolean areItemsTheSame(@NonNull CharacterEpisodeDetail oldItem, @NonNull CharacterEpisodeDetail newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull CharacterEpisodeDetail oldItem, @NonNull CharacterEpisodeDetail newItem) {
        return oldItem.equals(newItem);
    }
}
