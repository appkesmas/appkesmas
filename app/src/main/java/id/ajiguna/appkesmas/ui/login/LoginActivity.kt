package id.ajiguna.appkesmas.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ajiguna.appkesmas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginBinding.root
        
    }
}