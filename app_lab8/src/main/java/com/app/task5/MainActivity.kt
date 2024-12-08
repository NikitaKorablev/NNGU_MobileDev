package com.app.task5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainTask5Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask5Binding
    private val PICK_VIDEO_REQUEST = 2
    private var videoIsStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.filePicker.setOnClickListener(this::openFilePicker)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
        binding.btnPlay.setOnClickListener {onBtnPlayClicked()}
    }

    private fun onBtnPlayClicked() {
        if (videoIsStarted) {
            binding.videoView.pause()
            binding.btnPlay.text = "Play"
        } else {
            binding.videoView.start()
            binding.btnPlay.text = "Pause"
        }

        videoIsStarted = !videoIsStarted
    }

    private fun openFilePicker(view: View?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_VIDEO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedAudioUri: Uri? = data.data
            if (selectedAudioUri != null) {
                binding.videoView.setVideoURI(selectedAudioUri)
                binding.videoView.start()
                onBtnPlayClicked()
            }
        }
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.stopPlayback()
    }
}