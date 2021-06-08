package id.ajiguna.appkesmas.ui.accounts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.UserResponse
import id.ajiguna.appkesmas.core.utils.Session
import id.ajiguna.appkesmas.databinding.ContentDetailBinding
import id.ajiguna.appkesmas.databinding.ContentRegisterBinding
import id.ajiguna.appkesmas.databinding.FragmentAccountsBinding
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding
import id.ajiguna.appkesmas.ui.auth.RegisterActivity
import id.ajiguna.appkesmas.ui.clinic.ClinicActivity
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import id.ajiguna.appkesmas.ui.hospital.HospitalActivity
import id.ajiguna.appkesmas.ui.receipt.DetailReceiptActivity
import id.ajiguna.appkesmas.ui.receipt.ReceiptActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountsFragment : Fragment() {

    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var accountsBinding: FragmentAccountsBinding
    private lateinit var detailBinding: ContentRegisterBinding
    private var idUser : String? = null
    private val session: Session? = null
    var pref: SharedPreferences? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        accountsBinding = FragmentAccountsBinding.inflate(layoutInflater, container, false)
        detailBinding = accountsBinding.detailRegister
        return accountsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            pref = activity?.getSharedPreferences("appkesmas", Context.MODE_PRIVATE)
            idUser = pref?.getString("id", null)

            if (idUser == null){
                accountsBinding.shimmerFrameLayout.stopShimmer()
                accountsBinding. shimmerFrameLayout.visibility = View.GONE
                accountsBinding.content.visibility = View.VISIBLE

                detailBinding.btnRegister.setOnClickListener {
                    val intent = Intent(activity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }

            getUser()
            accountsBinding.btnResep.setOnClickListener {
                val intent = Intent(activity, ReceiptActivity::class.java)
                intent.putExtra("id_user", idUser)
                startActivity(intent)
            }
        }
    }

    private fun getUser() {
        ApiConfig.getApiService().getUser(idUser)
            .enqueue(object :
                Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    //Tulis code jika response sukses
                    if (response.code() == 200) {
                        accountsBinding.shimmerFrameLayout.stopShimmer()
                        accountsBinding. shimmerFrameLayout.visibility = View.GONE
                        accountsBinding.lineInfo.visibility = View.VISIBLE

                        with(accountsBinding){
                            tvNoMedis.text = response.body()?.data?.medicalId
                            tvNames.text = response.body()?.data?.name
                            tvNoKtp.text = response.body()?.data?.identityNumber
                            tvNoBpjs.text = response.body()?.data?.bpjsNumber
                            tvNoTelp.text = response.body()?.data?.phoneNumber
                            tvAddressUser.text = response.body()?.data?.address
                            tvAges.text = response.body()?.data?.age.toString() + " tahun"
                            tvGenders.text = response.body()?.data?.sex
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                }
            })
    }

    override fun onResume() {
        super.onResume()
        accountsBinding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        accountsBinding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }
}