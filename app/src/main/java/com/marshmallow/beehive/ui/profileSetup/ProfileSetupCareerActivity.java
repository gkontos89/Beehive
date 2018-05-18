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
import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;
import com.marshmallow.beehive.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProfileSetupCareerActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private Button addCareerPointButton;
    private RecyclerView careerPointRecylerView;
    private RecyclerView.LayoutManager careerPointLayoutManager;
    private List<CareerPointModel> careerPointModelList;
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
        careerPointModelList = new Vector<>();
        careerPointModelList = ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels();
        careerPointLayoutManager = new LinearLayoutManager(this);
        careerPointRecylerView.setLayoutManager(careerPointLayoutManager);
        careerPointAdapter = new CareerPointAdapter(this, careerPointModelList);
        careerPointRecylerView.setAdapter(careerPointAdapter);

        addCareerPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().generateNewCareerPointModel();
                Intent intent = new Intent(getApplicationContext(), ProfileSetupCareerPointActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();
                    BeehiveBackend.getInstance().submitUserProfileUpdates(getApplicationContext());
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
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
        // simply notify the adapter of any change that occurred in the underlying dataset
        careerPointAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveProfileData() {
        // For now there is nothing to save.  This view just contains a list of career points
        // that are already stored in the user model through the add buttons.
    }

    @Override
    public Boolean allFieldsComplete() {
        return true;
    }
}
