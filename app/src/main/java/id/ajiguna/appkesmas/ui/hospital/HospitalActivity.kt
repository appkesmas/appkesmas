package id.ajiguna.appkesmas.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ActivityHospitalBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalActivity : AppCompatActivity() {

    private lateinit var hospitalBinding: ActivityHospitalBinding
    private var list = ArrayList<ClinicResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalBinding = ActivityHospitalBinding.inflate(layoutInflater)
        hospitalBinding.root

        hospitalBinding.progressBar.visibility = View.VISIBLE
        ApiConfig.getApiService().getClinic()
                .enqueue(object :
                        Callback<List<ClinicResponse>> {
                    override fun onResponse(
                            call: Call<List<ClinicResponse>>,
                            response: Response<List<ClinicResponse>>
                    ) {
                        //Tulis code jika response sukses
                        if (response.code() == 200) {
                            hospitalBinding.progressBar.visibility = View.GONE
                            list = response.body() as ArrayList<ClinicResponse>
                            hospitalBinding.rvHospital.layoutManager =
                                    LinearLayoutManager(this@HospitalActivity)
                            val listClinicAdapter = ClinicAdapter(list)
                            hospitalBinding.rvHospital.adapter = listClinicAdapter
                            listClinicAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<ClinicResponse>>, t: Throwable) {
                    }
                })
    }
}