package id.ajiguna.appkesmas.ui.antrian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast

import id.ajiguna.appkesmas.databinding.ActivityAntrianBinding
import java.util.*
import java.util.concurrent.TimeUnit


class AntrianActivity : AppCompatActivity() {
    private lateinit var waitBinding: ActivityAntrianBinding
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        waitBinding= ActivityAntrianBinding.inflate(layoutInflater)
        setContentView(waitBinding.root)

        val duration = TimeUnit.MINUTES.toMillis(1)
        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sDuration = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(1) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1))
                )
                with(textView) {
                    this!!.setText(sDuration)
                }
            }

            override fun onFinish() {
                with(textView) { this!!.setVisibility(View.GONE) }
                Toast.makeText(
                    applicationContext, "CountDown berhenti", Toast.LENGTH_LONG
                ).show()
            }
        }.start()
    }
}