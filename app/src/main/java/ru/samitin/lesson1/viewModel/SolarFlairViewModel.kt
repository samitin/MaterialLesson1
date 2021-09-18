package ru.samitin.lesson1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.samitin.lesson1.BuildConfig
import ru.samitin.lesson1.repository.PODRetrofitImpl
import ru.samitin.lesson1.repository.PODServerResponseData
import ru.samitin.lesson1.repository.SolarFlairResponseData

class SolarFlairViewModel (private val liveDataToObserver: MutableLiveData<SolarFlairData> = MutableLiveData(),
                           private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
) : ViewModel(){

    fun getLiveData(): LiveData<SolarFlairData> {
        return liveDataToObserver
    }

    fun sendServerRequest(){
        liveDataToObserver.postValue(SolarFlairData.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getSolarFlairOfTheDay(apiKey,solarFlareCallback,"2021-09-07")
        }
    }

    val solarFlareCallback  = object : Callback<List<SolarFlairResponseData>> {
        override fun onResponse(call: Call<List<SolarFlairResponseData>>, response: Response<List<SolarFlairResponseData>>) {
            if(response.isSuccessful && response.body()!=null){

                liveDataToObserver.postValue(SolarFlairData.Success(response.body() as List<SolarFlairResponseData>)) // FIXME костыль
            }else{
                // TODO HW
}
}

override fun onFailure(call: Call<List<SolarFlairResponseData>>, t: Throwable) {
    liveDataToObserver.postValue(SolarFlairData.Error(t))
}
}
}