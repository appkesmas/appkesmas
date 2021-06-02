package id.ajiguna.appkesmas.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import id.ajiguna.appkesmas.MainActivity
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        splashBinding.root

        val animation =
                AnimationUtils.loadAnimation(this, R.anim.mytransition)
        splashBinding.icApp.startAnimation(animation)
        splashBinding.tvApp.startAnimation(animation)
        splashBinding.tvDesc.startAnimation(animation)
        val i = Intent(this@SplashActivity, MainActivity::class.java)
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(i)
                    finish()
                }
            }
        }
        timer.start()
    }
}