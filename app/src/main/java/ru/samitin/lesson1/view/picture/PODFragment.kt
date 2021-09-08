package ru.samitin.lesson1.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.FragmentMainBinding
import ru.samitin.lesson1.viewModel.PictureOfTheDayData
import ru.samitin.lesson1.viewModel.PODViewModel

class PODFragment : Fragment() {
    private var _bainding:FragmentMainBinding?=null
    private val binding
        get()=_bainding!!

    private val viewModel:PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _bainding= FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderDATA(it) })
        viewModel.sendServerRequest()
        binding.inputLayout.setEndIconOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW).apply {
                data= Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            startActivity(intent)
        }
    }
    private fun renderDATA(data:PictureOfTheDayData){
        when (data){
            is PictureOfTheDayData.Success->{
                binding.imageView.load(data.serverResponseData.url){
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is PictureOfTheDayData.Loading->{}
            is PictureOfTheDayData.Error->{}//TODO HW
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding==null
    }
    companion object{
        fun newInstance():PODFragment{
            return PODFragment()
        }
    }
}