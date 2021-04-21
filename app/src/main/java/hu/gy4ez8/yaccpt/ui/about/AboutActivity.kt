package hu.gy4ez8.yaccpt.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.gy4ez8.yaccpt.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}