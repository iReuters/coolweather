package com.example.firstandroidcode.chapter13;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.firstandroidcode.MyToast;
import com.example.firstandroidcode.R;

public class LongRunningService extends Service {

    private boolean isFirst = true;

    private static final int CANCEL_ALARM_TASK = 0;
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d("TAG", "run: " + intent.getIntExtra("Cancel Alarm Task", 1));
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent1 = new Intent(LongRunningService.this, Chapter13.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("1", "LongRunningService", NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                }
                PendingIntent pendingIntent = PendingIntent.getActivity(LongRunningService.this, 0, intent1, 0);

                if (intent.getIntExtra("Cancel Alarm Task", 1) == CANCEL_ALARM_TASK){
                    Log.d("TAG", "run: STOP ");
                    Notification notification = new NotificationCompat.Builder(LongRunningService.this, "1")
                            .setContentTitle("Long Running Service")
                            .setContentText("Stop Alarm Task")
                            .setSmallIcon(R.drawable.apple_icon)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple_icon))
                            .setContentIntent(pendingIntent)
                            .build();
                    notificationManager.notify(1, notification);
                }

                if(isFirst) {
                    isFirst = false;

                    Notification notification = new NotificationCompat.Builder(LongRunningService.this, "1")
                            .setContentTitle("Long Running Service")
                            .setContentText("Start Alarm Task")
                            .setSmallIcon(R.drawable.apple_icon)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple_icon))
                            .setContentIntent(pendingIntent)
                            .build();
                    notificationManager.notify(1, notification);
                }
                else {

                    Notification notification = new NotificationCompat.Builder(LongRunningService.this, "1")
                            .setContentTitle("Long Running Service")
                            .setContentText("Alarm Task")
                            .setSmallIcon(R.drawable.apple_icon)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple_icon))
                            .setContentIntent(pendingIntent)
                            .build();

                    notificationManager.notify(1, notification);

                }
            }
        }).start();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int aMin = 60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + aMin;
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);
       // alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        Log.d("TAG", "onStartCommand: " + triggerAtTime);
        return super.onStartCommand(intent, flags, startId);
    }
}
