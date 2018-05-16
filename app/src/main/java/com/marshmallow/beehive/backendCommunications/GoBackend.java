package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by George on 5/15/2018.
 */
public class GoBackend implements BeehiveBackendInterface {

    public GoBackend() {

    }

    @Override
    public Boolean isUserSignedIn() {
        return false;
    }

    @Override
    public void createUserWithEmailAndPassword(Context context, Activity activity, String email, String password) {
        new BTask().execute();
    }

    @Override
    public void signInWithEmailAndPassword(Context context, Activity activity, String email, String password) {

    }

    @Override
    public void signOutUser() {

    }

    class BTask extends AsyncTask<Integer, Void, Void> {
        protected Void doInBackground(Integer... params) {
            try {
                // Request for data and format the response to string
                String formattedResponse = null;
                URL url = new URL("http://192.168.1.17:8080/api/account");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("POST");

                JSONObject creds = new JSONObject();
                creds.put("email", "gk89@gmail.com");
                creds.put("password", "foobar");

//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
//                outputStreamWriter.write(creds.toString());

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(creds.toString());
                wr.flush();
                wr.close();

                urlConnection.setConnectTimeout(5000);



                int code = urlConnection.getResponseCode();
                String error = urlConnection.getResponseMessage();
                if (code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String response = null;
                    while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder = stringBuilder.append(response);
                    }

                    formattedResponse = stringBuilder.toString();

                    // Form the JSON response and validate that it contains resourceId and sessionId fields
                    JSONObject jsonResponse = new JSONObject(formattedResponse);
                    int j = 0;
                    j++;
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
