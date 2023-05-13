package ru.mihmas.rickandmortyapp.feature_details.character.presentation.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import ru.mihmas.rickandmortyapp.databinding.ItemEpisodeListBinding;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;

public class ListOfCharacterEpisodeAdapter extends ListAdapter<CharacterEpisodeDetail, ListOfCharacterEpisodeViewHolder> {

    private OnEpisodeClickListener onEpisodeClickListener;

    public interface OnEpisodeClickListener {
        void onClick(CharacterEpisodeDetail episodeDetail);
    }

    public ListOfCharacterEpisodeAdapter(@NonNull DiffUtil.ItemCallback<CharacterEpisodeDetail> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ListOfCharacterEpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEpisodeListBinding bind = ItemEpisodeListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ListOfCharacterEpisodeViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfCharacterEpisodeViewHolder holder, int position) {
        CharacterEpisodeDetail item = getItem(position);
        holder.initView(item);
        holder.itemView.setOnClickListener(v -> onEpisodeClickListener.onClick(item));
    }

    public void setOnEpisodeClickListener(OnEpisodeClickListener listener) {
        onEpisodeClickListener = listener;
    }
}