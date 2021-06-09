package id.ajiguna.appkesmas.ui.history

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.ajiguna.appkesmas.core.network.ApiConfig
import id.ajiguna.appkesmas.core.network.response.HistoryResponse
import id.ajiguna.appkesmas.databinding.ContentRegisterBinding
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding
import id.ajiguna.appkesmas.ui.auth.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyBinding: FragmentHistoryBinding
    private lateinit var detailBinding: ContentRegisterBinding
    private var list = ArrayList<HistoryResponse>()
    private var idUser : String? = null
    var pref: SharedPreferences? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        historyBinding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        detailBinding = historyBinding.detailContent
        return historyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            pref = activity?.getSharedPreferences("appkesmas", Context.MODE_PRIVATE)
            idUser = pref?.getString("id", null)

            if (idUser == null){
                historyBinding.shimmerFrameLayout.stopShimmer()
                historyBinding. shimmerFrameLayout.visibility = View.GONE
                historyBinding.content.visibility = View.VISIBLE

                detailBinding.btnRegister.setOnClickListener {
                    val intent = Intent(activity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }

            ApiConfig.getApiService().getHistory(idUser)
                .enqueue(object :
                    Callback<List<HistoryResponse>> {
                    override fun onResponse(
                        call: Call<List<HistoryResponse>>,
                        response: Response<List<HistoryResponse>>
                    ) {
                        //Tulis code jika response sukses
                        if (response.code() == 200) {
                            historyBinding.shimmerFrameLayout.stopShimmer()
                            historyBinding.shimmerFrameLayout.visibility = View.GONE
                            historyBinding.rvHistory.visibility = View.VISIBLE
                            if (response.body()?.size == 0) historyBinding.tvEmpty.visibility = View.VISIBLE
                            list = response.body() as ArrayList<HistoryResponse>
                            historyBinding.rvHistory.layoutManager =
                                LinearLayoutManager(activity)
                            val listHistoryAdapter = HistoryAdapter(list)
                            historyBinding.rvHistory.adapter = listHistoryAdapter
                            listHistoryAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<HistoryResponse>>, t: Throwable) {
                    }
                })
        }
    }

    override fun onResume() {
        super.onResume()
        historyBinding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        historyBinding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }
}