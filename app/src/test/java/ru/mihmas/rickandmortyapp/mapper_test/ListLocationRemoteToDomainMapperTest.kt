package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_list.location.data.mapper.LocationRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationModelRemote
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel

class ListLocationRemoteToDomainMapperTest {
    private lateinit var locationListRemote: List<LocationModelRemote>
    private lateinit var locationListDomain: List<LocationModel>
    @Before
    fun listInit() {
        locationListRemote = listOf(
            LocationModelRemote(
                created = "test",
                dimension = "test",
                id = 1,
                name = "test",
                residents = listOf("test"),
                type = "test",
                url = "test",
            )
        )
        locationListDomain = listOf(
            LocationModel(
                id = 1,
                name = "test",
                type = "test",
                dimension = "test"
            )
        )
    }

    @Test
    fun should_return_LocationModel() {
        val result = LocationRemoteToDomainMapper(locationListRemote).map()
        Assert.assertEquals(locationListDomain, result)
    }
}