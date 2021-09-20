package ru.samitin.lesson1.ui.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.FragmentWeatherBinding
import ru.samitin.lesson1.repository.SolarFlairResponseData
import ru.samitin.lesson1.viewModel.SolarFlairData
import ru.samitin.lesson1.viewModel.SolarFlairViewModel

class WeatherFragment : Fragment(){

    private var _binding : FragmentWeatherBinding?=null
    private val binding
    get() = _binding!!

    private val viewModel :SolarFlairViewModel by lazy {
        ViewModelProvider(this).get(SolarFlairViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentWeatherBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner,{ renderData(it)})
        viewModel.sendServerRequest()
    }
    private fun renderData(data : SolarFlairData){
        when(data){
            is SolarFlairData.Success ->{
                binding.conteinerWeather.show()
                binding.loadingLayout.hide()
                Toast.makeText(context,"Succes",Toast.LENGTH_SHORT).show()
                val infoAll=StringBuilder()
                for (info in data.serverResponseData)
                    infoAll.append(getSolarFairInfo(info))
                 binding.weatherTextInfo.text=infoAll.toString()
            }
            is SolarFlairData.Loading ->{
                binding.conteinerWeather.hide()
                binding.loadingLayout.show()
                Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
            }
            is SolarFlairData.Error ->{
                binding.conteinerWeather.hide()
                binding.loadingLayout.show()
                Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                binding.conteinerWeather.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload)
                ) {
                    viewModel.sendServerRequest()
                }

            }
        }
    }
    private fun getSolarFairInfo(serverResponseData: SolarFlairResponseData) : String{
        var info=""
        with(serverResponseData){
            info="\n \n fliD = $flrID \n"+
                    "begin time = $beginTime \n"+
                    "peak time = $peakTime \n"+
                    "end time = $endTime \n"+
                    "class type = $classType \n"+
                    "source location = $sourceLocation \n"+
                    "active region num = $activeRegionNum \n"+
                    "link = $link \n"
        }
        return info
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}