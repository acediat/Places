package ru.acediat.places

import ru.acediat.places.entities.Place
import ru.acediat.places.entities.PlaceType
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val api: Api,
) {

    fun getAllPlaces() = api.getPlaces()
        .map {
            if (it.isSuccessful) {
                it.body()!!
            } else {
                throw IllegalStateException("lol")
            }
        }.map {
            it.map {
                Place(
                    title = it.title ?: "",
                    description = it.description ?: "",
                    longitude = it.longitude ?: 0.0,
                    latitude = it.latitude ?: 0.0,
                    types = it.types?.map {
                        PlaceType(
                            id = it.id ?: 0,
                            title = it.title ?: "",
                            pluralTitle = it.titlePlural ?: "",
                        )
                    } ?: listOf(),
                    photos = it.photos?.map { it.url ?: "" } ?: listOf()
                )
            }
        }

}