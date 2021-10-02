package ru.samitin.lesson1.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import ru.samitin.lesson1.R
import ru.samitin.lesson1.view.chips.MY_THEME_KEY
import ru.samitin.lesson1.view.chips.THEME_GREEN
import ru.samitin.lesson1.view.chips.THEME_MAIN
import ru.samitin.lesson1.view.chips.THEME_ORANGE
import ru.samitin.lesson1.view.picture.PODFragment
import ru.samitin.lesson1.viewModel.OneBigFatViewModel

const val PREFERENCE_NAME="PREFERENCE_NAME"
class MainActivity : AppCompatActivity() {
    val oneBigFatViewModel by lazy {
        ViewModelProvider(this).get(OneBigFatViewModel::class.java)
    }
    private val preference by lazy {
        getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (preference.getString(MY_THEME_KEY, "")== THEME_MAIN)
            setTheme(R.style.ThemeMain)
        else if (preference.getString(MY_THEME_KEY, "")== THEME_ORANGE) {
            setTheme(R.style.orangeTheme)
        } else if(preference.getString(MY_THEME_KEY, "")== THEME_GREEN)
            setTheme(R.style.greenTheme)

        setContentView(R.layout.activity_main)

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
              // .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                .replace(R.id.container,PODFragment.newInstance())
                .commitNow()
        }
    }
}