package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;

import java.util.ArrayList;

public class ProfileSetupSummaryActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI handles
    private EditText summaryText;
    private EditText pursuitTextEntry;
    private Button addPursuitButton;
    private ListView pursuitsList;

    // TODO story questions w/ recycler view

    private Button backButton;
    private Button nextButton;

    private ArrayList<String> pursuits;
    private ArrayAdapter<String> pursuitsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_summary);

        // Setup GUI handles
        summaryText = findViewById(R.id.summary_text_entry);
        pursuitTextEntry = findViewById(R.id.pursuit_text_entry);
        addPursuitButton = findViewById(R.id.add_pursuit_button);
        pursuitsList = findViewById(R.id.pursuits_list);
        backButton = findViewById(R.id.back_button);
        nextButton = findViewById(R.id.next_button);

        // List and adapter to handle pursuits
        pursuits = new ArrayList<>();
        pursuitsAdapter = new ArrayAdapter<String>(this, 0, pursuits);
        pursuitsList.setAdapter(pursuitsAdapter);

        addPursuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pursuitTextEntry.getText().toString().isEmpty()) {
                    pursuitsAdapter.add(pursuitTextEntry.getText().toString());
                    pursuitTextEntry.setText("");
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
    public void saveProfileData() {
        String summaryTextString = summaryText.getText().toString();
        ModelManager.getInstance().getUserModel().getUserStory().setSummary(summaryTextString);
        for (int i=0; i<pursuitsAdapter.getCount(); i++) {
            ModelManager.getInstance().getUserModel().getUserStory().getPursuits().add(pursuitsAdapter.getItem(i));
        }

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
        UserModel userModel = ModelManager.getInstance().getUserModel();
        summaryText.setText(userModel.getUserStory().getSummary());
        pursuits.addAll(userModel.getUserStory().getPursuits());

        // TODO load story questions
    }
}
