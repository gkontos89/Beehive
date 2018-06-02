package com.marshmallow.beehive.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.backendCommunications.broadcasts.LoadUserStatusBroadcast;
import com.marshmallow.beehive.models.ModelManager;

public class HomeActivity extends AppCompatActivity {


    // BroadcastReceiver
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button signOutButton = findViewById(R.id.sign_out_button);
        Button deleteAccountButton = findViewById(R.id.delete_account_button);
        final ImageView profilePictureImageView = findViewById(R.id.profile_picture_image);


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeehiveBackend.getInstance().signOutUser();
                finish();
            }
        });

        // Set up broadcastReceiver for loading the user profile data
        intentFilter = new IntentFilter();
        intentFilter.addAction(LoadUserStatusBroadcast.action);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }
}
