package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

  // Start the AlarmService when the Alarm rings
  public void onReceive(Context context, Intent intent) {
    Intent resultIntent = new Intent(context, AlarmService.class);
    context.startService(resultIntent);
  }
}
