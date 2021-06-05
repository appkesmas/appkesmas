package id.ajiguna.appkesmas.ui.receipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ActivityReceiptBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceiptActivity : AppCompatActivity() {

    private lateinit var receiptBinding: ActivityReceiptBinding
    private var list = ArrayList<ClinicResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiptBinding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(receiptBinding.root)

        supportActionBar?.title = getString(R.string.list_receipt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getReceipt()
    }

    private fun getReceipt() {
        ApiConfig.getApiService().getClinic()
            .enqueue(object :
                Callback<List<ClinicResponse>> {
                override fun onResponse(
                    call: Call<List<ClinicResponse>>,
                    response: Response<List<ClinicResponse>>
                ) {
                    //Tulis code jika response sukses
                    if (response.code() == 200) {
                        receiptBinding.shimmerFrameLayout.stopShimmer()
                        receiptBinding. shimmerFrameLayout.visibility = View.GONE
                        receiptBinding.rvReceipt.visibility = View.VISIBLE
                        list = response.body() as ArrayList<ClinicResponse>
                        receiptBinding.rvReceipt.layoutManager =
                            LinearLayoutManager(this@ReceiptActivity)
                        val receiptAdapter = ReceiptAdapter(list)
                        receiptBinding.rvReceipt.adapter = receiptAdapter
                        receiptAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<ClinicResponse>>, t: Throwable) {
                    receiptBinding.shimmerFrameLayout.visibility = View.GONE
                    Toast.makeText(this@ReceiptActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        receiptBinding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        receiptBinding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }
}