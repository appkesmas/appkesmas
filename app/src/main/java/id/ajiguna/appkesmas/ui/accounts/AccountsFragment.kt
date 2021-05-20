package id.ajiguna.appkesmas.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.databinding.FragmentAccountsBinding
import id.ajiguna.appkesmas.databinding.FragmentHistoryBinding

class AccountsFragment : Fragment() {

    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var accountsBinding: FragmentAccountsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        accountsBinding = FragmentAccountsBinding.inflate(layoutInflater, container, false)
        return accountsBinding.root
    }
}