package id.ajiguna.appkesmas.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ItemMostViewBinding
import id.ajiguna.appkesmas.databinding.ItemHospitalBinding
import id.ajiguna.appkesmas.ui.clinic.ClinicAdapter
import id.ajiguna.appkesmas.ui.detail.DetailActivity

class MostAdapter (private val users: ArrayList<ClinicResponse>) :
        RecyclerView.Adapter<MostAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMostViewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users?.size!!

    inner class ListViewHolder(private val binding: ItemMostViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(clinic: ClinicResponse) {
            with(binding) {
//                Glide.with(itemView.context)
//                        .load(clinic.avatarUrl)
//                        .into(imgPoster)
                tvTitle.text = clinic.name
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_CONTENT, clinic)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}