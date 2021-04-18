package hu.gy4ez8.yaccpt.ui.coins

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.injector
import hu.gy4ez8.yaccpt.model.Coin
import javax.inject.Inject

class CoinsActivity : AppCompatActivity(), CoinsScreen {
    private var twoPane: Boolean = false

    private val coins: MutableList<Coin> = mutableListOf()
    private var coinsAdapter: CoinsAdapter? = null

    @Inject
    lateinit var coinsPresenter: CoinsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)
        injector.inject(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.coin_details_container) != null) {
            twoPane = true
        }
    }

    override fun onStart() {
        super.onStart()
        coinsPresenter.attachScreen(this)

        coinsPresenter.refreshCoins()
    }

    override fun onStop() {
        super.onStop()
        coinsPresenter.detachScreen()
    }

    override fun showCoins(coins: List<Coin>?) {
        val recyclerView : RecyclerView = findViewById(R.id.coins)
        recyclerView.adapter = CoinsAdapter(this, coins!!, twoPane)
    }

    override fun showNetworkError(errorMsg: String) {
        TODO("Not yet implemented")
    }
}