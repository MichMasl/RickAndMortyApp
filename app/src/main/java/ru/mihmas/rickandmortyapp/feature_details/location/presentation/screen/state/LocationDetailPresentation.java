package ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.state;

import java.util.List;

import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail;

public class LocationDetailPresentation {
    int id;
    String name;
    String type;
    String dimension;
    List<LocationCharacterDetail> residents;

    public LocationDetailPresentation(
            int id,
            String name,
            String type,
            String dimension,
            List<LocationCharacterDetail> residents
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.residents = residents;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }

    public List<LocationCharacterDetail> getResidents() {
        return residents;
    }
}
