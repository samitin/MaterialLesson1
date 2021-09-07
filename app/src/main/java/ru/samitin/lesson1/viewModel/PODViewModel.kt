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

class PODViewModel(private val liveDataToObserver:MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
                   private val retrofitImpl:PODRetrofitImpl= PODRetrofitImpl()
 ) : ViewModel() {

     fun getLiveData():LiveData<PictureOfTheDayData>{
         return liveDataToObserver
     }
    fun sendServerRequest(){
        liveDataToObserver.postValue(PictureOfTheDayData.Loading(null))
        val apiKey= BuildConfig.NASA_API_KEY//"ojZC5iFhBBGYF5XDEQaMvhM4Nvec80WZ0pBNZIis"
        if (apiKey.isBlank()){

        }else{
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(
                object : Callback<PODServerResponseData>{
                    override fun onResponse(call: Call<PODServerResponseData>, response: Response<PODServerResponseData>) {
                      if (response.isSuccessful && response.body()!=null){
                          liveDataToObserver.postValue(PictureOfTheDayData.Success(response.body()!!))
                      }else{
                          //TODO HW
                      }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        //TODO HW
                    }

                }
            )
        }
    }
}