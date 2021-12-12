package com.github.johnnysc.ecp.data.core

import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

interface SoundPlayer {
    fun play(url:String)

    fun stop()

    class BaseSoundPlayer(private val player : ExoPlayer) : SoundPlayer{
        override fun play(url: String) {
            val media = MediaItem.fromUri(Uri.parse(url))
            player.run {
                setMediaItem(media)
                prepare()
                play()
            }
        }

        override fun stop() {
            player.run {
                stop()
                release()
            }
        }
    }
}