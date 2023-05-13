package ru.mihmas.rickandmortyapp.feature_details.location.presentation.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding;
import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail;

public class ListOfLocationCharacterAdapter extends ListAdapter<LocationCharacterDetail, ListOfLocationCharacterViewHolder> {

    private OnCharacterClickListener onCharacterClickListener;

    public interface OnCharacterClickListener {
        void onClick(LocationCharacterDetail characterDetail);
    }

    public ListOfLocationCharacterAdapter(@NonNull DiffUtil.ItemCallback<LocationCharacterDetail> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ListOfLocationCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterListBinding bind = ItemCharacterListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ListOfLocationCharacterViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfLocationCharacterViewHolder holder, int position) {
        LocationCharacterDetail item = getItem(position);
        holder.initView(item);
        holder.itemView.setOnClickListener(v -> onCharacterClickListener.onClick(item));
    }

    public void setOnCharacterClickListener(OnCharacterClickListener onCharacterClickListener) {
        this.onCharacterClickListener = onCharacterClickListener;
    }
}