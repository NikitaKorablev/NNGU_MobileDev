package com.app.task4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.R


//import com.app.databinding.ActivityMainTask4Binding

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private val handler = Handler(Looper.getMainLooper())
    private var playerIsStarted = false
    private val PICK_AUDIO_REQUEST = 1
    private val REQUEST_PERMISSION_CODE = 1
    private val updateSeekBarRunnable = object : Runnable {
        override fun run() {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    seekBar.progress = it.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }
        }
    }
    private val audioQueue = mutableListOf<Uri>()
    private var currentTrackIndex = 0

    private lateinit var playPause: Button
    private lateinit var seekBar: SeekBar
    private lateinit var songName: TextView
    private lateinit var filePicker: Button
    private lateinit var prev: Button
    private lateinit var next: Button
    private lateinit var mainMenuButton: Button
    private lateinit var listView: ListView
    private lateinit var songAdapter: SongAdapter
    private val songList = ArrayList<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task4)
        playPause = findViewById(R.id.playPause)
        seekBar = findViewById(R.id.seekBar)
        songName = findViewById(R.id.songName)
        filePicker = findViewById(R.id.filePicker)
        prev = findViewById(R.id.prev)
        next = findViewById(R.id.next)
        mainMenuButton = findViewById(R.id.mainMenuButton)
        listView = findViewById(R.id.listView)
        songAdapter = SongAdapter(this, songList)
        listView.adapter = songAdapter

        checkPermissions()

        filePicker.setOnClickListener(this::openFilePicker)
        playPause.setOnClickListener {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.pause()
                    playPause.text = "Play"
                } else {
                    it.start()
                    seekBar.max = it.duration
                    handler.post(updateSeekBarRunnable)
                    playPause.text = "Pause"
                }
                playerIsStarted = it.isPlaying
            }
        }
        prev.setOnClickListener{playPrevTrack()}
        next.setOnClickListener{playNextTrack()}

        mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun openFilePicker(view: View?) {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, PICK_AUDIO_REQUEST)

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "audio/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_AUDIO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.clipData != null) {
                for (i in 0 until data.clipData!!.itemCount) {
                    val selectedAudioUri: Uri = data.clipData!!.getItemAt(i).uri
                    audioQueue.add(selectedAudioUri)
                    songList.add(Song(getSongName(selectedAudioUri), false))
                }
            } else if (data.data != null) {
                val selectedAudioUri: Uri = data.data!!
                audioQueue.add(selectedAudioUri)
                songList.add(Song(getSongName(selectedAudioUri), false))
            }

            if (audioQueue.isNotEmpty()) {
                songName.text = getSongName(audioQueue[0])
                playTrack(audioQueue[0])
                songAdapter.setCurrentPlayingPosition(0)
                songAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun playTrack(uri: Uri) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(this@MainActivity, uri)
            prepare()
            setOnCompletionListener {
                playNextTrack()
            }
        }
        seekBar.max = mediaPlayer?.duration ?: 0
    }

    private fun playPrevTrack() {
        if (currentTrackIndex-1 >= 0) {
            currentTrackIndex--
            mediaPlayer?.pause()
            playerIsStarted = false
            playPause.text = "Play"
            playTrack(audioQueue[currentTrackIndex])
            songAdapter.setCurrentPlayingPosition(currentTrackIndex)
            songName.text = getSongName(audioQueue[currentTrackIndex])
        }
    }

    private fun playNextTrack() {
        if (currentTrackIndex < audioQueue.size - 1) {
            currentTrackIndex++
            mediaPlayer?.pause()
            playerIsStarted = false
            playPause.text = "Play"
            playTrack(audioQueue[currentTrackIndex])
            songAdapter.setCurrentPlayingPosition(currentTrackIndex)
            songName.text = getSongName(audioQueue[currentTrackIndex])
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
            seekBar.progress = 0
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