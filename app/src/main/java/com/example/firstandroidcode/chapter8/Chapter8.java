package com.example.firstandroidcode.chapter8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firstandroidcode.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Target;

public class Chapter8 extends AppCompatActivity implements View.OnClickListener {

    private static final String mTAG = "Chapter8";
    public static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private Uri imageUri;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter8);
        findViewById(R.id.chapter8_btn1).setOnClickListener(this);
        findViewById(R.id.chapter8_btn2).setOnClickListener(this);
        findViewById(R.id.chapter8_btn3).setOnClickListener(this);
        findViewById(R.id.chapter8_btn4).setOnClickListener(this);
        findViewById(R.id.chapter8_btn5).setOnClickListener(this);
        findViewById(R.id.chapter8_btn6).setOnClickListener(this);
        initMediaPlayer();
        picture = findViewById(R.id.chapter8_iv1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Music/","今生今世-张杰.mp3");
            Toast.makeText(this, file.getPath(), Toast.LENGTH_SHORT).show();
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chapter8_btn1:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, 0);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                }
                Notification notification = new NotificationCompat.Builder(this, "1")
                        .setContentTitle("This is huaqiangbei")
                        .setContentText("huaqiangbei")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.apple_icon)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.apple_icon))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.apple_icon)))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setVibrate(new long[] {0, 1000, 1000, 1000})
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                notificationManager.notify(1, notification);
                Log.d(mTAG, "onClick: ");
                break;
            case R.id.chapter8_btn2:
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                if (outputImage.exists()){
                    outputImage.delete();
                }

                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(this, "com.example.firstandroidcode.chapter8", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent1, TAKE_PHOTO);
                break;
            case R.id.chapter8_btn3:
                if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.chapter8_btn4:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.chapter8_btn5:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.chapter8_btn6:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);

                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
                default:
                    break;
        }
    }
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d(mTAG, "handleImageOnKitKat");
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                displayImage(imagePath);
                Log.d(mTAG, "com.android.providers.media.documents");
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
                displayImage(imagePath);
                Log.d(mTAG, "com.android.providers.downloads.documents");
            }
        }

            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
                displayImage(imagePath);
                Log.d(mTAG, "content");
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                displayImage(imagePath);
                Log.d(mTAG, "file");
            }
        }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
            Toast.makeText(this, "success to get image " + imagePath, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "fail to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
