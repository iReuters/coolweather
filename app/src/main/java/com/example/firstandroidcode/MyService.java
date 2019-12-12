package com.example.firstandroidcode;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.firstandroidcode.chapter10.Chapter10;

public class MyService extends Service {
    private static final String mTAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(mTAG, "onCreate: ");
        Intent intent = new Intent(this, Chapter10.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent ,0);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        Notification notification = new NotificationCompat.Builder(this, "1")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.apple_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple_icon))
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(mTAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d(mTAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;

    }

    public class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d(mTAG, "startDownload: ");
        }
        public int getProgress(){
            Log.d(mTAG, "getProgress: ");
            return 0;
        }
        public void stopDownload(){
            Log.d(mTAG, "stopDownload: ");
        }
    }
}
