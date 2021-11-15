package com.medhdj.oplay.features.programs.details

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.medhdj.oplay.databinding.FragmentProgramDetailsBinding
import com.medhdj.oplay.features.common.PlayerSetupHelper
import com.medhdj.oplay.features.common.PlayerSetupHelper.player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramDetailsFragment : Fragment() {

    private var _binding: FragmentProgramDetailsBinding? = null
    private val binding get() = _binding!!

    private val programDetailsViewModel by viewModels<ProgramDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProgramDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = programDetailsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindPlayer()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerSetupHelper.handleOrientationChange(
            requireActivity().window,
            newConfig.orientation,
            binding.playerView,
            binding.fullScreenPlayerView
        )
    }

    private fun FragmentProgramDetailsBinding.bindPlayer() {
        PlayerSetupHelper.setUp(requireContext(), viewLifecycleOwner, playerView)
        playBtn.setOnClickListener {
            playBtn.isVisible = false
            programFullImage.isInvisible = true
            programDetailsViewModel.programDetailsData.value?.mediaItem?.let {
                player?.setMediaItem(it, player?.contentPosition ?: 0)
                player?.play()
            }
        }
    }
}
