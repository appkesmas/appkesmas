package id.ajiguna.appkesmas.ui.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.RegisterResponse
import id.ajiguna.appkesmas.databinding.ActivityDetailBinding
import id.ajiguna.appkesmas.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerBinding.root

        registerBinding.btnRegister.setOnClickListener { postLogin() }
    }

    private fun postLogin() {
//        loading.visibility = View.VISIBLE
//        val email = et_email.text.toString().trim()
//        val password = et_password.text.toString().trim()

//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(
//                    this@LoginActivity,
//                    "Mohon lengkapi username atau password !",
//                    Toast.LENGTH_SHORT
//            ).show()
//        } else if (et_email.getText().toString().contains(" ")) {
//            et_email.setError("Tidak Boleh Spasi")
//            Toast.makeText(this@LoginActivity, "Tidak Boleh Spasi", Toast.LENGTH_SHORT).show()
//        } else {
            val params = HashMap<String, String>()
//            params.put("email", email);
//            params.put("password", password)
            ApiConfig.getApiService().postRegister(params).enqueue(object :
                    Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.code() == 201) {
//                        loading.visibility = View.GONE
                        val token = response.body()?.message

//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        startActivity(intent)
//                        finish()

                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                    loading.visibility = View.GONE
                }
            })
//        }
    }
}