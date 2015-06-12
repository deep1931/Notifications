package biginteger.notification.example;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements View.OnClickListener {

    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private Bitmap bitmapLargeIcon;

    private Button btnNormalStyle;
    private Button btnBigTextStyle;
    private Button btnBigPictureStyle;
    private Button btnInboxStyle;
    private Button btnTickerText;
    private Button btnCustom;
    private Button btnProgress;
    private Button btnProgressContinuous;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    private void init() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification = new Notification();
        bitmapLargeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        btnNormalStyle = (Button) findViewById(R.id.btnNormal);
        btnBigTextStyle = (Button) findViewById(R.id.btnBigTextStyle);
        btnBigPictureStyle = (Button) findViewById(R.id.btnBigPicture);
        btnInboxStyle = (Button) findViewById(R.id.btnInboxStyle);
        btnTickerText = (Button) findViewById(R.id.btnTickerText);
        btnCustom = (Button) findViewById(R.id.btnCustom);
        btnProgress = (Button) findViewById(R.id.btnProgress);
        btnProgressContinuous = (Button) findViewById(R.id.btnProgressContinuous);

        btnNormalStyle.setOnClickListener(this);
        btnBigTextStyle.setOnClickListener(this);
        btnBigPictureStyle.setOnClickListener(this);
        btnInboxStyle.setOnClickListener(this);
        btnTickerText.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        btnProgressContinuous.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btnNormalStyle.getId()) {
            showNormalNotification();
        } else if (v.getId() == btnBigTextStyle.getId()) {
            showBigTextStyleNotification();
        } else if (v.getId() == btnBigPictureStyle.getId()) {
            showBigPictureStyleNotification();
        } else if (v.getId() == btnInboxStyle.getId()) {
            showInboxStyleNotification();
        } else if (v.getId() == btnTickerText.getId()) {
            showInboxStyleNotificationWithTickerText();
        } else if (v.getId() == btnCustom.getId()) {
            showCustomViewNotification();
        } else if (v.getId() == btnProgress.getId()) {
            showPrgressbarNotificatioin();
        } else if (v.getId() == btnProgressContinuous.getId()) {
            showContinuousPrgressbarNotificatioin();
        }

        mNotification.defaults |= Notification.DEFAULT_LIGHTS;
        mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotification.defaults |= Notification.DEFAULT_SOUND;

        mNotification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;

        mNotificationManager.notify(0, mNotification);
    }


    /**
     * Normal Notification
     */
    private void showNormalNotification() {

        // Setup an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // TaskStackBuilder ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLargeIcon)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_end_call, "DISCONNECT", resultPendingIntent)
                .addAction(R.drawable.ic_pick_call, "ANSWER", resultPendingIntent)
                .setContentTitle("Normal Notification")
                .setContentText("Example of Normal Style Notification.").build();
    }

    /**
     * Big Text Style Notification
     */
    private void showBigTextStyleNotification() {

        // Create the style object with BigTextStyle subclass.
        NotificationCompat.BigTextStyle notificationStyle = new NotificationCompat.BigTextStyle();
        notificationStyle.setBigContentTitle("Big Text After Expand");
        notificationStyle.setSummaryText("Here is lot of space to add text..");

        // Add the big text to the style.
        CharSequence bigText = "In this category of mNotification you can add long text which you want to show" +
                " after all it is 'BIG TEXT STYLE' mNotification.";
        notificationStyle.bigText(bigText);

        // Creates an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLargeIcon)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_end_call, "DISCONNECT", resultPendingIntent)
                .addAction(R.drawable.ic_pick_call, "ANSWER", resultPendingIntent)
                .setContentTitle("Big Text Style")
                .setContentText("Big text is waiting.Expand.")
                .setStyle(notificationStyle).build();
    }

    /**
     * Big Picture Style Notification
     */
    private void showBigPictureStyleNotification() {
        // Create the style object with BigPictureStyle subclass.
        NotificationCompat.BigPictureStyle notificationStyle = new NotificationCompat.BigPictureStyle();
        notificationStyle.setBigContentTitle("Big Picture After Expand");
        notificationStyle.setSummaryText("Picture added in mNotification area. ");


        Bitmap bigPicture = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo_biginteger);

        // Add the big picture to the style.
        notificationStyle.bigPicture(bigPicture);

        // Creates an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLargeIcon)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_notification_done, "CLICK", resultPendingIntent)
                .addAction(R.drawable.ic_notification_done, "CLICK", resultPendingIntent)
                .addAction(R.drawable.ic_notification_done, "CLICK", resultPendingIntent)
                .setContentTitle("Big Picture Notification")
                .setContentText("I have a picture for you. Expand.")
                .setStyle(notificationStyle).build();
    }

    /**
     * Inbox Style Notification
     */
    private void showInboxStyleNotification() {
        // Bitmap remote_picture = null;

        // Create the style object with InboxStyle subclass.
        NotificationCompat.InboxStyle notificationStyle = new NotificationCompat.InboxStyle();
        notificationStyle.setBigContentTitle("Sandeep");

        // Add the multiple lines to the style.
        // This is strictly for providing an example of multiple lines.

        notificationStyle.addLine("Hello ");
        notificationStyle.addLine("How are you? ");
        notificationStyle.addLine("What going on? ");
        notificationStyle.addLine("Where are you? ");

        notificationStyle.setSummaryText("4 new messages");


        // Creates an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLargeIcon)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_reply, "REPLY", resultPendingIntent)
                .addAction(R.drawable.ic_pick_call, "CALL", resultPendingIntent)
                .addAction(R.drawable.ic_delete, "DELETE", resultPendingIntent)
                .setContentTitle("Inbox Style Notification ")
                .setContentText("Some new message.Expand me.")
                .setStyle(notificationStyle).build();
    }

    /**
     * Custom View Notification
     */
    private void showCustomViewNotification() {

        // Creates an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create remote view and set bigContentView.
        RemoteViews expandedView = new RemoteViews(this.getPackageName(), R.layout.custom_notification);
        expandedView.setTextViewText(R.id.custom_txtTitle, "You text will replace me!");

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("Custom View Notification")
                .setContentText("I am a custom mNotification.").build();


        mNotification.bigContentView = expandedView;

    }


    /**
     * Inbox Style Notification
     */
    private void showInboxStyleNotificationWithTickerText() {

        // Create the style object with InboxStyle subclass.
        NotificationCompat.InboxStyle notificationStyle = new NotificationCompat.InboxStyle();
        notificationStyle.setBigContentTitle("Sandeep");


        notificationStyle.addLine("Hello ");
        notificationStyle.addLine("How are you? ");
        notificationStyle.addLine("What going on? ");
        notificationStyle.addLine("Where are you? ");

        notificationStyle.setSummaryText("4 new messages");


        // Creates an explicit intent for an CallingActivity to receive.
        Intent resultIntent = new Intent(this, CallingActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(CallingActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification_done)
                .setAutoCancel(true)
                .setLargeIcon(bitmapLargeIcon)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_reply, "REPLY", resultPendingIntent)
                .addAction(R.drawable.ic_pick_call, "CALL", resultPendingIntent)
                .addAction(R.drawable.ic_delete, "DELETE", resultPendingIntent)
                .setContentTitle("Inbox Style Notification ")
                .setContentText("Some new message.Expand me.")
                .setStyle(notificationStyle).build();
        mNotification.tickerText = "New message from Sandeep";
    }

    private void showPrgressbarNotificatioin() {
        final int id = 1;
        final NotificationCompat.Builder notificationStyle = new NotificationCompat.Builder(this);
        notificationStyle.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher);
        // Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            notificationStyle.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            mNotificationManager.notify(id, notificationStyle.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 2 seconds
                                Thread.sleep(2 * 1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        // When the loop is finished, updates the notification
                        notificationStyle.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);
                        mNotificationManager.notify(id, notificationStyle.build());
                    }
                }
                // Starts the thread by calling the run() method in its Runnable
        ).start();
    }

    private void showContinuousPrgressbarNotificatioin() {
        final int id = 1;
        final NotificationCompat.Builder notifyStyle = new NotificationCompat.Builder(this);
        notifyStyle.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher);
        // Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            notifyStyle.setProgress(100, incr, true);
                            // Displays the progress bar for the first time.
                            mNotificationManager.notify(id, notifyStyle.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 1 seconds
                                Thread.sleep(1 * 1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        // When the loop is finished, updates the notification
                        notifyStyle.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);
                        mNotificationManager.notify(id, notifyStyle.build());
                    }
                }
                // Starts the thread by calling the run() method in its Runnable
        ).start();
    }
}
