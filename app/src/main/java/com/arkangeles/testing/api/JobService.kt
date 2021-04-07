package com.arkangeles.testing.api

import com.arkangeles.testing.model.LocationApiModel
import com.arkangeles.testing.interfaces.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface JobService {

    @Headers(
        "x-rapidapi-key: d3fe3a34c5mshf70e1bb90c464a8p1ba4aajsn4233972e8a9c",
        "x-rapidapi-host: community-open-weather-map.p.rapidapi.com"
    )
    @GET(Constant.CAPITAL)
    fun getLocation(): Call<LocationApiModel>
}