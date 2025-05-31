package com.odesar.mapabioculturalinteractivosandionisio

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(window) {

            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

            enterTransition = Fade()

            exitTransition = Fade()

            exitTransition.duration = 5000

            enterTransition.duration = 5000

        }

        setContentView(R.layout.activity_splash)

        lifecycleScope.launch(Dispatchers.Main) {

            delay(2000)

            val intent = Intent(this@SplashActivity, MenuActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity).toBundle())

        }

    }
}