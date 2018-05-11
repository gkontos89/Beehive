package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;

public class ProfileSetupCareerPointActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private EditText careerPointTitle;
    private EditText careerPointLocation;
    private EditText startDate;
    private EditText endDate;
    private Button addCareerPositionButton;
    private Button saveCareerPointButton;
    private Button cancelButton;

    // Broadcast info
    private static final String careerPointIndexKeyString = "careerPointIndexKey";
    public static final String getCareerPointIndexKeyString() { return careerPointIndexKeyString; }

    // State info
    CareerPointModel loadedCareerPointModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_career_point);

        // Setup GUI handles
        careerPointTitle = findViewById(R.id.career_point_text);
        careerPointLocation = findViewById(R.id.career_point_location_text);
        startDate = findViewById(R.id.start_date_text);
        endDate = findViewById(R.id.end_date_text);
        addCareerPositionButton = findViewById(R.id.add_career_position_button);
        saveCareerPointButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        // On save button, create career point model and save to user
        saveCareerPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent.getIntExtra(getCareerPointIndexKeyString(), -1) != -1) {
            int index = intent.getIntExtra(getCareerPointIndexKeyString(), -1);
            loadedCareerPointModel = ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().get(index);
        }

        loadProfileData();
    }

    @Override
    public void loadProfileData() {
        if (loadedCareerPointModel != null) {
            careerPointTitle.setText(loadedCareerPointModel.getName());
            careerPointLocation.setText(loadedCareerPointModel.getLocation());
            startDate.setText(loadedCareerPointModel.getStartDate());
            endDate.setText(loadedCareerPointModel.getEndDate());

            // TODO get career positions
        }
    }

    @Override
    public void saveProfileData() {
        CareerPointModel careerPointModel = new CareerPointModel();
        careerPointModel.setName(careerPointTitle.getText().toString());
        careerPointModel.setLocation(careerPointLocation.getText().toString());
        careerPointModel.setStartDate(startDate.getText().toString());
        careerPointModel.setEndDate(endDate.getText().toString());

        // TODO career positions

        if (loadedCareerPointModel != null) {
            loadedCareerPointModel = careerPointModel;
        } else {
            ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().add(careerPointModel);
        }
    }

    @Override
    public Boolean allFieldsComplete() {
        if (TextUtils.isEmpty(careerPointTitle.getText().toString())) {
            careerPointTitle.setError("Career Point title cannot be empty");
            return false;
        } else if (TextUtils.isEmpty(careerPointLocation.getText().toString())) {
            careerPointLocation.setError("Location cannot be empty");
            return false;
        } else if (TextUtils.isEmpty(startDate.getText().toString())) {
            startDate.setError("Start date cannot be empty");
            return false;
        } else if (TextUtils.isEmpty(endDate.getText().toString())) {
            endDate.setError("End date cannot be empty");
            return false;
        }

        return true;
    }
}
