package ru.samitin.lesson1.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.samitin.lesson1.BuildConfig
import ru.samitin.lesson1.repository.PODRetrofitImpl
import ru.samitin.lesson1.repository.marsWeather.MarsPhotosResponseData

class MarsPhotosViewModel (private val liveDataToObserver: MutableLiveData<MarsPhotosData> = MutableLiveData(),
                           private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
) : ViewModel(){

    fun getLiveData(): LiveData<MarsPhotosData> {
        return liveDataToObserver
    }

    fun sendServerRequest(){
        liveDataToObserver.postValue(MarsPhotosData.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getMarsPhotos(apiKey,marsWeatherCallback)
        }
    }

    val marsWeatherCallback  = object : Callback<MarsPhotosResponseData>{
        override fun onResponse(
            call: Call<MarsPhotosResponseData>,
            response: Response<MarsPhotosResponseData>
        ) {
            if(response.isSuccessful && response.body()!=null){
                liveDataToObserver.postValue(MarsPhotosData.Success(response.body())) // FIXME костыль
                // TODO HW
            }
        }

        override fun onFailure(call: Call<MarsPhotosResponseData>, t: Throwable) {
            liveDataToObserver.postValue(MarsPhotosData.Error(t))
        }

    }
}