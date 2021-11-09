package com.medhdj.core.platform

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setupToolbar(
    toolbar: Toolbar,
    showTitle: Boolean = true,
    showBack: Boolean = true
) {
    with(requireActivity() as? AppCompatActivity) {
        this?.also {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(showTitle)
            supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
            supportActionBar?.setDisplayShowHomeEnabled(showBack)

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
}

