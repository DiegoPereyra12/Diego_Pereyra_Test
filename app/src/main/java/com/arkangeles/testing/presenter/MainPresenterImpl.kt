package com.arkangeles.testing.presenter

import android.content.Context
import android.os.Bundle
import com.arkangeles.testing.api.JobApi
import com.arkangeles.testing.api.JobService
import com.arkangeles.testing.interfaces.Constant
import com.arkangeles.testing.model.LocationApiModel
import com.arkangeles.testing.room.Location
import com.arkangeles.testing.room.LocationDao
import com.arkangeles.testing.room.LocationDb
import com.arkangeles.testing.view.fragments.BlankFragment
import com.arkangeles.testing.view.fragments.MapsViewFragment
import com.arkangeles.testing.view.fragments.OtherFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainPresenterImpl(
    val view: IMainView,
    context: Context,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : MainPresenter {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + uiContext
    private val scope = CoroutineScope(coroutineContext)
    val locationDao: LocationDao = LocationDb.getInstance(context).locationDao()
    private val mapsViewFragment = MapsViewFragment()
    private val emptyFragment = BlankFragment()
    private val otherFragment = OtherFragment()
    var resultsLat: Double = 10.000
    var resultsLon: Double = -0.1257
    var resultsTemp: Double = 0.0
    var name: String? = "City"
    private val apiClient: JobService? = JobApi.client.create(JobService::class.java)

    init {
        view.loadFragment(emptyFragment)
    }

    private fun apiCall() {
        val bundle = Bundle()
        val call = apiClient?.getLocation()
        call?.enqueue(object : Callback<LocationApiModel> {
            override fun onResponse(
                call: Call<LocationApiModel>,
                response: Response<LocationApiModel>
            ) {
                if (response.isSuccessful) {
                    resultsLat = response.body()!!.coord.lat
                    resultsLon = response.body()!!.coord.lon
                    resultsTemp = response.body()!!.main.temp
                    name = response.body()?.name

                    scope.launch {
                        locationDao.insert(
                            Location(
                                null,
                                resultsLat,
                                resultsLon,
                                name!!,
                                resultsTemp
                            )
                        )
                    }

                    bundle.putDouble(Constant.LAT_KEY, resultsLat)
                    bundle.putDouble(Constant.LON_KEY, resultsLon)
                    bundle.putString(Constant.NAME_KEY, name)
                    mapsViewFragment.arguments = bundle
                    view.loadFragment(mapsViewFragment)
                }
            }

            override fun onFailure(call: Call<LocationApiModel>, t: Throwable) {
                view.showErrorToast()
            }
        })
    }

    override fun onButtonMapsClick() {
        apiCall()
    }

    override fun onButtonRefreshClick() {
        view.loadFragment(emptyFragment)
        apiCall()
    }

    override fun onButtonOtherClick() {
        view.loadFragment(otherFragment)
    }

}