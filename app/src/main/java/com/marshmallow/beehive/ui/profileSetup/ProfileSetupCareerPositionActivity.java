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
        loadProfileData();
    }

    @Override
    public void loadProfileData() {
        CareerPositionModel activeCareerPositionModel = ModelManager.getInstance().getActiveCareerPointPositionModel();
        careerPositionTitle.setText(activeCareerPositionModel.getPositionName());
        startDate.setText(activeCareerPositionModel.getStartDate());
        endDate.setText(activeCareerPositionModel.getEndDate());
        summary.setText(activeCareerPositionModel.getSummary());
    }

    @Override
    public void saveProfileData() {
        CareerPositionModel activeCareerPositionModel = ModelManager.getInstance().getActiveCareerPointPositionModel();
        activeCareerPositionModel.setPositionName(careerPositionTitle.getText().toString());
        activeCareerPositionModel.setStartDate(startDate.getText().toString());
        activeCareerPositionModel.setEndDate(endDate.getText().toString());
        activeCareerPositionModel.setSummary(summary.getText().toString());

        ModelManager.getInstance().saveActiveCareerPointPositionModel();
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
