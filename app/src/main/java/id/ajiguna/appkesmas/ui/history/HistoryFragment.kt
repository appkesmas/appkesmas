package id.ajiguna.appkesmas.ui.history

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding
import id.ajiguna.appkesmas.databinding.FragmentHomeBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicActivity
import id.ajiguna.appkesmas.ui.hospital.HospitalActivity
import id.ajiguna.appkesmas.ui.hospital.HospitalAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyBinding: FragmentHistoryBinding
    private var list = ArrayList<HospitalResponse>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        historyBinding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return historyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            ApiConfig.getApiService().getHospital()
                .enqueue(object :
                    Callback<List<HospitalResponse>> {
                    override fun onResponse(
                        call: Call<List<HospitalResponse>>,
                        response: Response<List<HospitalResponse>>
                    ) {
                        //Tulis code jika response sukses
                        if (response.code() == 200) {
                            historyBinding.shimmerFrameLayout.stopShimmer()
                            historyBinding.shimmerFrameLayout.visibility = View.GONE
                            historyBinding.rvHospital.visibility = View.VISIBLE
                            list = response.body() as ArrayList<HospitalResponse>
                            historyBinding.rvHospital.layoutManager =
                                LinearLayoutManager(activity)
                            val listHospitalAdapter = HospitalAdapter(list)
                            historyBinding.rvHospital.adapter = listHospitalAdapter
                            listHospitalAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<HospitalResponse>>, t: Throwable) {
                        Toast.makeText(activity, "g enek", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    override fun onResume() {
        super.onResume()
        historyBinding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        historyBinding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }
}