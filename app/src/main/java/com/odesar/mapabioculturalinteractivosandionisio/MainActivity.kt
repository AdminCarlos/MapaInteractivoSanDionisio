package com.odesar.mapabioculturalinteractivosandionisio

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Fade
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.odesar.mapabioculturalinteractivosandionisio.Database.AppDatabase
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Lugares
import com.odesar.mapabioculturalinteractivosandionisio.Fragments.ExtraInfoDialog
import com.odesar.mapabioculturalinteractivosandionisio.Fragments.InformationDialog
import com.odesar.mapabioculturalinteractivosandionisio.Fragments.LeyendasDialog
import com.odesar.mapabioculturalinteractivosandionisio.databinding.ActivityMainBinding
import com.otaliastudios.zoom.ZoomEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    //HACER UN Build > Clean Project CUANDO HAYA ERROR DE NULL VIEW ID

    private lateinit var binding : ActivityMainBinding

    lateinit var customContext: Context
    lateinit var db: AppDatabase
    lateinit var assetManager: AssetManager

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getDatabase(this)

        ViewCompat.setZ(binding.menuControls, 100.0F)
        ViewCompat.setZ(binding.btnSearch, 100.0F)
        ViewCompat.setZ(binding.layoutPadreEditText, 100.0F)
        ViewCompat.setZ(binding.eTxtBuscar, 100.0F)
        ViewCompat.setZ(binding.btnMinusZoom, 100.0F)
        ViewCompat.setZ(binding.btnPlusZoom, 100.0F)

        var animation: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)

        val zoomLevels = arrayOf(
            1F,
            0.95F,
            0.90F,
            0.85F,
            0.80F,
            0.75F,
            0.70F,
            0.65F,
            0.60F,
            0.55F,
            0.50F,
            0.45F,
            0.40F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F,
            0.4F
        )

        customContext = createPackageContext("com.odesar.mapabioculturalinteractivosandionisio", 0)
        assetManager = customContext.assets

        binding.imgMapa.doOnLayout {

            lifecycleScope.launch(Dispatchers.Main) {

                binding.zoomImage.engine.addListener(object : ZoomEngine.Listener {

                    override fun onUpdate(engine: ZoomEngine, matrix: Matrix) {

                        binding.framePadre.children.forEach {

                            if (it.tag == "containerInfo") {

                                val container = it as LinearLayout

                                container.pivotX = ((container.width / 2).toFloat())
                                container.pivotY = ((container.height / 2).toFloat())
                                container.scaleX = zoomLevels[binding.zoomImage.zoom.toInt()]
                                container.scaleY = zoomLevels[binding.zoomImage.zoom.toInt()]

                                if (binding.zoomImage.zoom > 6) {

                                    if (!isVisible(container)) {

                                        container.background = null
//                                        container.alpha = 0.6F
                                        ViewCompat.setZ(container.children.elementAt(1), 0F)
                                        container.children.elementAt(1).visibility = View.INVISIBLE
                                        ViewCompat.setZ(container, 2.0F)

                                    } else {

                                        container.background = ResourcesCompat.getDrawable(
                                            resources,
                                            R.drawable.lugar_mapa_background_active,
                                            null
                                        )
                                        container.children.elementAt(1).visibility = View.VISIBLE
//                                        container.alpha = 1F
                                        ViewCompat.setZ(container, 10F)

                                    }

                                } else {

                                    container.background = null
                                    container.children.elementAt(1).visibility = View.GONE
//                                    container.children.elementAt(1).z = 0F
                                    ViewCompat.setZ(container.children.elementAt(1), 0F)
//                                    container.alpha = 1F
                                    ViewCompat.setZ(container, 2.0F)

                                }

                            }


                        }

                    }

                    override fun onIdle(engine: ZoomEngine) {

                    }

                })

            }

            lifecycleScope.launch(Dispatchers.IO) {

                val lugares = db.lugaresDAO().getAll()

                addLugaresToMap(lugares, binding.imgMapa)

            }

        }

        binding.btnPlusZoom.setOnClickListener {

            binding.zoomImage.zoomBy(1.6F, true)
        }

        binding.btnMinusZoom.setOnClickListener {

            binding.zoomImage.zoomBy(0.6F, true)

        }

        binding.btnSearch.setOnClickListener {

            if (binding.eTxtBuscar.visibility == View.INVISIBLE) {

                binding.eTxtBuscar.visibility = View.VISIBLE
                binding.eTxtBuscar.animate().alpha(1.0F)
                binding.eTxtBuscar.requestFocus()
                showSoftKeyboard(this@MainActivity, binding.eTxtBuscar)

            }

            else {

                binding.eTxtBuscar.animate().alpha(0.0F)
                binding.eTxtBuscar.visibility = View.INVISIBLE
                binding.eTxtBuscar.text = null
                binding.framePadre.children.forEach {

                    if (it.tag == "containerInfo") {

                        val container = it as LinearLayout

                        it.visibility = View.VISIBLE

                    }

                }
                hideSoftKeyboard(this@MainActivity, binding.eTxtBuscar)
            }

        }

        binding.btnInfo.setOnClickListener {

            val dialogLeyendas = LeyendasDialog()
            dialogLeyendas.show(supportFragmentManager, "LeyendasDialog")

        }

        binding.btnExtraInfo.setOnClickListener{

            val dialogExtraInfo = ExtraInfoDialog()
            dialogExtraInfo.show(supportFragmentManager, "ExtraInfoDialog")

        }

        binding.eTxtBuscar.addTextChangedListener(object  : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                if (s.toString().isNotEmpty()) {

                    binding.framePadre.children.forEach {

                        if (it.tag == "containerInfo") {

                            val container = it as LinearLayout

                            if (container.children.elementAt(0).tag.toString().contains(s.toString(), true)) {

                                it.visibility = View.VISIBLE

                            }

                            else {

                                it.visibility = View.INVISIBLE

                            }

                        }

                    }

                }

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

            }

        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(window) {

            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

            enterTransition = Fade()

            exitTransition = Fade()

            enterTransition.duration = 1000

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    suspend fun addLugaresToMap(lugares: List<Lugares>, imgMapa: ImageView) {

        withContext(Dispatchers.Main) {

            ViewCompat.setZ(imgMapa, 1.0F)

            lugares.forEach { lugar ->

                withContext(Dispatchers.IO) {

                    val icono = Uri.parse("file:///android_asset/" + lugar.icono)

                    withContext(Dispatchers.Default) {

//                        val X = (lugar.coordX!! * imgMapa.width) / 100
//                        val Y = (lugar.coordY!! * imgMapa.height) / 100

                        val container = LinearLayout(this@MainActivity)
                        val iconoLugar = ImageView(this@MainActivity)
                        val textoLugar = TextView(this@MainActivity)

                        container.tag = "containerInfo"

                        withContext(Dispatchers.Main) {

                            binding.framePadre.addView(container)
                            container.addView(iconoLugar)
                            container.addView(textoLugar)

                            container.orientation = LinearLayout.VERTICAL
                            container.gravity = Gravity.CENTER_HORIZONTAL
                            ViewCompat.setZ(container, 2.0F)
                            container.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                            container.layoutParams.width = (imgMapa.width * 0.04).roundToInt()
                            container.pivotX = 0F
                            container.pivotY = 0F

                            iconoLugar.layoutParams.height = (imgMapa.height * 0.04).roundToInt()
                            iconoLugar.layoutParams.width = (imgMapa.width * 0.04).roundToInt()
                            iconoLugar.setPadding(6, 6, 6, 6)

                            val X = (((lugar.coordX!! / 100) * imgMapa.width) - (iconoLugar.layoutParams.width / 2))
                            val Y = (((lugar.coordY!! / 100) * imgMapa.height) - (iconoLugar.layoutParams.height / 2))

                            textoLugar.layoutParams.width = (imgMapa.width * 0.04).roundToInt()
                            textoLugar.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                            textoLugar.text = lugar.nombre
                            textoLugar.textSize = (imgMapa.width * 0.002).toFloat()
                            textoLugar.gravity = Gravity.CENTER_HORIZONTAL
                            textoLugar.setTextColor(Color.WHITE)
                            textoLugar.visibility = View.GONE
                            textoLugar.setPadding(6, 6, 6, 6)
                            ViewCompat.setZ(textoLugar, 2.0F)

                            container.x = X.toFloat()
                            container.y = Y.toFloat()

                            iconoLugar.tag = lugar.nombre
                            ViewCompat.setZ(iconoLugar, 2.0F)

                            when {

                                lugar.tamanioAdicional == 1 -> {

                                    container.layoutParams.width =
                                        (imgMapa.width * 0.07).roundToInt()

                                    iconoLugar.layoutParams.height =
                                        (imgMapa.height * 0.07).roundToInt()
                                    iconoLugar.layoutParams.width =
                                        (imgMapa.width * 0.07).roundToInt()

                                }

                            }

                            try {

                                Glide.with(this@MainActivity).load(icono)
                                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .into(iconoLugar)

                            } catch (e: Exception) {

                                println("Error en " + lugar.nombre)

                            }

                            container.setOnClickListener {

                                val dialogInfo = InformationDialog(lugar)
                                dialogInfo.show(supportFragmentManager, "InfoDialog")

                            }

                        }

                    }

                }

            }

        }

    }

    fun resetEverything() {

        binding.framePadre.removeAllViews()
        binding.zoomImage.engine.clear()
        binding.zoomImage.zoomTo(1.0F, false)

    }

    fun isVisible(view: View): Boolean {

        if (!view.isShown) {

            return false

        } else {

            val actualPosition = Rect()
            view.getGlobalVisibleRect(actualPosition)

            val screen = Rect(
                (Resources.getSystem().displayMetrics.widthPixels * 0.6).toInt(),
                (Resources.getSystem().displayMetrics.heightPixels * 0.6).toInt(),
                (Resources.getSystem().displayMetrics.widthPixels * 0.6).toInt(),
                (Resources.getSystem().displayMetrics.heightPixels * 0.6).toInt()
            )

//            var screen = Rect(0, 0, Resources.getSystem().displayMetrics.widthPixels, Resources.getSystem().displayMetrics.heightPixels)

            return actualPosition.intersect(screen)

        }

    }

    fun isVisibleWholeScreen(view: View): Boolean {

        if (!view.isShown) {

            return false

        } else {

            val actualPosition = Rect()
            view.getGlobalVisibleRect(actualPosition)

            val screen = Rect(
                0,
                0,
                Resources.getSystem().displayMetrics.widthPixels,
                Resources.getSystem().displayMetrics.heightPixels
            )

            return actualPosition.intersect(screen)

        }

    }

    fun hideSoftKeyboard(activity: Activity, view: View) {

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)

    }

    fun showSoftKeyboard(activity: Activity, view: View) {

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    }

}