package q4_android_professional.myapplication.view.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import myapplication.historyscreen.HistoryFragment
import q4_android_professional.myapplication.R

class MainActivity : AppCompatActivity() {
    @RequiresApi(31)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)

        splashScreenCountdown()
        /** После смахивания появляется белая полоска сверху, поэтому закомментил
        slideSplashScreenToEnd(splashScreen)
        */

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    MainFragment.newInstance()
                )
                .commit()
        }
    }

    /** Смахивается splashscreen к концу в момент окончания его действия */
    private fun slideSplashScreenToEnd(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val slideEnd = ObjectAnimator.ofFloat(
                splashScreenViewProvider.view,
                View.TRANSLATION_X,
                0f,
                splashScreenViewProvider.view.width.toFloat()
            )
            slideEnd.interpolator = AnticipateInterpolator()
            slideEnd.duration = 500L
            slideEnd.doOnEnd { splashScreenViewProvider.remove() }
            slideEnd.start()
        }
    }

    /** Открываем отсчет времени, после которого наш splashscreen уберётся */
    private fun splashScreenCountdown() {
        var isHideSplashScreen = false
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    /** Сетим наше меню, при этом открываю ActionBar в темах */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                val manager = supportFragmentManager
                manager.let {
                    manager.beginTransaction()
                        .replace(R.id.fragment_container, HistoryFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}