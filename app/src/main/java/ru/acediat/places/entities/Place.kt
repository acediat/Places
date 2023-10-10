package ru.acediat.places.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceType(
    val id: Int,
    val title: String,
    val pluralTitle: String,
) : Serializable

data class Place(
    val title: String,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    val types: List<PlaceType>,
    val photos: List<String>,
) : Serializable
