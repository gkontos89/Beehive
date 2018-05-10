package com.marshmallow.beehive.ui.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupBasicsActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button profileSetupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        profileSetupButton = findViewById(R.id.setup_profile_button);
        profileSetupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch profile set up activity chain
                Intent intent = new Intent(getApplicationContext(), ProfileSetupBasicsActivity.class);
                startActivity(intent);
            }
        });
    }
}
