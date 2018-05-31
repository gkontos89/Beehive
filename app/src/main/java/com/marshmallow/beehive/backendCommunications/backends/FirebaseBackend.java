package com.marshmallow.beehive.backendCommunications.backends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.marshmallow.beehive.backendCommunications.broadcasts.CreateUserStatusBroadcast;
import com.marshmallow.beehive.backendCommunications.broadcasts.SignInStatusBroadcast;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;

/**
 * This class implements Firebase backend communications
 *
 * Created by George on 4/29/2018.
 */
public class FirebaseBackend implements BeehiveBackendInterface {
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;

    public FirebaseBackend() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    @Override
    public Boolean isUserSignedIn() {
        return (firebaseAuth.getCurrentUser() != null);
    }

    @Override
    public void createUserWithEmailAndPassword(final Context context, final Activity activity, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener< AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast(null, null);
                            Intent intent = createUserStatusBroadcast.getSuccessfulBroadcast();
                            context.sendBroadcast(intent);
                        } else {
                            CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast(task.getException().getMessage(), null);
                            Intent intent = createUserStatusBroadcast.getFailureBroadcast();
                            context.sendBroadcast(intent);
                        }


                    }
                });
    }

    @Override
    public void signInWithEmailAndPassword(final Context context, final Activity activity, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SignInStatusBroadcast signInStatusBroadcast = new SignInStatusBroadcast(null, null);
                            Intent intent = signInStatusBroadcast.getSuccessfulBroadcast();
                            context.sendBroadcast(intent);
                        } else {
                            SignInStatusBroadcast signInStatusBroadcast = new SignInStatusBroadcast(task.getException().getMessage(), null);
                            Intent intent = signInStatusBroadcast.getFailureBroadcast();
                            context.sendBroadcast(intent);
                        }
                    }
                });
    }

    @Override
    public void signOutUser() {
        firebaseAuth.signOut();
    }

    @Override
    public void setSessionId(String sessionId) {
        // Stubbed from interface
    }

    @Override
    public String getSessionId() { return null; }

    @Override
    public void submitUserProfileUpdates(Context context) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(getUserId()).setValue(ModelManager.getInstance().getUserModel());
    }

    @Override
    public String getUserId() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public void getUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ModelManager.getInstance().userModel = dataSnapshot.getValue(UserModel.class);
                ModelManager.getInstance().gotIt = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
