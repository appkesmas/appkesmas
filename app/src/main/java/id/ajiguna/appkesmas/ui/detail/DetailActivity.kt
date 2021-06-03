package id.ajiguna.appkesmas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.core.utils.Constants
import id.ajiguna.appkesmas.databinding.ActivityDetailBinding
import id.ajiguna.appkesmas.databinding.ContentDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailBinding: ContentDetailBinding
    private lateinit var hospital: HospitalResponse

    companion object {
        const val EXTRA_CONTENT = "extra_content"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        hospital = intent.getParcelableExtra<HospitalResponse>(EXTRA_CONTENT) as HospitalResponse

        getData(hospital)

    }

    private fun getData(hospitalResponse: HospitalResponse){
        with(hospitalResponse){
            with(detailBinding){
                tvName.text = name.toString()
                tvAddress.text = address.toString()
                tvLocation.text = area
                tvType.text = category
                tvClass.text = classType
                Glide.with(this@DetailActivity)
                    .load(Constants.IMG_URL + imageName)
                    .into(imgPhoto)

                tvBed.text = bedAvailability.toString() + " Bed"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}