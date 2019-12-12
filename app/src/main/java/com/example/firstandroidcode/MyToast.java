package com.example.firstandroidcode;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import android.widget.Toolbar;

public class MyToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToast(Context context,String content) {
        super(context);
    }


   static public void makeShortText(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    static public void makeText1(String content) {
        Toast.makeText(MyApplication.getContext(), content, Toast.LENGTH_SHORT).show();
    }

}
