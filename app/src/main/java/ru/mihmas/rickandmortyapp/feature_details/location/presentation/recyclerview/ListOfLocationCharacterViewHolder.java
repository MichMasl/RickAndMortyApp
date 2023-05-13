package ru.mihmas.rickandmortyapp.feature_details.location.presentation.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import ru.mihmas.rickandmortyapp.databinding.ItemCharacterListBinding;
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail;

public class ListOfLocationCharacterViewHolder  extends RecyclerView.ViewHolder {
    private final ItemCharacterListBinding bind;

    public ListOfLocationCharacterViewHolder(ItemCharacterListBinding bind) {
        super(bind.getRoot());
        this.bind = bind;
    }

    public void initView(LocationCharacterDetail item) {
        bind.nameCharacter.setText(item.getName());
        bind.speciesCharacter.setText(item.getSpecies());
        bind.statusCharacter.setText(item.getStatus());
        bind.genderCharacter.setText(item.getGender());
        Glide.with(itemView).load(item.getImage()).into(bind.imageCharacter);
    }
}
