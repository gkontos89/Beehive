package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.marshmallow.beehive.R;

public class ProfileSetupCareerActivity extends AppCompatActivity implements ProfileSetupInterface {

    Button addCareerPointButton;
    ListView careerPointListView;
    private Button backButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_career);

        // Setup GUI handles
        careerPointListView = findViewById(R.id.profile_career_list_view);
        addCareerPointButton = findViewById(R.id.add_career_point_button);
        backButton = findViewById(R.id.back_button);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();
                    Intent intent = new Intent(getApplicationContext(), ProfileSetupManager.getInstance().getNextProfileSetupActivity());
                    startActivity(intent);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();

                // TODO will this work?
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadProfileData();
    }

    @Override
    public void loadProfileData() {

    }

    @Override
    public void saveProfileData() {

    }

    @Override
    public Boolean allFieldsComplete() {
        return true;
    }
}
