package id.ajiguna.appkesmas.ui.antrian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.HistoryResponse
import id.ajiguna.appkesmas.core.network.response.QueueResponse

import id.ajiguna.appkesmas.databinding.ActivityAntrianBinding
import id.ajiguna.appkesmas.ui.history.HistoryAdapter
import id.ajiguna.appkesmas.ui.patient.RegisterPatientActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit


class AntrianActivity : AppCompatActivity() {

    private lateinit var waitBinding: ActivityAntrianBinding
    var textView: TextView? = null
    private var idQueue: String = ""

    companion object {
        const val EXTRA_QUEUE = "extra_queue"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        waitBinding= ActivityAntrianBinding.inflate(layoutInflater)
        setContentView(waitBinding.root)

        idQueue = intent.extras?.getString(EXTRA_QUEUE).toString()

        getData()
    }

    private fun getData() {
        ApiConfig.getApiService().getQueue(idQueue)
            .enqueue(object :
                Callback<QueueResponse> {
                override fun onResponse(
                    call: Call<QueueResponse>,
                    response: Response<QueueResponse>
                ) {
                    //Tulis code jika response sukses
                    if (response.code() == 200) {
                        val prediction = response.body()?.data?.predictionTime!!.toLong()

                        val duration = TimeUnit.MINUTES.toMillis(prediction)
                        object : CountDownTimer(duration, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                val sDuration = String.format(
                                    Locale.ENGLISH,
                                    "%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                                )
                                with(waitBinding) {
                                    txtTime.text = sDuration
                                }
                            }

                            override fun onFinish() {
                                Toast.makeText(
                                    applicationContext, "Waktu Tunggu Selesai", Toast.LENGTH_LONG
                                ).show()
                            }
                        }.start()
                    }
                }

                override fun onFailure(call: Call<QueueResponse>, t: Throwable) {
                }
            })
    }
}