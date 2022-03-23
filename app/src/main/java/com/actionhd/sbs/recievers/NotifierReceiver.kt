package com.actionhd.sbs.recievers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import com.actionhd.sbs.R
import com.actionhd.sbs.helpers.NotificationHelper
import com.actionhd.sbs.utils.AppUtils

class NotifierReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val prefs = context.getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE)
        val notificationsTone = prefs.getString(
                AppUtils.NOTIFICATION_TONE_URI_KEY, RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION
        ).toString()
        )


        val title = context.resources.getString(R.string.app_name)
        val messageToShow = prefs.getString(
                AppUtils.NOTIFICATION_MSG_KEY,
                context.resources.getString(R.string.pref_notification_message_value)
        )


        val nHelper = NotificationHelper(context)
        @SuppressLint("ResourceType") val nBuilder = messageToShow?.let {
            nHelper
                    .getNotification(title, it, notificationsTone)


        }
        nHelper.notify(1, nBuilder)

    }
}
