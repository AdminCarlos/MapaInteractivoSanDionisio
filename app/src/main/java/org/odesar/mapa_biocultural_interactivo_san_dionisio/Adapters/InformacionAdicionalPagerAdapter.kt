package org.odesar.mapa_biocultural_interactivo_san_dionisio.Adapters

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.InformacionAdicional
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Fragments.InfoTabFragment

class InformacionAdicionalPagerAdapter(fa : DialogFragment, var listInformacion : List<InformacionAdicional>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return listInformacion.size
    }

    override fun createFragment(position: Int): Fragment = InfoTabFragment(listInformacion[position])

}