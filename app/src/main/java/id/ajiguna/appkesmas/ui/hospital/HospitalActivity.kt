package id.ajiguna.appkesmas.ui.hospital

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.core.utils.GridSpacingItemDecoration
import id.ajiguna.appkesmas.databinding.ActivityHospitalBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import id.ajiguna.appkesmas.ui.home.MostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class HospitalActivity : AppCompatActivity() {

    private lateinit var hospitalBinding: ActivityHospitalBinding
    private var list = ArrayList<HospitalResponse>()

    private var listArticle = ArrayList<ClinicResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalBinding = ActivityHospitalBinding.inflate(layoutInflater)
        setContentView(hospitalBinding.root)

        supportActionBar?.title = getString(R.string.hospital)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getHospital()
        getArticle()
    }

    private fun getHospital() {
        ApiConfig.getApiService().getHospital()
            .enqueue(object :
                Callback<List<HospitalResponse>> {
                override fun onResponse(
                    call: Call<List<HospitalResponse>>,
                    response: Response<List<HospitalResponse>>
                ) {
                    //Tulis code jika response sukses
                    if (response.code() == 200) {
                        hospitalBinding.shimmerFrameLayout.stopShimmer()
                        hospitalBinding.shimmerFrameLayout.visibility = View.GONE
                        hospitalBinding.rvHospital.visibility = View.VISIBLE
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

    private fun getArticle() {
        val layoutManagerfn: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        hospitalBinding.rvArticle.layoutManager = layoutManagerfn
        (layoutManagerfn as LinearLayoutManager).orientation =
            LinearLayoutManager.HORIZONTAL
        hospitalBinding.rvArticle.addItemDecoration(
            GridSpacingItemDecoration(
                10, dpToPx(10),
                true
            )
        )
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(hospitalBinding.rvArticle)

        ApiConfig.getApiService().getClinic().enqueue(object :
            Callback<List<ClinicResponse>> {
            override fun onResponse(
                call: Call<List<ClinicResponse>>,
                response: Response<List<ClinicResponse>>
            ) {
                //Tulis code jika response sukses
                if (response.code() == 200) {
                    hospitalBinding.shimmerFrameArticle.stopShimmer()
                    hospitalBinding.shimmerFrameArticle.visibility = View.GONE
                    hospitalBinding.rvArticle.visibility = View.VISIBLE
                    listArticle = response.body() as ArrayList<ClinicResponse>
                    val articleAdapter = ArticleAdapter(listArticle)
                    hospitalBinding.rvArticle.adapter = articleAdapter

                }
            }

            override fun onFailure(call: Call<List<ClinicResponse>>, t: Throwable) {
                Toast.makeText(this@HospitalActivity, "Belum ada data", Toast.LENGTH_SHORT).show()
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        hospitalBinding.shimmerFrameLayout.startShimmer()
        hospitalBinding.shimmerFrameArticle.startShimmer()
    }

    override fun onPause() {
        hospitalBinding.shimmerFrameLayout.stopShimmer()
        hospitalBinding.shimmerFrameArticle.stopShimmer()
        super.onPause()
    }

}