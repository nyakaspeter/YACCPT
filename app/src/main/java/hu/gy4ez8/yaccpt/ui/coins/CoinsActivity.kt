package hu.gy4ez8.yaccpt.ui.coins

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        //findViewById(R.id.item_list).adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
    }

    override fun onStart() {
        super.onStart()
        coinsPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        coinsPresenter.detachScreen()
    }

    override fun showCoins(coins: List<Coin>?) {
        TODO("Not yet implemented")
    }

    override fun showNetworkError(errorMsg: String) {
        TODO("Not yet implemented")
    }
}