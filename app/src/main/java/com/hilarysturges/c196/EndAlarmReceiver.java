package com.hilarysturges.c196;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class EndAlarmReceiver extends BroadcastReceiver {
    public static final String CHANNEL_2_ID = "Channel2";
    public static final String CHANNEL_2_NAME = "Channel Two";
    private NotificationManager nm;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("alarm request received");

        String title = "WGU Student Tracker Tool";
        String message = "Course ended!";

        NotificationChannel nc = new NotificationChannel(CHANNEL_2_ID, CHANNEL_2_NAME, NotificationManager.IMPORTANCE_HIGH);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_2_ID);
        if (nm == null)
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm!=null)
            nm.createNotificationChannel(nc);
        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.build();
        nm.notify(1,builder.build());


    }
}
