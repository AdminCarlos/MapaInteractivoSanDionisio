package com.odesar.mapabioculturalinteractivosandionisio.Fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.odesar.mapabioculturalinteractivosandionisio.Entities.InformacionAdicional
import com.odesar.mapabioculturalinteractivosandionisio.R
import kotlinx.android.synthetic.main.fragment_info_tab.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream

class InfoTabFragment(var infoAdicional : InformacionAdicional) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.txtInfoTab.text = infoAdicional.texto

        val customContext =
            requireActivity().createPackageContext("com.odesar.mapabioculturalinteractivosandionisio", 0)
        val assetManager = customContext.assets

        val imageSource = BufferedInputStream(assetManager.open(infoAdicional.imagen!!))

        val options = BitmapFactory.Options()

        lifecycleScope.launch(Dispatchers.Default) {

            var sampleSize = 0

            options.inJustDecodeBounds = true
            val prevImage =
                BitmapFactory.decodeStream(imageSource, null, options)

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

            val newImage = BufferedInputStream(assetManager.open(infoAdicional.imagen!!))

            val finalImage =
                BitmapFactory.decodeStream(newImage, null, newOption)

            withContext(Dispatchers.Main) {

                Glide.with(requireActivity()).asBitmap().load(finalImage)
                    .skipMemoryCache(true).diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    ).into(view.imgTab)

                newImage.close()
                imageSource.close()

            }

        }

    }
}