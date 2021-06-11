package id.ajiguna.appkesmas.ui.hospital

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.R
import id.ajiguna.appkesmas.core.network.response.HospitalResponse
import id.ajiguna.appkesmas.core.network.response.HospitaliResponse
import id.ajiguna.appkesmas.core.utils.Constants
import id.ajiguna.appkesmas.databinding.ItemHospitalBinding
import id.ajiguna.appkesmas.ui.detail.DetailActivity
import java.math.RoundingMode

class HospitalAdapter (private val hospitals: ArrayList<HospitaliResponse>) :
        RecyclerView.Adapter<HospitalAdapter.ListViewHolder>() {

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
        fun bind(hospital: HospitaliResponse) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(hospital.imageURL)
                        .into(imgPoster)
                tvItemTitle.text = hospital.namaRS
                tvDescription.text = hospital.wilayah
                val range = hospital.jarak?.toBigDecimal()?.setScale(1, RoundingMode.UP)?.toDouble()
                tvRange.text = range.toString() + " Km"
                tvClass.text = "Kelas "+ hospital.kelas
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_CONTENT, hospital)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}