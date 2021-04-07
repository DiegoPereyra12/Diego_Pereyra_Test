package com.arkangeles.testing.interfaces

interface Constant {

    companion object {
         const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
         private const val city = "México"
         const val CAPITAL = "weather?q=$city"
         const val BASE_URL = "https://community-open-weather-map.p.rapidapi.com/"
         const val LAT_KEY = "LAT"
         const val LON_KEY = "LON"
         const val NAME_KEY = "NAME"
    }
}