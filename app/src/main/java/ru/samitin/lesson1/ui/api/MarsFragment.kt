package ru.samitin.lesson1.ui.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.textview.MaterialTextView
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.FragmentMarsBinding
import ru.samitin.lesson1.repository.marsWeather.MarsPhotosResponseData
import ru.samitin.lesson1.viewModel.MarsPhotosData
import ru.samitin.lesson1.viewModel.MarsPhotosViewModel


class MarsFragment : Fragment(){
    private var _binding : FragmentMarsBinding?=null
    private val binding
        get() = _binding!!

    private val viewModel : MarsPhotosViewModel by lazy {
        ViewModelProvider(this).get(MarsPhotosViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentMarsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner,{
            renderData(it)
        })

        viewModel.sendServerRequest()
    }
    private fun renderData(data : MarsPhotosData){
        when(data){
            is MarsPhotosData.Success ->{
                binding.conteinerMarsPhotos.show()
                binding.loadingLayout.hide()
                Toast.makeText(context,"Succes",Toast.LENGTH_SHORT).show()
                data.serverResponseData?.let { getMarsWeatherInfo(it) }
            }
            is MarsPhotosData.Loading ->{
                Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                binding.conteinerMarsPhotos.hide()
                binding.loadingLayout.show()
            }
            is MarsPhotosData.Error ->{
                binding.conteinerMarsPhotos.hide()
                binding.loadingLayout.show()
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                binding.conteinerMarsPhotos.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload)
                ) {
                    viewModel.sendServerRequest()
                }
            }
        }
    }
    private fun getMarsWeatherInfo(serverResponseData: MarsPhotosResponseData){
        for (marsPhoto in serverResponseData.marsPhotos!!) {
            val dateText = MaterialTextView(requireContext())
            dateText.text = "Дата земли : ${marsPhoto.earthDate}"
            val image = AppCompatImageView(requireContext())
            image.load(marsPhoto.imgSrc){
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.progres_image_animation)
            }
            binding.conteinerMarsPhotos.addView(image)
            binding.conteinerMarsPhotos.addView(dateText)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}