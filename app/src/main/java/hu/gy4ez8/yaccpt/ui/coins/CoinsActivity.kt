package hu.gy4ez8.yaccpt.ui.coins

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.injector
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.ui.about.AboutActivity
import javax.inject.Inject


class CoinsActivity : AppCompatActivity(), CoinsScreen {
    private var twoPane: Boolean = false

    private val coins: MutableList<Coin> = mutableListOf()
    private lateinit var coinsAdapter: CoinsAdapter
    private lateinit var swipeContainer: SwipeRefreshLayout

    @Inject
    lateinit var coinsPresenter: CoinsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)
        injector.inject(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.coins_activity_title)
        setSupportActionBar(toolbar)

        if (findViewById<NestedScrollView>(R.id.coin_details_container) != null) {
            twoPane = true
        }

        swipeContainer = findViewById(R.id.coin_list_swipe_container)
        swipeContainer.setOnRefreshListener {
            coinsAdapter.clear()
            coinsPresenter.refreshCoins()
        }

        coinsAdapter = CoinsAdapter(this, coins, twoPane)

        val recyclerView = findViewById<RecyclerView>(R.id.coin_list)
        recyclerView.adapter = coinsAdapter
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
        coinsAdapter.clear()
        coinsAdapter.addAll(coins!!)
        swipeContainer.isRefreshing = false

        findViewById<RelativeLayout>(R.id.coin_list_loading).visibility = View.GONE
        findViewById<FrameLayout>(R.id.coin_list_layout).visibility = View.VISIBLE
    }

    override fun showNetworkError() {
        Toast.makeText(this, "Network Error! Showing offline data...", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info -> {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}