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
import ru.samitin.lesson1.repository.MarsPhotosServerResponseData
import ru.samitin.lesson1.repository.MarsServerResponseData
import ru.samitin.lesson1.utils.CustomImageView
import ru.samitin.lesson1.viewModel.AppState
import ru.samitin.lesson1.viewModel.OneBigFatViewModel


class MarsFragment : Fragment(){
    private var _binding : FragmentMarsBinding?=null
    private val binding
        get() = _binding!!

    private val viewModel : OneBigFatViewModel by lazy {
        ViewModelProvider(this).get(OneBigFatViewModel::class.java)
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

        viewModel.getMarsPicture()
    }
    private fun renderData(data : AppState){
        when(data){
            is AppState.SuccessMars ->{
                binding.conteinerMarsPhotos.show()
                binding.loadingLayout.hide()
                Toast.makeText(context,"Succes",Toast.LENGTH_SHORT).show()
                data.serverResponseData?.let { getMarsWeatherInfo(it) }
            }
            is AppState.Loading ->{
                Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                binding.conteinerMarsPhotos.hide()
                binding.loadingLayout.show()
            }
            is AppState.Error ->{
                binding.conteinerMarsPhotos.hide()
                binding.loadingLayout.show()
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                binding.conteinerMarsPhotos.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload)
                ) {
                    viewModel.getMarsPicture()
                }
            }
        }
    }
    private fun getMarsWeatherInfo(serverResponseData: MarsPhotosServerResponseData){
        for (marsPhoto in serverResponseData.photos!!) {
            val dateText = MaterialTextView(requireContext())
            dateText.text = "Дата земли : ${marsPhoto.earth_date}"
            val image = CustomImageView(requireContext())

            //image.layoutParams.width=200
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