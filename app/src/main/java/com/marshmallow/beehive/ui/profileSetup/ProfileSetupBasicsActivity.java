package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.ModelManager;

import java.io.IOException;

public class ProfileSetupBasicsActivity extends AppCompatActivity implements ProfileSetupInterface {

    // GUI Handles
    EditText profileNameText;
    EditText profileQuickPitchText;
    ImageButton profileImageButton;
    Button nextButton;

    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_basics);

        // Setup GUI handles
        profileNameText = findViewById(R.id.profile_name_text);
        profileQuickPitchText = findViewById(R.id.profile_quick_pitch_text);
        profileImageButton = findViewById(R.id.profile_picture_image_button);
        nextButton = findViewById(R.id.next_button);

        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete()) {
                    saveProfileData();
                    Intent intent = new Intent(getApplicationContext(), ProfileSetupSummaryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profileImageButton.setImageBitmap(bitmap);
                // Log.d(TAG, String.valueOf(bitmap));

//                ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadProfileData();
    }

    @Override
    public void saveProfileData() {
        // TODO Handle profile picture
        String profileName = profileNameText.getText().toString();
        String profileQuickPitch = profileQuickPitchText.getText().toString();
        ModelManager.getInstance().getUserModel().setUserName(profileName);
        ModelManager.getInstance().getUserModel().setQuickPitch(profileQuickPitch);
    }

    @Override
    public Boolean allFieldsComplete() {
        // TODO how to handle picture selection
        if (TextUtils.isEmpty(profileNameText.getText().toString())) {
            profileNameText.setError("Profile name cannot be empty.");
            return false;
        } else if (TextUtils.isEmpty(profileQuickPitchText.getText().toString())) {
            profileQuickPitchText.setError("Quick pitch cannot be empty.");
            return false;
        }

        return true;
    }

    @Override
    public void loadProfileData() {
        profileNameText.setText(ModelManager.getInstance().getUserModel().getUserName());
        profileQuickPitchText.setText(ModelManager.getInstance().getUserModel().getQuickPitch());
        // TODO handle profile picture
    }
}
