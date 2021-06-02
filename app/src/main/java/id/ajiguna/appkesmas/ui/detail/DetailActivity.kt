package id.ajiguna.appkesmas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ajiguna.appkesmas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        const val EXTRA_CONTENT = "extra_content"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding.root
    }
}