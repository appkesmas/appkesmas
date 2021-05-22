package id.ajiguna.appkesmas.ui.clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ajiguna.appkesmas.databinding.ActivityClinicBinding
import id.ajiguna.appkesmas.databinding.ActivityHospitalBinding

class ClinicActivity : AppCompatActivity() {

    private lateinit var clinicBinding: ActivityClinicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clinicBinding = ActivityClinicBinding.inflate(layoutInflater)
        clinicBinding.root
    }
}