package com.marshmallow.beehive.backendCommunications.webServices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.marshmallow.beehive.backendCommunications.backends.BeehiveBackend;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;

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
public class UpdateProfileWebService extends IntentService {


    public UpdateProfileWebService() {
        super("UpdateProfileWebService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            URL url = new URL("http://192.168.1.3:8080/api/users");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);

            // Setup header
            // TODO if this header will not change, wrap it in a backend call
            // TODO fix the authentication issue
            urlConnection.setRequestProperty("beehive", BeehiveBackend.getInstance().getSessionId());

            // Setup body
            // TODO simply run user.mapModelToJson call
            UserModel userModel = ModelManager.getInstance().getUserModel();
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("profileName", userModel.getUserName());
            jsonBody.put("accountId", ModelManager.getInstance().getAccountId());

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
            outputStreamWriter.write(jsonBody.toString());
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
                Log.d("Logger", "Got it");
                // TODO handle sucesses, failures, ect.
                // TODO match returned data with current data
                // TODO fill in resourceId to User
            }

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO handle this throught the GUI for 'production'
        }
    }
}
