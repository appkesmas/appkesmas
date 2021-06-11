package id.ajiguna.appkesmas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.HospitaliResponse
import id.ajiguna.appkesmas.databinding.ActivityDetailBinding
import id.ajiguna.appkesmas.databinding.ContentDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailBinding: ContentDetailBinding
    private lateinit var hospital: HospitaliResponse

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

        hospital = intent.getParcelableExtra<HospitaliResponse>(EXTRA_CONTENT) as HospitaliResponse

        getData(hospital)

    }

    private fun getData(hospitalResponse: HospitaliResponse){
        with(hospitalResponse){
            with(detailBinding){
                tvName.text = namaRS.toString()
                tvAddress.text = wilayah.toString()
                tvLocation.text = wilayah
                tvType.text = jenisRS
                tvClass.text = kelas
                Glide.with(this@DetailActivity)
                    .load(imageURL)
                    .into(imgPhoto)

                tvBed.text = totalKetersediaan.toString() + " Bed"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}