package ru.acediat.places

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import ru.acediat.network.EndpointUrl
import ru.acediat.places.dtos.PlaceDto

@EndpointUrl("https://killroyka.pythonanywhere.com/api/")
interface Api {

    @GET("route")
    fun getPlaces(): Single<Response<List<PlaceDto>>>
}