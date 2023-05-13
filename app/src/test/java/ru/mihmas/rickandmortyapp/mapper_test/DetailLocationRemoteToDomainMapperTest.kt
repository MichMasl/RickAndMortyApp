package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_details.location.data.mapper.LocationDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationReferenceRemote
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail

private const val TEST_DATA = "TEST_DATA"

class DetailLocationRemoteToDomainMapperTest {
    private lateinit var locationDetailRemote: LocationDetailRemote
    private lateinit var locationDetail: LocationDetail
    private lateinit var locationCharacterDetailListRemote: List<LocationCharacterDetailRemote>
    private lateinit var locationCharacterDetailListDomain: List<LocationCharacterDetail>

    @Before
    fun init() {
        val reference = LocationReferenceRemote(
            name = TEST_DATA,
            url = TEST_DATA
        )
        locationDetailRemote = LocationDetailRemote(
            created = TEST_DATA,
            dimension = TEST_DATA,
            id = 1,
            name = TEST_DATA,
            residents = listOf(TEST_DATA),
            type = TEST_DATA,
            url = TEST_DATA
        )
        locationDetail = LocationDetail(
            id = 1,
            name = TEST_DATA,
            type = TEST_DATA,
            dimension = TEST_DATA,
            residents = listOf(TEST_DATA)
        )
        locationCharacterDetailListRemote = listOf(
            LocationCharacterDetailRemote(
                created = TEST_DATA,
                episode = listOf(TEST_DATA),
                gender = TEST_DATA,
                id = 1,
                image = TEST_DATA,
                location = reference,
                name = TEST_DATA,
                origin = reference,
                species = TEST_DATA,
                status = TEST_DATA,
                type = TEST_DATA,
                url = TEST_DATA
            )
        )
        locationCharacterDetailListDomain = listOf(
            LocationCharacterDetail(
                id = 1,
                name = TEST_DATA,
                species = TEST_DATA,
                status = TEST_DATA,
                gender = TEST_DATA,
                image = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_LocationDetail() {
        val result = LocationDetailMapper.mapRemoteToDomain(locationDetailRemote)
        Assert.assertEquals(locationDetail, result)
    }

    @Test
    fun should_return_LocationCharacterDetail() {
        val result = LocationDetailMapper.characterListRemoteToDomain(locationCharacterDetailListRemote)
        Assert.assertEquals(locationCharacterDetailListDomain, result)
    }
}