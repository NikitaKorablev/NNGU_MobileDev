package com.app.task4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.databinding.ActivityMainTask4Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask4Binding
    private var mediaPlayer: MediaPlayer? = null
    private val handler = Handler(Looper.getMainLooper())
    private var playerIsStarted = false
    private val PICK_AUDIO_REQUEST = 1
    private val REQUEST_PERMISSION_CODE = 1
    private val updateSeekBarRunnable = object : Runnable {
        override fun run() {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    binding.seekBar.progress = it.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()

        binding.filePicker.setOnClickListener(this::openFilePicker)
        binding.playPause.setOnClickListener {
            mediaPlayer?.let {
                if (!playerIsStarted) {
                    it.start()
                    binding.seekBar.max = it.duration
                    handler.post(updateSeekBarRunnable)
                    binding.playPause.text = "Pause"
                } else {
                    it.pause()
                    binding.playPause.text = "Play"
                }

                playerIsStarted = !playerIsStarted
            }
        }

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun openFilePicker(view: View?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_AUDIO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedAudioUri: Uri? = data.data
            if (selectedAudioUri != null) {
                mediaPlayer = MediaPlayer.create(this, selectedAudioUri)
                binding.seekBar.max = mediaPlayer?.duration ?: 0
                binding.songName.text = getSongName(selectedAudioUri)
            }
        }
    }

    private fun checkPermissions() {
        // Проверка наличия разрешений
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Запрос разрешений
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_PERMISSION_CODE
            )
        } else {
            Log.d("MainActivity", "Permission already granted")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("MainActivity", "Permission granted")
            } else {
                Log.e("MainActivity", "Permission denied")
            }
        }
    }

    private fun getSongName(uri: Uri?): String {
        var songName = "Unknown"
        if (uri == null) return songName

        songName = uri.lastPathSegment ?: "Unknown"
        return songName
    }

    private fun onStopMediaPlayerEvent() {
        mediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.prepareAsync()
            binding.seekBar.progress = 0
            handler.removeCallbacks(updateSeekBarRunnable)
        }
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        onStopMediaPlayerEvent()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}