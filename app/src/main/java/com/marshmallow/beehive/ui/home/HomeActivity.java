package com.marshmallow.beehive.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.backendCommunications.BeehiveBackend;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button signOutButton = findViewById(R.id.sign_out_button);
        Button deleteAccountButton = findViewById(R.id.delete_account_button);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeehiveBackend.getInstance().signOut();
                finish();
            }
        });
    }
}
