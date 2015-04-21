package com.example;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;


public class MainActivity extends Activity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Begin the Time Service when the main activity launches
    startService(new Intent(this, TimeService.class));
    registerReceiver();
  }

  private final String BROADCAST_ACTION = "com.example.VIEW_ACTION";

  // Receive the action from the notification item when its clicked
  // This receiver can be used to receive intents from other applications as well not just our Notification
  BroadcastReceiver notifyServiceReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      //intent, from the arguments will contain the parameters from the Notification used to trigger our IntentFilter
      startActivityIfNeeded(new Intent(getApplicationContext(), MainActivity.class), 1);
    }
  };

  // Register the Intent Receiver with tha Broadcast action it's to be called with
  private void registerReceiver() {
    IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
    registerReceiver(notifyServiceReceiver, intentFilter);
  }

  private void unregisterReceiver() {
    try {
      this.unregisterReceiver(notifyServiceReceiver);
    } catch (Exception e) {
    }
  }

  @Override
  protected void onDestroy() {
    unregisterReceiver();
    super.onDestroy();
  }

  @Override
  protected void onPause() {
    unregisterReceiver();
    super.onPause();
  }

  protected void onResume() {
    registerReceiver();
    super.onResume();
  }


}
