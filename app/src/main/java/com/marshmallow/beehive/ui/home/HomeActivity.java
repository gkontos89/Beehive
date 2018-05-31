package com.marshmallow.beehive.ui.home;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.models.ModelManager;

public class HomeActivity extends AppCompatActivity {



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

        findViewById(R.id.load_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePictureImageView.setImageBitmap(ModelManager.getInstance().getUserModel().getProfilePictureBitmap());
            }
        });



        // TODO load data blah blah, notify when data is retrieved
        BeehiveBackend.getInstance().getUser();


//        profilePictureImageView.setImageBitmap(ModelManager.getInstance().getUserModel().getProfilePictureBitmap());



    }
}
