package id.ajiguna.appkesmas.ui.clinic

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.databinding.ItemClinicBinding
import id.ajiguna.appkesmas.ui.detail.DetailActivity
import id.ajiguna.appkesmas.ui.patient.RegisterPatientActivity

class ClinicAdapter (private val clinics: ArrayList<ClinicResponse>) :
        RecyclerView.Adapter<ClinicAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemClinicBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(clinics[position])
    }

    override fun getItemCount(): Int = clinics.size

    inner class ListViewHolder(private val binding: ItemClinicBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(clinic: ClinicResponse) {
            with(binding) {
                tvItemTitle.text = clinic.name
                tvDescription.text = clinic.address
                btnQueue.setOnClickListener {
                    val intent = Intent(itemView.context, RegisterPatientActivity::class.java)
                    intent.putExtra(RegisterPatientActivity.EXTRA_ID, clinic.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}