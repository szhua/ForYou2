package com.szhua.foryou.utils

import com.szhua.foryou.ForYouApp


fun Int.toDp(): Int {
    val density = ForYouApp.getInstance().resources.displayMetrics.density
    return (this/density+0.5f).toInt()
}

fun Int.toPx():Int{
    val density = ForYouApp.getInstance().resources.displayMetrics.density
    return (this*density+0.5f).toInt()
}
