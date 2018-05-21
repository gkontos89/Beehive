package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.auth.data.model.User;
import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.StoryModel;
import com.marshmallow.beehive.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProfileSetupSummaryActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private EditText summaryText;
    private EditText pursuitTextEntry;
    private Button addPursuitButton;
    private RecyclerView pursuitsRecyclerView;
    private RecyclerView.LayoutManager pursuitsLayoutManager;
    private List<String> pursuitsList;
    private PursuitsAdapter pursuitsAdapter;

    // TODO story questions w/ recycler view

    private Button backButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_summary);

        // Setup GUI handles
        summaryText = findViewById(R.id.summary_text_entry);
        pursuitTextEntry = findViewById(R.id.pursuit_text_entry);
        addPursuitButton = findViewById(R.id.add_pursuit_button);
        pursuitsRecyclerView = findViewById(R.id.pursuits_list);
        backButton = findViewById(R.id.back_button);
        nextButton = findViewById(R.id.next_button);

        // List and adapter to handle pursuits
        pursuitsList = new Vector<>();
        pursuitsList = ModelManager.getInstance().getUserModel().getUserStory().getPursuits();
        pursuitsLayoutManager = new LinearLayoutManager(this);
        pursuitsRecyclerView.setLayoutManager(pursuitsLayoutManager);
        pursuitsAdapter = new PursuitsAdapter(pursuitsList);
        pursuitsRecyclerView.setAdapter(pursuitsAdapter);


        addPursuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(pursuitTextEntry.getText().toString())) {
                    pursuitsList.add(pursuitTextEntry.getText().toString());
                    pursuitsAdapter.notifyDataSetChanged();
                    pursuitTextEntry.setText("");
                } else {
                    pursuitTextEntry.setError("Pursuit cannot be empty");
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();
                    Intent intent = new Intent(getApplicationContext(), ProfileSetupCareerActivity.class);
                    startActivity(intent);
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
    public void saveProfileData() {
        StoryModel storyModel = ModelManager.getInstance().getUserModel().getUserStory();
        storyModel.setSummary(summaryText.getText().toString());

        // TODO get story questions
    }

    @Override
    public Boolean allFieldsComplete() {
        // TODO check for empty story questions in recycler view
        if (TextUtils.isEmpty(summaryText.getText().toString())) {
            summaryText.setError("Summary text cannot be empty");
            return false;
        }

        return true;
    }

    @Override
    public void loadProfileData() {
        StoryModel storyModel = ModelManager.getInstance().getUserModel().getUserStory();
        summaryText.setText(storyModel.getSummary());
        pursuitsAdapter.notifyDataSetChanged();
        // TODO load story questions
    }
}
