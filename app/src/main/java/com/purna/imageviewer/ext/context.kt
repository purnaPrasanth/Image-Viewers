package com.purna.imageviewer.ext

import android.content.Context
import android.widget.Toast

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

fun Context.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}