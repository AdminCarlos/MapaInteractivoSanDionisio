package com.odesar.mapabioculturalinteractivosandionisio.Fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.odesar.mapabioculturalinteractivosandionisio.Adapters.InformacionAdicionalPagerAdapter
import com.odesar.mapabioculturalinteractivosandionisio.Database.AppDatabase
import com.odesar.mapabioculturalinteractivosandionisio.Entities.InformacionAdicional
import com.odesar.mapabioculturalinteractivosandionisio.R
import com.odesar.mapabioculturalinteractivosandionisio.databinding.DialogExtraInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class ExtraInfoDialog : DialogFragment() {

    private var _binding : DialogExtraInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0F)
        dialog?.window?.setGravity(GravityCompat.END)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        _binding = DialogExtraInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getDatabase(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {

            val listTabs = db.informacionAdicionalDAO().getAllInformacionAdicional()

            withContext(Dispatchers.Main) {

                //Instanciar viewpager y falta crear el fragment que creara el viewpager, NO ES ESTE
                val vwPager = binding.viewPager2Textos
                val pagerAdapter = InformacionAdicionalPagerAdapter(this@ExtraInfoDialog, listTabs)

                vwPager.adapter = pagerAdapter

                TabLayoutMediator(binding.tabLayoutTitulos, vwPager) { tab, position ->

                    tab.text = listTabs[position].nombre

                }.attach()

            }

        }

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var width = Resources.getSystem().displayMetrics.widthPixels
        var height = Resources.getSystem().displayMetrics.heightPixels

        dialog!!.window!!.setLayout(
            (width * 1.0).roundToInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

}