package com.platogo.cordova.emailinbox;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

import android.content.Intent;
import android.util.Log;

public class EmailInboxPlugin extends CordovaPlugin {
    private static final String TAG = "EmailInbox";
    private static final String ACTION_OPEN_EMAIL_APP = "openEmailApp";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (ACTION_OPEN_EMAIL_APP.equals(action)) {
            openEmailApp(callbackContext);
            return true;
        }
        return false; // Returning false results in a "MethodNotFound" error.
    }

    private void openEmailApp(CallbackContext callbackContext) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);

        if (intent.resolveActivity(cordova.getActivity().getPackageManager()) != null) {
            Intent chooser = Intent.createChooser(intent, "Open mail app with…");
            chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cordova.getActivity().startActivity(chooser);
            callbackContext.success();
        } else {
            Log.w(TAG, "No email app available to handle CATEGORY_APP_EMAIL");
            callbackContext.error("NO_EMAIL_APP");
        }
    }
}
