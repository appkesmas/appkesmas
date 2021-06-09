package id.ajiguna.appkesmas.ui.patient

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.TreadmentResponse
import id.ajiguna.appkesmas.core.network.response.UserResponse
import id.ajiguna.appkesmas.databinding.ActivityRegisterPatientBinding
import id.ajiguna.appkesmas.ui.antrian.AntrianActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPatientActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterPatientBinding
    private var idUser : String? = null
    var pref: SharedPreferences? = null
    private var idClinic: String = ""
    var progressDialog: ProgressDialog? = null

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterPatientBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        supportActionBar?.title = "Daftar Antrian"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pref = getSharedPreferences("appkesmas", Context.MODE_PRIVATE)
        idUser = pref?.getString("id", null)
        idClinic = intent.extras?.getString(EXTRA_ID).toString()

        progressDialog = ProgressDialog(this)

        if (idUser == null){
            registerBinding.shimmerFrameLayout.stopShimmer()
            registerBinding. shimmerFrameLayout.visibility = View.GONE
            registerBinding.content.visibility = View.VISIBLE

        } else {
            getData()
        }
    }

    private fun getData() {
        ApiConfig.getApiService().getUser(idUser)
            .enqueue(object :
                Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    //Tulis code jika response sukses
                    if (response.code() == 200) {
                        registerBinding.shimmerFrameLayout.stopShimmer()
                        registerBinding. shimmerFrameLayout.visibility = View.GONE
                        registerBinding.content.visibility = View.VISIBLE

                        with(registerBinding){
                            etNames.setText(response.body()?.data?.name)
                            etNumber.setText(response.body()?.data?.medicalId)
                        }

                        registerBinding.btnRegister.setOnClickListener {
                            postAntrian()
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                }
            })
    }

    private fun postAntrian() {
        val pain = registerBinding.spPainScale.selectedItemPosition + 1
        val immediacy = registerBinding.spImmediacy.selectedItemPosition + 1
        val temperature = registerBinding.spPainTemperature.selectedItemPosition + 1
        val name = registerBinding.etNames.text.toString().trim()
        val no_medis = registerBinding.etNumber.text.toString().trim()

        if ( name.isEmpty()|| no_medis.isEmpty()) {
            Toast.makeText(
                    this@RegisterPatientActivity,
                    "Mohon lengkapi seluruh data",
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            val params = HashMap<String, String>()
            params["user_id"] = idUser.toString()
            params["puskesmas_id"] = idClinic
            params["painscale"] = pain.toString()
            params["immediacy"] = immediacy.toString()
            params["temperature"] = temperature.toString()

            progressDialog?.setMessage("Daftar Antrian...")
            progressDialog?.show()
            progressDialog?.setCanceledOnTouchOutside(false)

            ApiConfig.getApiService().postTreatment(params).enqueue(object :
                    Callback<TreadmentResponse> {
                override fun onResponse(call: Call<TreadmentResponse>, response: Response<TreadmentResponse>) {
                    if (response.code() == 201) {
//                        loading.visibility = View.GONE
                        val token = response.body()?.message

                        val intent = Intent(this@RegisterPatientActivity, AntrianActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()

                    }
                }

                override fun onFailure(call: Call<TreadmentResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterPatientActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}