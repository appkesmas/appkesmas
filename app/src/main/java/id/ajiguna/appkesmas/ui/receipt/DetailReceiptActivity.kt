package id.ajiguna.appkesmas.ui.receipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.databinding.ActivityDetailReceiptBinding

class DetailReceiptActivity : AppCompatActivity() {

    private lateinit var receiptBinding: ActivityDetailReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiptBinding = ActivityDetailReceiptBinding.inflate(layoutInflater)
        setContentView(receiptBinding.root)

        supportActionBar?.title = getString(R.string.list_receipt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}