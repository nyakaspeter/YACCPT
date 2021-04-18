package hu.gy4ez8.yaccpt.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hu.gy4ez8.yaccpt.R
import hu.gy4ez8.yaccpt.ui.coins.CoinsActivity

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        DetailsFragment.COIN_ID,
                            intent.getStringExtra(DetailsFragment.COIN_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.coin_details_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, CoinsActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}