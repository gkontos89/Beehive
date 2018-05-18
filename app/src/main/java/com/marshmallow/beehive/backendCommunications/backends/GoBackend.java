package com.marshmallow.beehive.backendCommunications.backends;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.marshmallow.beehive.backendCommunications.webServices.CreateUserWebService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by George on 5/15/2018.
 */
public class GoBackend implements BeehiveBackendInterface {

    private Boolean userSignedIn = false;
    private String resourceId = null;
    private String sessionId = null;

    public GoBackend() {

    }

    @Override
    public Boolean isUserSignedIn() {
        return userSignedIn;
    }

    @Override
    public void createUserWithEmailAndPassword(Context context, Activity activity, String email, String password) {
        Intent intent = new Intent(context, CreateUserWebService.class);
        intent.putExtra(CreateUserWebService.emailKey, email);
        intent.putExtra(CreateUserWebService.passwordKey, password);
        context.startService(intent);
    }

    @Override
    public void signInWithEmailAndPassword(Context context, Activity activity, String email, String password) {

    }

    @Override
    public void signOutUser() {

    }

    @Override
    public void setAccountIds(String resourceId, String sessionId) {
        this.resourceId = resourceId;
        this.sessionId = sessionId;
        userSignedIn = true;
    }

    @Override
    public String getResourceId() { return null; }

    @Override
    public String getSessionId() { return null; }


//    public class WebClient extends IntentService {
//
//        public static final String REQUEST_METHOD = "requestMethod";
//        public static final String URL = "url";
//        public static final String FAILURE_BROADCAST = "failureBroadcast";
//        public static final String SUCCESS_BROADCAST = "successBroadcast";
//        public static final String DATA = "data";
//
//        private Intent receivedIntent;
//        private String s;
//
//
//        public WebClient() {
//            super("WebClient");
//        }
//
//        public void setter(String s) {
//            this.s = s;
//        }
//
//        @Override
//        protected void onHandleIntent(@Nullable Intent intent) {
//            receivedIntent = intent;
//            if (receivedIntent.getStringExtra(REQUEST_METHOD) != null) {
//                switch(receivedIntent.getStringExtra(REQUEST_METHOD)) {
//                    case "POST":
//                        isUserSignedIn();
//                        try{
//
//                        } catch (Exception e) {
//
//                        }
//                        //handlePostAction();
//                        break;
//                    case "GET":
//                        handleGetAction();
//                        break;
//                }
//            }
//
//        }
//
//        private void handleGetAction() {
//            try {
//                // Request for data and format the response to string
//                String formattedResponse = null;
//                URL url = new URL(receivedIntent.getStringExtra(URL));
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setConnectTimeout(5000);
//
//                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    InputStream inputStream = urlConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String response = null;
//                    while ((response = bufferedReader.readLine()) != null) {
//                        stringBuilder = stringBuilder.append(response);
//                    }
//
//                    formattedResponse = stringBuilder.toString();
//
//                    // Broadcast the data using the message provided by the caller of the intent
//                    Intent responseIntent = new Intent();
//                    responseIntent.setAction(receivedIntent.getStringExtra(SUCCESS_BROADCAST));
//                    //responseIntent.putExtra(getResponseDataKey(), formattedResponse);
//                    sendBroadcast(responseIntent);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
