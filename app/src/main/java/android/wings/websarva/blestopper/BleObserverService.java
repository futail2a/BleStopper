package android.wings.websarva.blestopper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class BleObserverService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        Log.d("BleObserverService", "created");
        String id = "bleobserverservice_notification_channel";
        String name = getString(R.string.notification_channel_name);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(id, name,importance);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(BleObserverService.this, "bleobserverservice_notification_channel");

        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentTitle(getString(R.string.msg_notification_title_start));

        Intent i = new Intent(BleObserverService.this, MainActivity.class);
        i.putExtra("fromNotification", true);
        PendingIntent stopServiceIntent = PendingIntent.getActivity(BleObserverService.this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(stopServiceIntent);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, notification);
        Log.d("BleObserverService", "onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(BleObserverService.this, "bleobserverservice_notification_channel");

        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentTitle(getString(R.string.msg_notification_title_finish));

        Intent i = new Intent(BleObserverService.this, MainActivity.class);
        i.putExtra("fromNotification", true);
        PendingIntent stopServiceIntent = PendingIntent.getActivity(BleObserverService.this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(stopServiceIntent);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, notification);
        Log.d("BleObserverService", "onStartCommand");
    }

}
