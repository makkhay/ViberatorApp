package com.example.makkhay.viberatorapp;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VibratorDemoActivity extends AppCompatActivity implements View.OnClickListener
{


    private Button vibrateButton;
    private Button vibrateButton1;
    private Button vibrateButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator_demo);
        //onClick events
        vibrateButton = (Button) findViewById(R.id.vibrateButton);
        vibrateButton.setOnClickListener(this);
        vibrateButton1 = (Button) findViewById(R.id.vibrateButton1);
        vibrateButton1.setOnClickListener(this);
        vibrateButton2 = (Button) findViewById(R.id.vibrateButton2);
        vibrateButton2.setOnClickListener(this);

    }


    /**
     * Push notification is enabled when a button is pressed
     * You also need to make changes in manifest file. you need to add another intent from NotifyMessage.java
     */
    public void showNotification(final String text)
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("My Vibrate Notification");
        builder.setContentText(text);
        Intent intent = new Intent(this,NotifyMessage.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotifyMessage.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,builder.build());
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.vibrateButton:
                //  Vibrator vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // triplets .. start, last, pause (repeat)
                long[] pattern = {
                        0, 100, 1000,
                        300, 200, 100,
                        500, 200, 100
                };
                vibrator.vibrate(new long[]{2000, 500,500}, -1);
                // -1 means go forever
                vibrator.vibrate(pattern, -1);
                showNotification("Short vibration is activated");
                Toast.makeText(this,"Short vibration is activated", Toast.LENGTH_LONG).show();

                if (vibrator.hasVibrator())
                {
                    Log.d("Vibrate", "Yes, Has Vibrator.");
                    Toast.makeText(v.getContext(), "This device has a vibrator",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "This device doesn't have a vibrator",
                            Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.vibrateButton1:
                //  Vibrator vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
                Vibrator vibrator1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // triplets .. start, last, pause (repeat)
                //The first value is the time to wait until start vibration,
                // second one is vibration duration, and the third is pause.
                long[] pattern1 = {
                        0, 2000, 1000,
                        300, 200, 100,
                        500, 20000, 100
                };

                vibrator1.vibrate(new long[]{2000, 500,500}, -1);
                // -1 means go forever
                vibrator1.vibrate(pattern1, -1);

                Toast.makeText(this,"Long vibration is activated", Toast.LENGTH_LONG).show();
                showNotification("Long vibration is activated");
                break;

            case R.id.vibrateButton2:

                //  Vibrator vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
                Vibrator vibrator2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // triplets .. start, last, pause (repeat)
                //The first value is the time to wait until start vibration,
                // second one is vibration duration, and the third is pause.
                long[] pattern2 = {
                         0,1000,
                        300, 200, 100,
                        500, 200, 100
                };

                vibrator2.vibrate(pattern2, -1);
                showNotification("Medium vibration is activated");
                Toast.makeText(this,"Medium vibration is activated", Toast.LENGTH_LONG).show();
                break;

        }

    }
}





