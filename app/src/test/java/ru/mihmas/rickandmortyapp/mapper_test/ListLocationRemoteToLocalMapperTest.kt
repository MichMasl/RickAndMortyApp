package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal
import ru.mihmas.rickandmortyapp.feature_list.location.data.mapper.LocationRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationModelRemote

class ListLocationRemoteToLocalMapperTest {
    private lateinit var locationListRemote: List<LocationModelRemote>
    private lateinit var locationListLocal: List<LocationLocal>
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
        locationListLocal = listOf(
            LocationLocal(
                id = 1,
                name = "test",
                type = "test",
                dimension = "test"
            )
        )
    }

    @Test
    fun should_return_LocationLocal() {
        val result = LocationRemoteToLocalMapper(locationListRemote).map()
        Assert.assertEquals(locationListLocal, result)
    }
}