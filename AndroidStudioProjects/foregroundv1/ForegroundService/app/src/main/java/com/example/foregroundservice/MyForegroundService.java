package com.example.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.foregroundservice.App.CHANNEL_ID;
import static com.example.foregroundservice.MainActivity.INTENT_KEY;

public class MyForegroundService extends Service
{

    public static final int REQUEST_CODE = 0; // used to retrieve the same pending intent instance later on (for cancelling, etc)
    public static final int FLAGS = 0; //defines what happen when updating the pending intent with a new intent
    public static final int ID = 1; // service id

    // called only at the first time the service is created
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    // triggered when starting the service (every single time)
    @Override
    public int onStartCommand(Intent intent, int flag, int startId)
    {
        String input = intent.getStringExtra(INTENT_KEY); // get data from the intent
        Intent notificationIntent = new Intent(this, MainActivity.class); // will take to the mainActivity when noti is clicked
        // to set it on a notification, must create a pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, notificationIntent, FLAGS);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Tracking location")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_baseline)
                .setContentIntent(pendingIntent)
                .build();

        // start service!
        startForeground(ID, notification);

        // what should happen when the service is killed?
        return START_NOT_STICKY; // CHANGE INTO STICKY OR REDELIVER
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    // used only for bound services yet has to be overridden
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}