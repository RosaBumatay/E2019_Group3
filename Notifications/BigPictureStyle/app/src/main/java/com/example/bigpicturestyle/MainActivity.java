package com.example.bigpicturestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNotify = (Button)findViewById(R.id.btnShow);
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();

                bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_stat_name)).build();
                Intent rIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tutlane.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
                pendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
                NotificationCompat.Builder mBuilder
                        = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle("Big Picture Notification Example")
                        .addAction(R.drawable.ic_stat_name, "Share", pendingIntent)
                        .setStyle(bpStyle);
                mBuilder.setContentIntent(pendingIntent);

                int mNotificationId = 001;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(mNotificationId,
                        mBuilder.build());

            }
        });
    }
}