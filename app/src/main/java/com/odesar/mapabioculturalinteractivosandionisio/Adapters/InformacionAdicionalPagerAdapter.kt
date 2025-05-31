package com.odesar.mapabioculturalinteractivosandionisio.Adapters

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.odesar.mapabioculturalinteractivosandionisio.Entities.InformacionAdicional
import com.odesar.mapabioculturalinteractivosandionisio.Fragments.ExtraInfoDialog
import com.odesar.mapabioculturalinteractivosandionisio.Fragments.InfoTabFragment

class InformacionAdicionalPagerAdapter(fa : DialogFragment, var listInformacion : List<InformacionAdicional>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return listInformacion.size
    }

    override fun createFragment(position: Int): Fragment = InfoTabFragment(listInformacion[position])

}