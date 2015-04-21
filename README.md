Using Android Alarm Manager and Services
---------------------------------------

+ Super quick guide to using the Alarm Manager, Services, and Boot Receiver to make your Android Apps do stuff in the background.


+ Each function in each script contain concise comments saying what the proceeding blocks of code do.


###AlarmReceiver.java
It extends the *BroadcastReceiver* class, and runs the service, you wish to run intermittently in the background. To pull data from the internet, and of course, create a notification in the Notification Bar of your phone

###AlarmService.java
It extends the *Service* Class, it contains the actions you want to perform each time the Alarm is triggered. The interval for the Alarm triggering is set in the *TimeService.java* srcipt.

###TimeService.java
This *Service* helps implement the AlarmManager, setting the interval you want the Alarm (AlarmReceiver) to be called.

###MainActivity.java
This is the Main Activity of the Android Application. This is where we create the receiver and register it.
This receiver listens for an Intent with properties pointing to this Activity to be called. It runs the code in the `onReceive` code block when such intent is Broadcasted.


###AndroidManifest.xml
The Services are registered  as pertinent.
The Boot receiver is registered, as well as the permission to Boot up with the device.




_This is short and as simple as it could get. It may not be as luculent as I think it is, but I'll try to ameliorate is soon enough_
_Have Fun_


