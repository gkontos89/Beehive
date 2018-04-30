package com.marshmallow.beehive.backendCommunications;

/**
 * BeehiveBackend is the front facing API's that are used throughout the application.
 * It implements the BeehiveBackendInterface so that all different backend models can be dropped in
 * and instantiated as the new backend model
 *
 * Created by George on 4/29/2018.
 */
public class BeehiveBackend implements BeehiveBackendInterface {
    private static BeehiveBackend instance = null;
    private Object backendHandle;

    private BeehiveBackend() {
        backendHandle = new FirebaseBackend();
    }

    public static BeehiveBackend getInstance() {
        if (instance == null) {
            instance = new BeehiveBackend();
        }

        return instance;
    }
}
