package com.example.firstandroidcode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String mTAG = "BootCompleteReceiver";
        Log.d(mTAG, "onReceive: ");
        Toast.makeText(context,"BOOT COMPLETED!",Toast.LENGTH_LONG).show();

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
