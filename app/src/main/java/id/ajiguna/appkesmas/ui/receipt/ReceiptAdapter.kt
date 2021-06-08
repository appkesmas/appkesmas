package id.ajiguna.appkesmas.ui.receipt

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.ReceiptResponse
import id.ajiguna.appkesmas.databinding.ItemClinicBinding
import id.ajiguna.appkesmas.databinding.ItemReceiptBinding
import id.ajiguna.appkesmas.ui.detail.DetailActivity
import id.ajiguna.appkesmas.ui.patient.RegisterPatientActivity

class ReceiptAdapter (private val receipts: ArrayList<ReceiptResponse>) :
        RecyclerView.Adapter<ReceiptAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemReceiptBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(receipts[position])
    }

    override fun getItemCount(): Int = receipts.size

    inner class ListViewHolder(private val binding: ItemReceiptBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(receipt: ReceiptResponse) {
            with(binding) {
                tvItemTitle.text = receipt.puskesmas
                tvTime.text = receipt.date
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailReceiptActivity::class.java)
                    intent.putExtra(DetailReceiptActivity.EXTRA_RECEIPT, receipt)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}