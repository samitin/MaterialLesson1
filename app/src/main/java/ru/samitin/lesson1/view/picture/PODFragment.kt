package ru.samitin.lesson1.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.FragmentMainBinding
import ru.samitin.lesson1.view.MainActivity
import ru.samitin.lesson1.view.chips.ChipsFragment
import ru.samitin.lesson1.viewModel.PictureOfTheDayData
import ru.samitin.lesson1.viewModel.PODViewModel

class PODFragment : Fragment() {
    private var _bainding:FragmentMainBinding?=null
    private val binding
        get()=_bainding!!

    private lateinit var bottomSheetBehavior:BottomSheetBehavior <ConstraintLayout>
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
        setBottomAppBar(view)
        binding.inputLayout.setEndIconOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW).apply {
                data= Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            startActivity(intent)
        }
        bottomSheetBehavior=BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state=BottomSheetBehavior.STATE_HALF_EXPANDED
        /*bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                    BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                    BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                TODO("not implemented")
            }
        })*/
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_search -> requireActivity().supportFragmentManager
                .beginTransaction().replace(R.id.container,ChipsFragment.newInstance()).addToBackStack("").commit()
            // у нашего бургера такой вот id внутри android
            android.R.id.home-> BottomNavigationDraverFragment().show(requireActivity().supportFragmentManager,"TAG")
        }
        return super.onOptionsItemSelected(item)
    }

    private var isMain=true
    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null // лучше придумать замену бургеру
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                    )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

}