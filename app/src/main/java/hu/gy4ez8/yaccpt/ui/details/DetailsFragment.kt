package hu.gy4ez8.yaccpt.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.injector
import hu.gy4ez8.yaccpt.model.Coin
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsScreen {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        detailsPresenter.attachScreen(this)
    }

    override fun onDetach() {
        detailsPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_COIN_ID)) {
                val coinId = it.getString(ARG_COIN_ID)

                detailsPresenter.refreshCoin(coinId!!)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.coin_details, container, false)

        return rootView
    }

    override fun showCoin(coin: Coin?) {
        activity?.findViewById<TextView>(R.id.coin_details_name)?.text = coin?.name
    }

    override fun showNetworkError(errorMsg: String) {
        TODO("Not yet implemented")
    }

    companion object {
        const val ARG_COIN_ID = "COIN_ID"
    }
}