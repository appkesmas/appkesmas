package id.ajiguna.appkesmas.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.databinding.ActivityHospitalBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalActivity : AppCompatActivity() {

    private lateinit var hospitalBinding: ActivityHospitalBinding
    private var list = ArrayList<HospitalResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalBinding = ActivityHospitalBinding.inflate(layoutInflater)
        setContentView(hospitalBinding.root)

        supportActionBar?.title = getString(R.string.hospital)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        hospitalBinding.progressBar.visibility = View.VISIBLE
        ApiConfig.getApiService().getHospital()
                .enqueue(object :
                    Callback<List<HospitalResponse>> {
                    override fun onResponse(
                        call: Call<List<HospitalResponse>>,
                        response: Response<List<HospitalResponse>>
                    ) {
                        //Tulis code jika response sukses
                        if (response.code() == 200) {
                            hospitalBinding.progressBar.visibility = View.GONE
                            list = response.body() as ArrayList<HospitalResponse>
                            hospitalBinding.rvHospital.layoutManager =
                                LinearLayoutManager(this@HospitalActivity)
                            val listHospitalAdapter = HospitalAdapter(list)
                            hospitalBinding.rvHospital.adapter = listHospitalAdapter
                            listHospitalAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<HospitalResponse>>, t: Throwable) {
                        Toast.makeText(this@HospitalActivity, "g enek", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}