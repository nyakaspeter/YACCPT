package hu.gy4ez8.yaccpt.ui.coins

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.model.Coin

class CoinsAdapter constructor(
    private val context: Context,
    private var coins: List<Coin>) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.coins_content, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        val coins = coins[position]

        artist.images?.let {
            val images = artist.images!!
            if (images.isNotEmpty()) {
                Glide.with(context).load(images[0].url).into(holder.ivImage)
            }
        }

        holder.tvName.text = artist.name
        holder.tvPopularity.text = artist.popularity!!.toString()
         */
    }

    override fun getItemCount() = coins.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}