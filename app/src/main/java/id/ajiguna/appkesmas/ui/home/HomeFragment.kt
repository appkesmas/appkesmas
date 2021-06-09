package id.ajiguna.appkesmas.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.BannerResponse
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.CovidResponse
import id.ajiguna.appkesmas.core.utils.GridSpacingItemDecoration
import id.ajiguna.appkesmas.databinding.FragmentHomeBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicActivity
import id.ajiguna.appkesmas.ui.hospital.HospitalActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding

    private var listMost = ArrayList<BannerResponse>()

    private lateinit var locationManager: LocationManager
    var lattitude: String? = null
    var longitude: String? = null

    companion object{
        private const val REQUEST_LOCATION = 1
    }

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

            locationManager =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps()
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation()
            }
            getCovid()
            getCategory()
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

        ApiConfig.getApiService().getBanner().enqueue(object :
            Callback<List<BannerResponse>> {
            override fun onResponse(
                call: Call<List<BannerResponse>>,
                response: Response<List<BannerResponse>>
            ) {
                //Tulis code jika response sukses
                if (response.code() == 200) {
                    homeBinding.shimmerFrameMost.stopShimmer()
                    homeBinding.shimmerFrameMost.visibility = View.GONE
                    homeBinding.rvMost.visibility = View.VISIBLE
                    listMost = response.body() as ArrayList<BannerResponse>
                    val mostAdapter = MostAdapter(listMost)
                    homeBinding.rvMost.adapter = mostAdapter

                }
            }

            override fun onFailure(call: Call<List<BannerResponse>>, t: Throwable) {
                Toast.makeText(activity, "Belum ada data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCovid(){
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd LLLL yyyy HH:mm")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        homeBinding.tvTime.text = dateTime

        ApiConfig.getApiCovid().getCovid().enqueue(object :
            Callback<List<CovidResponse>> {
            override fun onResponse(
                call: Call<List<CovidResponse>>,
                response: Response<List<CovidResponse>>
            ) {
                //Tulis code jika response sukses
                if (response.code() == 200) {
                    homeBinding.tvPositive.text = response.body()?.get(0)?.positif
                    homeBinding.tvHeal.text = response.body()?.get(0)?.sembuh
                    homeBinding.tvDied.text = response.body()?.get(0)?.meninggal
                }
            }

            override fun onFailure(call: Call<List<CovidResponse>>, t: Throwable) {
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

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Please Turn ON your GPS Connection")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { dialog, id -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            val location: Location? =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val location1: Location? =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val location2: Location? =
                locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            if (location != null) {
                val latti = location.latitude
                val longi = location.longitude
                lattitude = latti.toString()
                longitude = longi.toString()
            } else if (location1 != null) {
                val latti = location1.latitude
                val longi = location1.longitude
                lattitude = latti.toString()
                longitude = longi.toString()
            } else if (location2 != null) {
                val latti = location2.latitude
                val longi = location2.longitude
                lattitude = latti.toString()
                longitude = longi.toString()
            } else {
                Toast.makeText(activity, "Unable to Trace your location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeBinding.shimmerFrameMost.startShimmer()
    }

    override fun onPause() {
        homeBinding.shimmerFrameMost.stopShimmer()
        super.onPause()
    }

}