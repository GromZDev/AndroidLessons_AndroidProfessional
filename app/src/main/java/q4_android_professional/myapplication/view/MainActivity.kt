package q4_android_professional.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import q4_android_professional.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    MainFragment.newInstance()
                )
                .addToBackStack(null)
                .commit()
        }
    }
}