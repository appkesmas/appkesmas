package id.ajiguna.appkesmas.ui.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ajiguna.appkesmas.databinding.ActivityHospitalBinding

class HospitalActivity : AppCompatActivity() {

    private lateinit var hospitalBinding: ActivityHospitalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalBinding = ActivityHospitalBinding.inflate(layoutInflater)
        hospitalBinding.root
    }
}