package id.ajiguna.appkesmas.ui.home

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import id.ajiguna.appkesmas.MainActivity
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.CovidResponse
import id.ajiguna.appkesmas.core.utils.GridSpacingItemDecoration
import id.ajiguna.appkesmas.databinding.FragmentHomeBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicActivity
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import id.ajiguna.appkesmas.ui.hospital.HospitalActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding

    private var listMost = ArrayList<ClinicResponse>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            getCovid()

            homeBinding.cvHospital.setOnClickListener {
                val intent = Intent(activity, HospitalActivity::class.java)
                startActivity(intent)
            }

            homeBinding.cvClinic.setOnClickListener {
                val intent = Intent(activity, ClinicActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getCategory(){
        val layoutManagerfn: RecyclerView.LayoutManager =
                LinearLayoutManager(activity)
        homeBinding.rvMost.layoutManager = layoutManagerfn
        (layoutManagerfn as LinearLayoutManager).orientation =
                LinearLayoutManager.HORIZONTAL
        homeBinding.rvMost.addItemDecoration(
                GridSpacingItemDecoration(
                        10, dpToPx(10),
                        true
                )
        )
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(homeBinding.rvMost)

        ApiConfig.getApiService().getClinic().enqueue(object :
                Callback<List<ClinicResponse>> {
            override fun onResponse(call: Call<List<ClinicResponse>>, response: Response<List<ClinicResponse>>) {
                //Tulis code jika response sukses
                if(response.code() == 200) {
                    listMost = response.body() as ArrayList<ClinicResponse>
                    val mostAdapter = MostAdapter(listMost)
                    homeBinding.rvMost.adapter = mostAdapter

                }
            }
            override fun onFailure(call: Call<List<ClinicResponse>>, t: Throwable){
                Toast.makeText(activity, "Belum ada data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCovid(){
        ApiConfig.getApiCovid().getCovid().enqueue(object :
            Callback<List<CovidResponse>> {
            override fun onResponse(call: Call<List<CovidResponse>>, response: Response<List<CovidResponse>>) {
                //Tulis code jika response sukses
                if(response.code() == 200) {
                    homeBinding.tvPositive.text = response.body()?.get(0)?.positif
                    homeBinding.tvDied.text = response.body()?.get(0)?.meninggal
                }
            }
            override fun onFailure(call: Call<List<CovidResponse>>, t: Throwable){
            }
        })
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r: Resources = resources
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
        ).roundToInt()
    }
}