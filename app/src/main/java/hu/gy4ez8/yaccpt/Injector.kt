package hu.gy4ez8.yaccpt

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: AppComponent
    get() {
        return (this.applicationContext as App).injector
    }

val Fragment.injector: AppComponent
    get() {
        return (this.context!!.applicationContext as App).injector
    }
