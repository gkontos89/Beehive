package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.CareerPositionModel;
import com.marshmallow.beehive.models.ModelManager;

import java.util.List;
import java.util.Vector;

public class ProfileSetupCareerPointActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private EditText careerPointTitle;
    private EditText careerPointLocation;
    private EditText startDate;
    private EditText endDate;
    private Button addCareerPositionButton;
    private Button saveCareerPointButton;
    private Button cancelButton;
    private RecyclerView careerPointPositionRecyclerView;
    private RecyclerView.LayoutManager careerPointPositionLayoutManager;

    // Recycler items
    private List<CareerPositionModel> careerPositionModelList;
    private CareerPositionAdapter careerPositionAdapter;

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
        careerPointPositionRecyclerView = findViewById(R.id.career_positions_rv);
        saveCareerPointButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Set up recycler and view adapters
        careerPositionModelList = new Vector<>();
        careerPositionModelList = ModelManager.getInstance().getActiveCareerPointModel().getCareerPositionModels();
        careerPointPositionLayoutManager = new LinearLayoutManager(this);
        careerPointPositionRecyclerView.setLayoutManager(careerPointPositionLayoutManager);
        careerPositionAdapter = new CareerPositionAdapter(this, careerPositionModelList);
        careerPointPositionRecyclerView.setAdapter(careerPositionAdapter);

        addCareerPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save UI fields data into activeCareerPoint before leaving screen in case this is a new
                // career point
                if (!ModelManager.getInstance().userStoryContainsActiveCareerPointModel()) {
                    CareerPointModel activeCareerPointModel = ModelManager.getInstance().getActiveCareerPointModel();
                    activeCareerPointModel.setName(careerPointTitle.getText().toString());
                    activeCareerPointModel.setLocation(careerPointLocation.getText().toString());
                    activeCareerPointModel.setStartDate(startDate.getText().toString());
                    activeCareerPointModel.setEndDate(endDate.getText().toString());
                }

                ModelManager.getInstance().generateNewCareerPointPositionModel();
                Intent intent = new Intent(getApplicationContext(), ProfileSetupCareerPositionActivity.class);
                startActivity(intent);
            }
        });

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
        loadProfileData();
    }

    @Override
    public void loadProfileData() {
        CareerPointModel activeCareerPointModel = ModelManager.getInstance().getActiveCareerPointModel();
        careerPointTitle.setText(activeCareerPointModel.getName());
        careerPointLocation.setText(activeCareerPointModel.getLocation());
        startDate.setText(activeCareerPointModel.getStartDate());
        endDate.setText(activeCareerPointModel.getEndDate());
        careerPositionAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveProfileData() {
        CareerPointModel activeCareerPointModel = ModelManager.getInstance().getActiveCareerPointModel();
        activeCareerPointModel.setName(careerPointTitle.getText().toString());
        activeCareerPointModel.setLocation(careerPointLocation.getText().toString());
        activeCareerPointModel.setStartDate(startDate.getText().toString());
        activeCareerPointModel.setEndDate(endDate.getText().toString());

        ModelManager.getInstance().saveActiveCareerPointModel();
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
