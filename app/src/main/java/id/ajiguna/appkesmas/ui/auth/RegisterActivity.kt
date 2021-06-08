package id.ajiguna.appkesmas.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ajiguna.appkesmas.MainActivity
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.RegisterResponse
import id.ajiguna.appkesmas.core.utils.Session
import id.ajiguna.appkesmas.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    var session: Session? = null
    var progressDialog: ProgressDialog? = null
    var gender: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        supportActionBar?.title = "Daftar Akun"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        session = Session(this)
        progressDialog = ProgressDialog(this)

        registerBinding.spAssurance.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItemText = parent.getItemAtPosition(position) as String
                // Notify the selected item text
                if (selectedItemText != "Tidak Ada"){
                    registerBinding.lineNumber.visibility = View.VISIBLE
                } else {
                    registerBinding.lineNumber.visibility = View.GONE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        registerBinding.btnRegister.setOnClickListener {
            postRegister()
        }
    }

    private fun postRegister() {
        if (registerBinding.rbMale.isChecked) {
            gender = registerBinding.rbMale.text.toString()
        } else if (registerBinding.rbFemale.isChecked) {
            gender = registerBinding.rbFemale.text.toString()
        }
        val name = registerBinding.etName.text.toString().trim()
        val noKtp = registerBinding.etKtp.text.toString().trim()
        val address = registerBinding.etAddress.text.toString().trim()
        val phone = registerBinding.etTelp.text.toString().trim()
        val bpjs = registerBinding.etNoAssurance.text.toString().trim()
        val age = registerBinding.etAge.text.toString().trim()

        if (name.isEmpty() || noKtp.isEmpty()) {
            Toast.makeText(
                this,
                "Mohon lengkapi seluruh data",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val params = HashMap<String, String>()
            params["name"] = name
            params["address"] = address
            params["identity_number"] = noKtp
            params["phone_number"] = phone
            params["bpjs_number"] = bpjs
            params["sex"] = gender
            params["age"] = age

            progressDialog?.setMessage("Register...")
            progressDialog?.show()
            progressDialog?.setCanceledOnTouchOutside(false)

            ApiConfig.getApiService().postRegister(params).enqueue(object :
                Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.code() == 200) {
                        progressDialog?.dismiss()
                        session?.setLogin(
                            true,
                            response.body()?.user?.id.toString()
                        )
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()

                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    progressDialog?.dismiss()
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