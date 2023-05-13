package ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.state;

import java.util.List;

import ru.mihmas.rickandmortyapp.feature_details.character.domain.model.CharacterEpisodeDetail;

public class CharacterDetailPresentation {
    private final int id;
    private final String name;
    private final String status;
    private final String species;
    private final String type;
    private final String gender;
    private final String originName;
    private final int originId;
    private final String locationName;
    private final int locationId;
    private final String image;
    private final List<CharacterEpisodeDetail> episode;

    public CharacterDetailPresentation(
            int id,
            String name,
            String status,
            String species,
            String type,
            String gender,
            String originName,
            int originId,
            String locationName,
            int locationId,
            String image,
            List<CharacterEpisodeDetail> episode
    ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.originName = originName;
        this.originId = originId;
        this.locationName = locationName;
        this.locationId = locationId;
        this.image = image;
        this.episode = episode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getOriginName() {
        return originName;
    }

    public int getOriginId() {
        return originId;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getImage() {
        return image;
    }

    public List<CharacterEpisodeDetail> getEpisode() {
        return episode;
    }
}