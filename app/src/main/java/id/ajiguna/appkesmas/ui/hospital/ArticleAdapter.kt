package id.ajiguna.appkesmas.ui.hospital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ajiguna.appkesmas.core.network.response.ArticleResponse
import id.ajiguna.appkesmas.core.utils.Constants
import id.ajiguna.appkesmas.databinding.ItemArticleBinding

class ArticleAdapter (private val users: ArrayList<ArticleResponse>) :
        RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class ListViewHolder(private val binding: ItemArticleBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleResponse) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(Constants.IMG_ARTICLE+article.imageUrl)
                        .into(imgPoster)
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(DetailActivity.EXTRA_CONTENT, clinic)
//                    itemView.context.startActivity(intent)
//                }
            }
        }
    }
}