package org.odesar.mapa_biocultural_interactivo_san_dionisio.Fragments

import android.annotation.SuppressLint
import android.content.Context
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
import org.odesar.mapa_biocultural_interactivo_san_dionisio.R
import org.odesar.mapa_biocultural_interactivo_san_dionisio.databinding.FragmentMediaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedInputStream

class MediaFragment(val src : String) : DialogFragment() {

    private var _binding : FragmentMediaBinding? = null
    private val binding get() = _binding!!

    lateinit var contexto: Context
    val statusBarHeight = Resources.getSystem().getDimensionPixelSize(
        Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
    )
    val width = Resources.getSystem().displayMetrics.widthPixels
    val height = Resources.getSystem().displayMetrics.heightPixels - statusBarHeight

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0F)
        dialog?.window?.setGravity(GravityCompat.END)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contexto = requireActivity()

        val customContext =
            contexto.createPackageContext("org.odesar.mapa_biocultural_interactivo_san_dionisio", 0)
        val assetManager = customContext.assets

        val videoFile = createTempFile()
        val videoStream = BufferedInputStream(assetManager.open(src))

        videoStream.use { input ->

            videoFile.outputStream().use { output ->

                input.copyTo(output)

            }

        }

        lifecycleScope.launch(Dispatchers.Main) {
            /*val player = ExoPlayer.Builder(requireContext()).build().also { exoPlayer ->

                val videoSource = MediaItem.fromUri(videoFile.toURI().toString())
                exoPlayer.setMediaItem(videoSource)
                videoPlayer.player = exoPlayer
                exoPlayer.play()

            }*/

            /*val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoPlayer)
            mediaController.setMediaPlayer(videoPlayer)
            ViewCompat.setZ(mediaController, 100.0F)
            mediaController.isEnabled = true
            mediaController.show()

            videoPlayer.setMediaController(mediaController)*/
            binding.videoPlayer.setVideoPath(videoFile.path)
            binding.videoPlayer.start()
        }

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.BLACK))

    }
}