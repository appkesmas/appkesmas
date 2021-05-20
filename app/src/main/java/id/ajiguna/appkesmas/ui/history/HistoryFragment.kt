package id.ajiguna.appkesmas.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding
import id.ajiguna.appkesmas.databinding.FragmentHomeBinding

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyBinding: FragmentHistoryBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        historyBinding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return historyBinding.root
    }
}