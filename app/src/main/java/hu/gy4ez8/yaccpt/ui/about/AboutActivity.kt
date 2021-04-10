package hu.gy4ez8.yaccpt.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.gy4ez8.yaccpt.R

class AboutActivity : AppCompatActivity(), AboutScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        showAppInfo()
    }

    override fun showAppInfo() {
        TODO("Not yet implemented")
    }
}