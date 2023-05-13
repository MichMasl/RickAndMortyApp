package ru.mihmas.rickandmortyapp.feature_list.location.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel

object LocationDiffUtil : DiffUtil.ItemCallback<LocationModel>() {
    override fun areItemsTheSame(
        oldItem: LocationModel,
        newItem: LocationModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: LocationModel,
        newItem: LocationModel
    ): Boolean {
        return oldItem == newItem
    }
}