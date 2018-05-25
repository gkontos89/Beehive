package com.marshmallow.beehive.backendCommunications.webServices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.backendCommunications.broadcasts.CreateUserStatusBroadcast;
import com.marshmallow.beehive.models.ModelManager;

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
            outputStreamWriter.close();

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
                JSONObject jsonResponse = new JSONObject(formattedResponse);
                if (jsonResponse.has("resourceId") && jsonResponse.has("sessionId")) {
                    // Store the sessionId for this connection
                    BeehiveBackend.getInstance().setSessionId(jsonResponse.getString("sessionId"));

                    // Store the resourceId for the account that just got created
                    ModelManager.getInstance().setAccountId(jsonResponse.getString("resourceId"));

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

            urlConnection.disconnect();
        } catch (Exception e) {
            String message = e.getMessage();
            CreateUserStatusBroadcast createUserStatusBroadcast = new CreateUserStatusBroadcast(message, null);
            Intent createStatusIntent = createUserStatusBroadcast.getFailureBroadcast();
            sendBroadcast(createStatusIntent);
        }
    }
}
