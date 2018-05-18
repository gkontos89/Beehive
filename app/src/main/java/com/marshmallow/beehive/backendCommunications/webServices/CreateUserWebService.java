package com.marshmallow.beehive.backendCommunications.webServices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.backendCommunications.broadcasts.CreateUserStatusBroadcast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by George on 5/17/2018.
 */
public class CreateUserWebService extends IntentService {

    // Keys
    public static final String emailKey = "email";
    public static final String passwordKey = "password";

    public CreateUserWebService() {
        super("CreateUserWebService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String email = intent.getStringExtra(emailKey);
        String password = intent.getStringExtra(passwordKey);
        try {
            // TODO put URL somewhere else
            URL url = new URL("http://192.168.1.3:8080/api/account");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);

            JSONObject credentials = new JSONObject();
            credentials.put("email", email);
            credentials.put("password", password);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
            outputStreamWriter.write(credentials.toString());
            outputStreamWriter.flush();

            // TODO close connection


            Integer code = urlConnection.getResponseCode();
            String error = urlConnection.getResponseMessage();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String response;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder = stringBuilder.append(response);
                }

                String formattedResponse = stringBuilder.toString();

                // Form the JSON response and validate that it contains resourceId and sessionId fields
                JSONObject jsonResponse = new JSONObject(formattedResponse);
                if (jsonResponse.has("resourceId") && jsonResponse.has("sessionId")) {
                    BeehiveBackend.getInstance().setAccountIds(jsonResponse.getString("resourceId"), jsonResponse.getString("sessionId"));
                    CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast(null, null);
                    Intent createStatusIntent = createUserStatusBroadcast.getSuccessfulBroadcast();
                    sendBroadcast(createStatusIntent);
                } else {
                    CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast("Server error in sending session keys!", null);
                    Intent createStatusIntent = createUserStatusBroadcast.getFailureBroadcast();
                    sendBroadcast(createStatusIntent);
                }
            } else {
                CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast(code.toString() + ": " + error, null);
                Intent createStatusIntent = createUserStatusBroadcast.getFailureBroadcast();
                sendBroadcast(createStatusIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO handle this through the GUI for 'production'
        }
    }
}
