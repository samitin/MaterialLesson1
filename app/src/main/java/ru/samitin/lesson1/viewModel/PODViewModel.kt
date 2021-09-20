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

class PODViewModel(private val liveDataToObserver:MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
                   private val retrofitImpl:PODRetrofitImpl= PODRetrofitImpl()
 ) : ViewModel() {

    fun getLiveData():LiveData<PictureOfTheDayData>{
        return liveDataToObserver
    }

    fun sendServerRequest(){
        liveDataToObserver.postValue(PictureOfTheDayData.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getPictureOfTheDay(apiKey,PODCallback)
        }
    }

    val PODCallback  = object : Callback<PODServerResponseData>{
        override fun onResponse(call: Call<PODServerResponseData>, response: Response<PODServerResponseData>) {
            if(response.isSuccessful && response.body()!=null){
                liveDataToObserver.postValue(PictureOfTheDayData.Success(response.body() as PODServerResponseData)) // FIXME костыль
            }else{
                // TODO HW
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            // TODO HW
        }
    }


}