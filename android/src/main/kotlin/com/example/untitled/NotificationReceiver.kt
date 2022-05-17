package com.example.untitled

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.RemoteInput

const val BROADCAST = "PACKAGE_NAME.android.action.broadcast"
class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if (remoteInput != null) {
            var noti = NotificationHelper()
            val title = remoteInput.getCharSequence(
                    noti.KEY_TEXT_REPLY).toString()
            Log.d("NotificationReceiver", title)
            UntitledPlugin.getInstance().showNewIdea(title) // add this line to invoke method channel
//            noti.createChannel(context)
            noti.showNotification(context)
        }
    }
}