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
import com.marshmallow.beehive.models.CareerPositionModel;
import com.marshmallow.beehive.models.ModelManager;

import org.w3c.dom.Text;

public class ProfileSetupCareerPositionActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private EditText careerPositionTitle;
    private EditText startDate;
    private EditText endDate;
    private EditText summary;
    private Button saveButton;
    private Button cancelButton;

    // Broadcast info
    private static final String careerPointIndexKeyString = "careerPointIndexKey";
    public static final String getCareerPointIndexKeyString() { return careerPointIndexKeyString; }
    private static final String careerPositionIndexKeyString = "careerPositionIndexKey";
    public static final String getCareerPositionIndexKeyString() { return careerPositionIndexKeyString; }

    // State info
    CareerPositionModel loadedCareerPositionModel = null;
    int careerPointIndex = -1;
    int careerPositionIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_career_position);

        // Setup GUI handles
        careerPositionTitle = findViewById(R.id.career_position_text);
        startDate = findViewById(R.id.start_date_text);
        endDate = findViewById(R.id.end_date_text);
        summary = findViewById(R.id.career_position_summary);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
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
        careerPointIndex = intent.getIntExtra(getCareerPointIndexKeyString(), -1);
        careerPositionIndex = intent.getIntExtra(getCareerPositionIndexKeyString(), -1);

        if (careerPointIndex != -1 && careerPositionIndex != -1) {
            loadedCareerPositionModel = ModelManager.getInstance()
                    .getUserModel()
                    .getUserStory()
                    .getCareerPointModels().get(careerPointIndex)
                    .getCareerPositionModels().get(careerPositionIndex);
        }

        loadProfileData();
    }

    @Override
    public void loadProfileData() {
        if (loadedCareerPositionModel != null) {
            careerPositionTitle.setText(loadedCareerPositionModel.getPositionName());
            startDate.setText(loadedCareerPositionModel.getStartDate());
            endDate.setText(loadedCareerPositionModel.getEndDate());
            summary.setText(loadedCareerPositionModel.getSummary());
        }
    }

    @Override
    public void saveProfileData() {
        CareerPositionModel careerPositionModel = new CareerPositionModel();
        careerPositionModel.setPositionName(careerPositionTitle.getText().toString());
        careerPositionModel.setStartDate(startDate.getText().toString());
        careerPositionModel.setEndDate(endDate.getText().toString());
        careerPositionModel.setSummary(summary.getText().toString());

        if (loadedCareerPositionModel != null) {
            loadedCareerPositionModel = careerPositionModel;
        } else if (careerPointIndex != -1){
            CareerPointModel careerPointModel = ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().get(careerPointIndex);
            careerPointModel.getCareerPositionModels().add(careerPositionModel);
        }else {
            ModelManager.getInstance().getTempCareerPointModel().getCareerPositionModels().add(careerPositionModel);
        }
    }

    @Override
    public Boolean allFieldsComplete() {
        if (TextUtils.isEmpty(careerPositionTitle.getText().toString())) {
            careerPositionTitle.setError("Career position title cannot be empty");
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
