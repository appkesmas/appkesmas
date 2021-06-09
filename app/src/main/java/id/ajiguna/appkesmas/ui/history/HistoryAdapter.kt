package id.ajiguna.appkesmas.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ajiguna.appkesmas.core.network.response.HistoryResponse
import id.ajiguna.appkesmas.databinding.ItemHospitalBinding

class HistoryAdapter (private val hospitals: ArrayList<HistoryResponse>) :
        RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHospitalBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(hospitals[position])
    }

    override fun getItemCount(): Int = hospitals.size

    inner class ListViewHolder(private val binding: ItemHospitalBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryResponse) {
            with(binding) {
                tvItemTitle.text = history.startTime
                tvDescription.text = history.puskesmasId
                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(DetailActivity.EXTRA_CONTENT, history)
//                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}