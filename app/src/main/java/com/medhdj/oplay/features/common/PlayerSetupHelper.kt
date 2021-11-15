package com.medhdj.oplay.features.common

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.medhdj.core.platform.LifecycleEventDispatcher
import timber.log.Timber

object PlayerSetupHelper {
    private var _player: ExoPlayer? = null
    val player: ExoPlayer?
        get() = _player

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    private val playbackStateListener = playbackStateListener()

    fun setUp(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        playerView: PlayerView
    ) {
        LifecycleEventDispatcher(
            lifecycleOwner,
            onStart = {

                if (Util.SDK_INT >= Build.VERSION_CODES.N) {
                    initializePlayer(context, playerView)
                }
            },
            onResume = {
//                hideSystemUi(playerView)
                if ((Util.SDK_INT < Build.VERSION_CODES.N || _player == null)) {
                    initializePlayer(context, playerView)
                }
            },
            onPause = {
                if (Util.SDK_INT < Build.VERSION_CODES.N) {
                    releasePlayer()
                }
            },
            onStop = {
                if (Util.SDK_INT >= Build.VERSION_CODES.N) {
                    releasePlayer()
                }
            }
        )
    }

    fun handleOrientationChange(
        window: Window,
        orientation: Int,
        playerView: PlayerView,
        fullScreenPlayerView: PlayerView
    ) {
        player?.let { theExoplayer ->
            if (orientation === Configuration.ORIENTATION_LANDSCAPE) {
                PlayerView.switchTargetView(theExoplayer, playerView, fullScreenPlayerView)
                fullScreenPlayerView?.isVisible = true
                hideSystemUI(window, fullScreenPlayerView)

            } else if (orientation === Configuration.ORIENTATION_PORTRAIT) {
                PlayerView.switchTargetView(theExoplayer, fullScreenPlayerView, playerView)
                fullScreenPlayerView?.isVisible = false
                showSystemUI(window, playerView)
            }
        }
    }

    // initialization
    private fun initializePlayer(context: Context, playerView: PlayerView) {
        val trackSelector = DefaultTrackSelector(context)
        _player = ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                playerView.player = exoPlayer
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()

                exoPlayer.addListener(playbackStateListener)
            }
    }

    private fun releasePlayer() {
        _player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentWindowIndex
            playWhenReady = exoPlayer.playWhenReady

            exoPlayer.removeListener(playbackStateListener)

            exoPlayer.release()
        }
        _player = null
    }

    // UI
    private fun hideSystemUI(window: Window, view: View) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, view).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI(window: Window, view: View) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(
            window,
            view
        ).show(WindowInsetsCompat.Type.systemBars())
    }

    // listeners
    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Timber.d("changed state to $stateString")
        }
    }
}
