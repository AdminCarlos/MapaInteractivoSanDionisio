package com.odesar.mapabioculturalinteractivosandionisio.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.odesar.mapabioculturalinteractivosandionisio.Database.AppDatabase
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Lugares
import com.odesar.mapabioculturalinteractivosandionisio.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_info.*
import kotlinx.android.synthetic.main.dialog_info.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.InputStream
import kotlin.math.roundToInt

class InfoDialog(var lugar: Lugares) : DialogFragment() {

    lateinit var contexto: Context
    val statusBarHeight = Resources.getSystem().getDimensionPixelSize(
        Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
    )
    val width = Resources.getSystem().displayMetrics.widthPixels
    val height = Resources.getSystem().displayMetrics.heightPixels - statusBarHeight

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0F)
        dialog?.window?.setGravity(GravityCompat.END)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        return inflater.inflate(R.layout.dialog_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contexto = requireActivity()

        val customContext =
            contexto.createPackageContext("com.odesar.mapabioculturalinteractivosanramn", 0)
        val assetManager = customContext.assets

        val db = AppDatabase.getDatabase(contexto)

        view.txtNombre.setText(lugar.nombre)
        view.txtDescripcion.text = lugar.descripcion

        if (lugar.imagenAdicional == 1) {

            lifecycleScope.launch(Dispatchers.IO) {

                val imagenes = db.imagenAdicionalDAO().getImagenesAdicionalesByLugar(lugar.id!!)

                val options = BitmapFactory.Options()

                withContext(Dispatchers.Main) {

                    imagenes.forEach {

                        val iconoLugar = ImageView(contexto)

                        iconoLugar.tag = "IMAGEN"

                        layoutPadreDialog.addView(iconoLugar)

                        iconoLugar.setPadding(0, 40, 0, 0)

                        val thumbnailLugar = TextView(contexto)
                        layoutPadreDialog.addView(thumbnailLugar)
                        thumbnailLugar.text = it.thumbnail
                        thumbnailLugar.setTextColor(ContextCompat.getColor(contexto,R.color.white))
                        thumbnailLugar.textSize = 17.0F
                        thumbnailLugar.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        thumbnailLugar.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                        thumbnailLugar.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

                        withContext(Dispatchers.IO) {

                            try {

                                val imageSource = BufferedInputStream(assetManager.open(it.src!!))

                                withContext(Dispatchers.Default) {

                                    var sampleSize = 0

                                    options.inJustDecodeBounds = true
                                    val prevImage =
                                        BitmapFactory.decodeStream(imageSource, null, options)

                                    println(options.outWidth.toString() + " : " + options.outHeight.toString())

                                    when {

                                        (options.outWidth > 3000) || (options.outHeight > 3000) -> {

                                            sampleSize = 6

                                        }

                                        (options.outWidth > 2000 && options.outWidth < 3000) || (options.outHeight > 2000 && options.outHeight < 3000) -> {

                                            sampleSize = 4

                                        }

                                        (options.outWidth > 1000 && options.outWidth < 2000) || (options.outHeight > 1000 && options.outHeight < 2000) -> {

                                            sampleSize = 2

                                        }

                                        else -> {

                                            sampleSize = 1

                                        }

                                    }

                                    val newOption = BitmapFactory.Options()
                                    newOption.inSampleSize = sampleSize

                                    val newImage = BufferedInputStream(assetManager.open(it.src!!))

                                    val finalImage =
                                        BitmapFactory.decodeStream(newImage, null, newOption)

                                    withContext(Dispatchers.Main) {

                                        Glide.with(this@InfoDialog).asBitmap().load(finalImage)
                                            .skipMemoryCache(true).diskCacheStrategy(
                                                DiskCacheStrategy.NONE
                                            ).into(iconoLugar)

                                        newImage.close()
                                        imageSource.close()

                                    }

                                }

                            } catch (e: Throwable) {

                                println(e)

                            }

                        }

                    }

                }


            }

        }

    }

    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog!!.window!!.setLayout(
            (width * 0.7).roundToInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    fun checkWidth(imageStream: InputStream): Int {

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(imageStream, null, options)

        imageStream.close()

        return options.outWidth

    }

}