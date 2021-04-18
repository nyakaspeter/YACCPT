package hu.gy4ez8.yaccpt.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.injector
import hu.gy4ez8.yaccpt.model.Coin
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsScreen {
    private val coin: Coin? = null

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.details, container, false)

        return rootView
    }

    override fun showCoin(coin: Coin?) {
        TODO("Not yet implemented")
    }

    override fun showNetworkError(errorMsg: String) {
        TODO("Not yet implemented")
    }

    companion object {
        const val COIN_ID = "90"
    }
}