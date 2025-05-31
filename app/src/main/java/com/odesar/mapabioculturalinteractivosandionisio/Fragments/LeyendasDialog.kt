package com.odesar.mapabioculturalinteractivosandionisio.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.core.view.doOnLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.odesar.mapabioculturalinteractivosandionisio.Database.AppDatabase
import com.odesar.mapabioculturalinteractivosandionisio.R
import com.odesar.mapabioculturalinteractivosandionisio.databinding.DialogInfoBinding
import com.odesar.mapabioculturalinteractivosandionisio.databinding.DialogLeyendasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt


class LeyendasDialog() : DialogFragment() {

    private var _binding : DialogLeyendasBinding? = null
    private val binding get() = _binding!!

    val width = Resources.getSystem().displayMetrics.widthPixels
    val height = Resources.getSystem().displayMetrics.heightPixels
    lateinit var contexto: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0F)
        dialog?.window?.setGravity(GravityCompat.END)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        _binding = DialogLeyendasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contexto = requireActivity()

        val db = AppDatabase.getDatabase(contexto)

        binding.txtNombresLeyenda.text = "Leyendas"

        lifecycleScope.launch(Dispatchers.IO) {

            val listaCategorias = db.categoriasDAO().getAllCategorias()
            val listaLeyendas = db.leyendasDAO().getAllLeyendas()

            withContext(Dispatchers.Main) {

                listaCategorias.forEach { categoria ->

                    val container = LinearLayout(contexto)
                    container.orientation = LinearLayout.HORIZONTAL
                    container.gravity = Gravity.CENTER
                    container.setBackgroundColor(Color.parseColor("#000000"))

                    binding.layoutPadreDialogLeyendas.addView(container)

                    val labelCategoria = Button(contexto)
                    labelCategoria.setText(categoria.nombre)
                    labelCategoria.gravity = Gravity.CENTER

                    if (categoria.icono != null) {

                        val iconDrawable = Drawable.createFromResourceStream(
                            resources,
                            TypedValue(),
                            resources.assets.open(categoria.icono!!),
                            null
                        )

                        iconDrawable!!.setBounds(0, 0, (width * 0.1).toInt(), (width * 0.1).toInt())

//                        container.setPadding(70, 0, 70, 0)

                        labelCategoria.setCompoundDrawables(iconDrawable, null, null, null)

                    }

                    else {

                        labelCategoria.setCompoundDrawables(null, null, null, null)

                    }

                    container.addView(labelCategoria)

                    labelCategoria.layoutParams.width = LayoutParams.WRAP_CONTENT
//                    labelCategoria.textSize = (width * 0.025).toFloat()
//                    labelCategoria.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    labelCategoria.gravity = Gravity.CENTER
//                    labelCategoria.setPadding(0, (height * 0.015).toInt(), 0, (height * 0.015).toInt())

                    labelCategoria.setBackgroundColor(Color.parseColor("#000000"))
                    labelCategoria.setTextColor(Color.parseColor("#FFFFFF"))

                    val childContainer = GridLayout(contexto)
                    childContainer.orientation = GridLayout.HORIZONTAL
                    childContainer.columnCount = 4
                    childContainer.id = categoria.id!!

                    binding.layoutPadreDialogLeyendas.addView(childContainer)

                    childContainer.layoutParams.width = (width * 0.75).roundToInt()

                }

                listaLeyendas.forEach { leyenda ->

                    val container = LinearLayout(contexto)
                    container.orientation = LinearLayout.VERTICAL

                    val labelLeyenda = TextView(contexto)
                    labelLeyenda.setText(leyenda.nombre)

                    val padre = binding.layoutPadreDialogLeyendas.findViewById<GridLayout>(leyenda.categoria!!.toInt())
                    padre.addView(container)

                    container.layoutParams.width = ((width * 0.75) * 0.25).toInt()
                    container.layoutParams.height = (height * 0.18).toInt()

                    val iconLeyenda = ImageView(contexto)

                    container.addView(iconLeyenda)
                    container.doOnLayout {

                        iconLeyenda.layoutParams.width = ((width * 0.75) * 0.20).toInt()
                        iconLeyenda.layoutParams.height = (height * 0.1).toInt()
                        container.gravity = Gravity.CENTER_HORIZONTAL

                        Glide.with(contexto).load(Uri.parse("file:///android_asset/" + leyenda.rutaIcono)).fitCenter().into(iconLeyenda)

                        container.addView(labelLeyenda)
                        labelLeyenda.layoutParams.width = container.width
                        labelLeyenda.layoutParams.height = (height * 0.08).toInt()
                        labelLeyenda.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        labelLeyenda.gravity = Gravity.CENTER_HORIZONTAL
                        labelLeyenda.textSize = (width * 0.01).toFloat()
                        labelLeyenda.setTextColor(Color.parseColor("#FFFFFF"))

                    }

                }

            }

        }

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog!!.window!!.setLayout(
            (width * 0.75).roundToInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

}