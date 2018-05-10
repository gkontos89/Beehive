package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;
import com.marshmallow.beehive.ui.home.HomeActivity;

import java.util.ArrayList;

public class ProfileSetupCareerActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private Button addCareerPointButton;
    private RecyclerView careerPointRecylerView;
    private RecyclerView.LayoutManager careerPointLayoutManager;
    private ArrayList<CareerPointModel> careerPointModelArrayList;
    private CareerPointAdapter careerPointAdapter;

    private Button backButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_career);

        // Setup GUI handles
        careerPointRecylerView = findViewById(R.id.profile_career_rv);
        addCareerPointButton = findViewById(R.id.add_career_point_button);
        backButton = findViewById(R.id.back_button);
        submitButton = findViewById(R.id.submit_button);

        // Set up recycler and view adapters
        careerPointModelArrayList = new ArrayList<>();
        careerPointLayoutManager = new LinearLayoutManager(this);
        careerPointRecylerView.setLayoutManager(careerPointLayoutManager);
        careerPointAdapter = new CareerPointAdapter(careerPointModelArrayList);
        careerPointRecylerView.setAdapter(careerPointAdapter);

        addCareerPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careerPointModelArrayList.add(new CareerPointModel());
                careerPointAdapter.notifyDataSetChanged();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();

                    // TODO submit data to backend
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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
        UserModel userModel = ModelManager.getInstance().getUserModel();
        careerPointModelArrayList.addAll(userModel.getUserStory().getCareerPointModels());
        careerPointAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveProfileData() {
        UserModel userModel = ModelManager.getInstance().getUserModel();
        userModel.getUserStory().getCareerPointModels().clear();
        for (int i=0; i<careerPointLayoutManager.getItemCount(); i++){
            View view = careerPointLayoutManager.findViewByPosition(i);
            CareerPointModel careerPointModel = new CareerPointModel();

            // Grab view elements from the careerPoint view
            EditText careerPointName = view.findViewById(R.id.career_point_text);
            EditText location = view.findViewById(R.id.career_point_location_text);
            EditText startDate = view.findViewById(R.id.start_date_text);
            EditText endDate = view.findViewById(R.id.end_date_text);

            // TODO get career point positions

            // Map the view elements to the model and add to the user
            careerPointModel.setName(careerPointName.getText().toString());
            careerPointModel.setLocation(location.getText().toString());
            careerPointModel.setStartDate(startDate.getText().toString());
            careerPointModel.setEndDate(endDate.getText().toString());

            userModel.getUserStory().getCareerPointModels().add(careerPointModel);
        }

    }

    @Override
    public Boolean allFieldsComplete() {
        return true;
    }
}
