package id.ajiguna.appkesmas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.BannerResponse
import id.ajiguna.appkesmas.core.utils.Constants
import id.ajiguna.appkesmas.databinding.ItemMostViewBinding

class MostAdapter (private val users: ArrayList<BannerResponse>) :
        RecyclerView.Adapter<MostAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMostViewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class ListViewHolder(private val binding: ItemMostViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: BannerResponse) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(Constants.IMG_BANNER + banner.imageUrl)
                        .into(imgPoster)
            }
        }
    }
}