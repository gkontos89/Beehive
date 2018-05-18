package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        WebClient wc = new WebClient();
        wc.setter("hey");
        Intent i = new Intent(context, WebClient.class);
        context.startService(i);

    }

    @Override
    public void signInWithEmailAndPassword(Context context, Activity activity, String email, String password) {

    }

    @Override
    public void signOutUser() {

    }

    public class m {
        public m() {

        }

        public void hey() {
            return;
        }
    }

    public class WebClient extends IntentService {

        public static final String REQUEST_METHOD = "requestMethod";
        public static final String URL = "url";
        public static final String FAILURE_BROADCAST = "failureBroadcast";
        public static final String SUCCESS_BROADCAST = "successBroadcast";
        public static final String DATA = "data";

        private Intent receivedIntent;
        private String s;


        public WebClient() {
            super("WebClient");
        }

        public void setter(String s) {
            this.s = s;
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            receivedIntent = intent;
            if (receivedIntent.getStringExtra(REQUEST_METHOD) != null) {
                switch(receivedIntent.getStringExtra(REQUEST_METHOD)) {
                    case "POST":
                        isUserSignedIn();
                        try{

                        } catch (Exception e) {

                        }
                        //handlePostAction();
                        break;
                    case "GET":
                        handleGetAction();
                        break;
                }
            }

        }

        private void handleGetAction() {
            try {
                // Request for data and format the response to string
                String formattedResponse = null;
                URL url = new URL(receivedIntent.getStringExtra(URL));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String response = null;
                    while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder = stringBuilder.append(response);
                    }

                    formattedResponse = stringBuilder.toString();

                    // Broadcast the data using the message provided by the caller of the intent
                    Intent responseIntent = new Intent();
                    responseIntent.setAction(receivedIntent.getStringExtra(SUCCESS_BROADCAST));
                    //responseIntent.putExtra(getResponseDataKey(), formattedResponse);
                    sendBroadcast(responseIntent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }















    class BTask extends AsyncTask<Void, Void, Void> {
        private Context context;

        public BTask(Context context) {
            this.context = context;
        }

        protected Void doInBackground(Void... params) {
            try {
                // Request for data and format the response to string
                String formattedResponse = null;
                URL url = new URL("http://192.168.1.17:8080/api/account");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");

                JSONObject creds = new JSONObject();
                creds.put("email", "gk189@gmail.com");
                creds.put("password", "foobar");

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                outputStreamWriter.write(creds.toString());
                outputStreamWriter.flush();

                // TODO close connection

                //OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());


//                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
//                wr.writeBytes(creds.toString());
//                wr.flush();
//                wr.close();

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

                    //context.sendBroadcast(new Intent(());

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
