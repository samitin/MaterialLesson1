package ru.samitin.lesson1.view.chips

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.FragmentSettingsBinding
import ru.samitin.lesson1.view.PREFERENCE_NAME

const val THEME_ORANGE="THEME_ORANGE"
const val THEME_GREEN="THEME_GREEN"
const val MY_THEME_KEY="MY_THEME_KEY"

class SettingsFragment:Fragment() {


    var _bindong: FragmentSettingsBinding? = null
    val binding: FragmentSettingsBinding
        get() {
            return _bindong!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        /*val theme=savedInstanceState?.getString(MY_THEME_KEY)
        if (theme== THEME_ORANGE){
            requireActivity().setTheme(R.style.orangeTheme)
            requireActivity().recreate()
        }else if (theme== THEME_GREEN){
            requireActivity().setTheme(R.style.greenTheme)
            requireActivity().recreate()
        }*/
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_chips, container, false)
        _bindong =  FragmentSettingsBinding.inflate(inflater)
        return  binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindong = null
    }
    private val preference by lazy {
        requireActivity().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener{childGroup,position->
            Toast.makeText(context,"Click $position",Toast.LENGTH_SHORT).show()
        }
        binding.chipWithClose.setOnCloseIconClickListener {
            Toast.makeText(context,"Click on chipWithClose",Toast.LENGTH_SHORT).show()
        }
        binding.btnOrangeTheme.setOnClickListener {
                Toast.makeText(context,"Click btnOrangeTheme",Toast.LENGTH_SHORT).show()
                preference.edit().putString(MY_THEME_KEY, THEME_ORANGE).apply()
                requireActivity().recreate()
        }
        binding.btnGreenTheme.setOnClickListener {
                Toast.makeText(context,"Click btnGreenTheme",Toast.LENGTH_SHORT).show()
                preference.edit().putString(MY_THEME_KEY, THEME_GREEN).apply()
                requireActivity().recreate()

        }
    }
    companion object {
        fun newInstance() = SettingsFragment()
    }


}