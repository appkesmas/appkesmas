package id.ajiguna.appkesmas.ui.accounts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.UserResponse
import id.ajiguna.appkesmas.databinding.FragmentAccountsBinding
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicActivity
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import id.ajiguna.appkesmas.ui.hospital.HospitalActivity
import id.ajiguna.appkesmas.ui.receipt.ReceiptActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountsFragment : Fragment() {

    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var accountsBinding: FragmentAccountsBinding
    private var idUser : String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        accountsBinding = FragmentAccountsBinding.inflate(layoutInflater, container, false)
        return accountsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            getUser()
            accountsBinding.btnResep.setOnClickListener {
                val intent = Intent(activity, ReceiptActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getUser() {
        ApiConfig.getApiService().getUser("fc70bef6c8b146c5843eccc81fe3b4a5")
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
                            tvName.text = response.body()?.data?.name
                            tvKtp.text = response.body()?.data?.identityNumber
                            tvTelp.text = response.body()?.data?.phoneNumber
                            tvAddress.text = response.body()?.data?.address
                            tvAge.text = response.body()?.data?.age.toString()
                            tvGender.text = response.body()?.data?.sex
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