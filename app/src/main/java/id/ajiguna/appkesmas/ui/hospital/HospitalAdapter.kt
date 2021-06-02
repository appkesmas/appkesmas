package id.ajiguna.appkesmas.ui.hospital

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ItemsHospitalBinding
import id.ajiguna.appkesmas.ui.detail.DetailActivity

class HospitalAdapter (private val users: ArrayList<ClinicResponse>) :
        RecyclerView.Adapter<HospitalAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemsHospitalBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users?.size!!

    inner class ListViewHolder(private val binding: ItemsHospitalBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(clinic: ClinicResponse) {
            with(binding) {
//                Glide.with(itemView.context)
//                        .load(clinic.avatarUrl)
//                        .into(imgAvatar)
                tvItemTitle.text = clinic.name
                tvDescription.text = clinic.address
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_CONTENT, clinic)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}