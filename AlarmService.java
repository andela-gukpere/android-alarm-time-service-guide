package com.example;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class AlarmService extends Service {
  private Handler mHandler = new Handler();

  public IBinder onBind(Intent intent) {
    return null;
  }

  public int onStartCommand(Intent intent, int flags, int startId) {
    mHandler.removeCallbacks(r);
    mHandler.post(r);
    return super.onStartCommand(intent, flags, startId);
  }

  private final int mNotificationId = 5514;

  // Build your notification widget
  void notifyMe(String from, String title, int count, Bitmap bitmap) {

    // Specify the intent to be triggered when the Notification is clicked on
    Intent resultIntent = new Intent(this, MainActivity.class);
    resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

    // here you can put in extra intents, values you want to be received by the Receiving activity, when a Notification is clicked
    resultIntent.putExtra("RQS", 1);
    PendingIntent resultPendingIntent =
            PendingIntent.getActivity(
                    this,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
    NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(bitmap)
                    .setContentTitle(from)
                    .setAutoCancel(true)
                    .setTicker(title) //Ticker texts flash in the message bar for a couple of seconds while the notification is still fresh
                    .setLights(Color.argb(255,255,100,0), 500, 5000)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setNumber(count)
                    .setPriority(2)
                    .setContentText(title);
    mBuilder.setContentIntent(resultPendingIntent);


    // Get Notification manager service
    NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    // mNotificationId and ID used to represent Notificaitons from our application
    mNotifyMgr.notify(mNotificationId, mBuilder.build());
  }

  // A runnable to perform actions when the Alarm is fired
  private Runnable r = new Runnable() {
    public void run() {
      // Do stuff every time this service is called by the Alarm, every 60 seconds in this example
      new Thread(new Runnable() {
        public void run() {


          Bitmap bitmap = null;
          try {
            // Get Image from Internet
            InputStream in = new java.net.URL("http://img.exmaple.com/image.jpg").openStream();
            bitmap = BitmapFactory.decodeStream(in);
          }
          catch (Exception e0) {
            // Get a default image from resources
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
          }
          // Pass in a bitmap to represent the big image for the notification
          // Not required, but it's a cool addition
          notifyMe(getString(R.string.app_name), "You have 5 new updates", 5, bitmap);

        }
      }).start();
    }
  };
}
