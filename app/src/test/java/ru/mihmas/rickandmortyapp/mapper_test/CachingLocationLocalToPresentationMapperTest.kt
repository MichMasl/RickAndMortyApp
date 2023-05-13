package ru.mihmas.rickandmortyapp.mapper_test

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mihmas.rickandmortyapp.feature_caching.location.data.mapper.LocationLocalMapper
import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation

private const val TEST_DATA = "TEST_DATA"

class CachingLocationLocalToPresentationMapperTest {
    private lateinit var locationLocalList: List<LocationLocal>
    private lateinit var cachedLocationList: List<CachedLocation>

    @Before
    fun init() {
        locationLocalList = listOf(
            LocationLocal(
                id = 1,
                name = TEST_DATA,
                type = TEST_DATA,
                dimension = TEST_DATA
            )
        )
        cachedLocationList = listOf(
            CachedLocation(
                id = 1,
                name = TEST_DATA,
                type = TEST_DATA,
                dimension = TEST_DATA
            )
        )
    }

    @Test
    fun should_return_CachedLocation() {
        val result = LocationLocalMapper(locationLocalList).map()
        Assert.assertEquals(cachedLocationList, result)
    }
}