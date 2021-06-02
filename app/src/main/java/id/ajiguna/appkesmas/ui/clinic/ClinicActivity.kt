package id.ajiguna.appkesmas.ui.clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ActivityClinicBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicActivity : AppCompatActivity() {

    private lateinit var clinicBinding: ActivityClinicBinding
    private var list = ArrayList<ClinicResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clinicBinding = ActivityClinicBinding.inflate(layoutInflater)
        clinicBinding.root

        clinicBinding.progressBar.visibility = View.VISIBLE
        ApiConfig.getApiService().getClinic()
                .enqueue(object :
                        Callback<List<ClinicResponse>> {
                    override fun onResponse(
                            call: Call<List<ClinicResponse>>,
                            response: Response<List<ClinicResponse>>
                    ) {
                        //Tulis code jika response sukses
                        if (response.code() == 200) {
                            clinicBinding.progressBar.visibility = View.GONE
                            list = response.body() as ArrayList<ClinicResponse>
                            clinicBinding.rvClinic.layoutManager =
                                    LinearLayoutManager(this@ClinicActivity)
                            val listClinicAdapter = ClinicAdapter(list)
                            clinicBinding.rvClinic.adapter = listClinicAdapter
                            listClinicAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<ClinicResponse>>, t: Throwable) {
                    }
                })
    }
}