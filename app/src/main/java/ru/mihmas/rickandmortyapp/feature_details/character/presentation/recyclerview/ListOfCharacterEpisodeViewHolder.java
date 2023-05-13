package ru.mihmas.rickandmortyapp.feature_details.character.presentation.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;

public class ListOfCharacterEpisodeViewHolder extends RecyclerView.ViewHolder {
    private final ItemEpisodeListBinding bind;

    public ListOfCharacterEpisodeViewHolder(ItemEpisodeListBinding bind) {
        super(bind.getRoot());
        this.bind = bind;
    }

    public void initView(CharacterEpisodeDetail item) {
        bind.nameEpisode.setText(item.getName());
        bind.numberEpisode.setText(item.getEpisode());
        bind.airDateEpisode.setText(item.getAirDate());
    }
}