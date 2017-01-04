package com.example.sridh.target75.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.sridh.target75.MainActivity;
import com.example.sridh.target75.R;

/**
 * Created by SatyamBansal on 04-01-2017.
 */

public class ReminderService extends IntentService {

    public ReminderService() {
        super("ReminderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createNotification(getApplicationContext());
    }

    private void createNotification(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,2,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(getResources().getString(R.string.notification_heading))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText(getResources().getString(R.string.notification_detail))
                .setSmallIcon(android.R.drawable.sym_def_app_icon);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }
}
