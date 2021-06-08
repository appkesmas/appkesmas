package id.ajiguna.appkesmas.ui.receipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.core.network.response.ReceiptResponse
import id.ajiguna.appkesmas.core.utils.Constants
import id.ajiguna.appkesmas.databinding.ActivityDetailReceiptBinding
import id.ajiguna.appkesmas.ui.detail.DetailActivity

class DetailReceiptActivity : AppCompatActivity() {

    private lateinit var receiptBinding: ActivityDetailReceiptBinding
    private lateinit var receipt: ReceiptResponse

    companion object {
        const val EXTRA_RECEIPT = "extra_receipt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiptBinding = ActivityDetailReceiptBinding.inflate(layoutInflater)
        setContentView(receiptBinding.root)

        supportActionBar?.title = getString(R.string.list_receipt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        receipt = intent.getParcelableExtra<ReceiptResponse>(EXTRA_RECEIPT) as ReceiptResponse

        getData(receipt)
    }

    private fun getData(receiptResponse: ReceiptResponse){
        with(receiptResponse){
            with(receiptBinding){
                tvDoctor.text = doctorName.toString()
                tvTime.text = date.toString()
                tvItemTitle.text = drugList.toString()
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