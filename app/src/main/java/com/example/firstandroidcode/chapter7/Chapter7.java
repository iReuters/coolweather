package com.example.firstandroidcode.chapter7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firstandroidcode.R;

import java.util.ArrayList;
import java.util.List;

public class Chapter7 extends AppCompatActivity implements View.OnClickListener {

    private static final String mTAG = "Chaper7";
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private List<Contacts> mContactsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter7);
        Log.d(mTAG, "onCreate: ");
        findViewById(R.id.chapter7_btn1).setOnClickListener(this);

        initRecyclerView();
        requestPermission();


    }

    private void initRecyclerView() {
        adapter = new ContactsAdapter(mContactsList);
        recyclerView = findViewById(R.id.chapter7_rv1);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Chapter7.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {
                    Manifest.permission.READ_CONTACTS
            },1);
        }
        else {
            readContacts();
        }
    }

    private void readContacts() {
        Cursor cursor = null;
        try {

            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (displayName.equals("智行火车票")){
                        continue;
                    }
                    Contacts contacts = new Contacts(displayName, number);
                    mContactsList.add(contacts);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chapter7_btn1:
               if (ContextCompat.checkSelfPermission(Chapter7.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   ActivityCompat.requestPermissions(Chapter7.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
               } else {
                   call();
               }
                break;
        }
    }
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:112"));
            startActivity(intent);
        }catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                }
                else {
                    Toast.makeText(Chapter7.this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
