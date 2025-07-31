package org.odesar.mapa_biocultural_interactivo_san_dionisio

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import org.odesar.mapa_biocultural_interactivo_san_dionisio.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                finishAffinity()

            }

        }

        onBackPressedDispatcher.addCallback(this, callback)

        binding.btnIrAlMapa.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

        }
    }
}