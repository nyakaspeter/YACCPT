package hu.gy4ez8.yaccpt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.injector
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.network.NetworkConfig
import java.text.DecimalFormat
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsScreen {
    private var coinId: String? = null

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    private lateinit var swipeContainer: SwipeRefreshLayout

    override fun onStart() {
        super.onStart()
        injector.inject(this)
        detailsPresenter.attachScreen(this)
        detailsPresenter.refreshCoin(coinId!!)
    }

    override fun onDetach() {
        detailsPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_COIN_ID)) {
                coinId = it.getString(ARG_COIN_ID)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.coin_details, container, false)

        swipeContainer = rootView.findViewById(R.id.coin_details)
        swipeContainer.setOnRefreshListener {
            detailsPresenter.refreshCoin(coinId!!)
        }

        return rootView
    }

    override fun showCoin(coin: Coin?) {
        val image = activity?.findViewById<ImageView>(R.id.coin_details_image)
        val name = activity?.findViewById<TextView>(R.id.coin_details_name)
        val price = activity?.findViewById<TextView>(R.id.coin_details_price)
        val priceChange1h = activity?.findViewById<TextView>(R.id.coin_details_price_change_1h)
        val priceChange24h = activity?.findViewById<TextView>(R.id.coin_details_price_change_24h)
        val priceChange7d = activity?.findViewById<TextView>(R.id.coin_details_price_change_7d)
        val volume24h = activity?.findViewById<TextView>(R.id.coin_details_volume_24h)
        val marketCap = activity?.findViewById<TextView>(R.id.coin_details_market_cap)

        val imageUrl = NetworkConfig.CRYPTO_ICON_API_ENDPOINT_ADDRESS + coin!!.symbol!!.toLowerCase()
        val nameString = coin.name + " (" + coin.symbol + ")"
        val priceString = "$" + coin.priceUsd
        val percentChange1hString = "%.2f".format(coin.priceUsd!!.toDouble() - coin.priceUsd!!.toDouble() / (1 + coin.percentChange1h!!.toDouble() / 100)) + " USD (" + coin.percentChange1h + "%)"
        val percentChange24hString = "%.2f".format(coin.priceUsd!!.toDouble() - coin.priceUsd!!.toDouble() / (1 + coin.percentChange24h!!.toDouble() / 100)) + " USD (" + coin.percentChange24h + "%)"
        val percentChange7dString = "%.2f".format(coin.priceUsd!!.toDouble() - coin.priceUsd!!.toDouble() / (1 + coin.percentChange7d!!.toDouble() / 100)) + " USD (" + coin.percentChange7d + "%)"
        val volume24hString = DecimalFormat("#,### USD").format(coin.volume24!!.toDouble())
        val marketCapString = DecimalFormat("#,### USD").format(coin.marketCapUsd!!.toDouble())

        Glide.with(activity!!).load(imageUrl).into(image!!)
        name!!.text = nameString
        price!!.text = priceString
        priceChange1h!!.text = percentChange1hString
        priceChange24h!!.text = percentChange24hString
        priceChange7d!!.text = percentChange7dString
        volume24h!!.text = volume24hString
        marketCap!!.text = marketCapString

        swipeContainer.isRefreshing = false

        activity!!.findViewById<RelativeLayout>(R.id.coin_details_loading)?.visibility = View.GONE
        activity!!.findViewById<LinearLayout>(R.id.coin_details_layout).visibility = View.VISIBLE
    }

    override fun showNetworkError() {
        Toast.makeText(activity, "Network Error! Showing offline data...", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COIN_ID = "COIN_ID"
    }
}