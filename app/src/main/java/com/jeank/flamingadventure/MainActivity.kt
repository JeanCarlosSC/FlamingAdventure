package com.jeank.flamingadventure

import android.R
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jeank.flamingadventure.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide notification bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        // Example of a call to a native method
        //binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_dialog_alert)
            .setTitle("Confirm exit")
            .setMessage("Do you want to quit the game?")
            .setPositiveButton(
                "Okay"
            ) { dialog, which -> finish() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                // Envolvente fijo
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    /**
     * Called when the user taps the exit button
     */
    fun showExitDialog(view: View) {
        onBackPressed()
    }

    /**
     * Called when the user taps the editor button
     */
    fun toMyLevelsActivity(view: View){
        val intent = Intent(this, MyLevelsActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

}