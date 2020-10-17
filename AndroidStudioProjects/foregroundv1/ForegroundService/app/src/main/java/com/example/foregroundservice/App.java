package com.example.foregroundservice;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application
{
    public static final String CHANNEL_ID = "locationForegroundService"; // notification chanel id

    // app constructor, called as soon as the app is started
    @Override
    public void onCreate()
    {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel()
    {
        // create the notification chanel only if using Oreo or high
        // because only then it's required
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            // set the notification chanel
            NotificationChannel serviceChanel = new NotificationChannel(CHANNEL_ID, "Location Tracking", NotificationManager.IMPORTANCE_DEFAULT);
            // get the notification manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            // create the notification channel
            manager.createNotificationChannel(serviceChanel);
        }
    }
}
