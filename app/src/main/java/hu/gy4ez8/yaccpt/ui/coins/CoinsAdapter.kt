package hu.gy4ez8.yaccpt.ui.coins

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.network.NetworkConfig.CRYPTO_ICON_API_ENDPOINT_ADDRESS
import hu.gy4ez8.yaccpt.ui.details.DetailsActivity
import hu.gy4ez8.yaccpt.ui.details.DetailsFragment


class CoinsAdapter constructor(
        private val context: Context,
        private var coins: MutableList<Coin>,
        private val twoPane: Boolean
) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val coin = v.tag as Coin

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, coin.id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, coin.name)
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "coin")
        FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        if (twoPane) {
            val fragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DetailsFragment.ARG_COIN_ID, coin.id)
                }
            }
            (context as CoinsActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.coin_details_container, fragment)
                .commit()
        } else {
            val intent = Intent(v.context, DetailsActivity::class.java).apply {
                putExtra(DetailsFragment.ARG_COIN_ID, coin.id)
            }
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(
                R.layout.coin_list_item,
                viewGroup,
                false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coins[position]

        Glide.with(context).load(CRYPTO_ICON_API_ENDPOINT_ADDRESS + coin.symbol!!.toLowerCase()).into(holder.image)
        holder.name.text = coin.name
        holder.price.text = "$" + coin.priceUsd

        with(holder.itemView) {
            tag = coin
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = coins.size

    fun clear() {
        coins.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: Collection<Coin>) {
        coins.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.coin_list_item_image)
        val name: TextView = view.findViewById(R.id.coin_list_item_name)
        val price: TextView = view.findViewById(R.id.coins_list_item_price)
    }
}