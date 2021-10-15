package com.example.bai3;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class DownloadService extends Service {
    public static String ACTION_DOWNLOAD = "ACTION_DOWNLOAD";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("url");
        startDownloadFile(url);
        return START_STICKY;
    }

    private void startDownloadFile(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Download");
        request.setDescription("Download file .......");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, String.valueOf(System.currentTimeMillis()));
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
            Intent intent = new Intent(ACTION_DOWNLOAD);
            getApplicationContext().sendBroadcast(intent);
        }
    }
}
