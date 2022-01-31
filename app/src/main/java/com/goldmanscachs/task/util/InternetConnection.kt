package com.goldmanscachs.task.util

import android.content.Context
import android.net.ConnectivityManager

object InternetConnection {
    /**
     * It checks Whether Internet connection is available or not.
     *
     * @param context It indicates context of either Application or Activity/Fragment
     */
    @Suppress("DEPRECATION")
    fun checkNetworkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                true
            } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }
}