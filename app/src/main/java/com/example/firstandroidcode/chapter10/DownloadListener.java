package com.example.firstandroidcode.chapter10;

public interface DownloadListener {
    void onProgress(int Progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
