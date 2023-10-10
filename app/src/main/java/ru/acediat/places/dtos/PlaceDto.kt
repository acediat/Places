package ru.acediat.places.dtos

import com.google.gson.annotations.SerializedName

data class PlaceTypeDto(
    val id: Int?,
    val title: String?,
    @SerializedName("title_plural")
    val titlePlural: String?,
)

data class PhotoDto(
    val id: Int?,
    @SerializedName("photo")
    val url: String?,
)

data class PlaceDto(
    val title: String?,
    val description: String?,
    @SerializedName("long")
    val longitude: Double?,
    @SerializedName("lat")
    val latitude: Double?,
    val types: List<PlaceTypeDto>?,
    val photos: List<PhotoDto>?,
)