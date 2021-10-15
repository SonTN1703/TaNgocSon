package com.example.bai3;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 1;
    private Button btnConfirm;
    private EditText etUrl;
    private boolean isDownload;
    private DownloadBroadcast downloadBroadcast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnConfirm.setOnClickListener(v -> {
            checkPermission();
            if (isDownload) {
                String url = etUrl.getText().toString().trim();
                Intent intent = new Intent(this, DownloadService.class);
                intent.putExtra("url", url);
                startService(intent);
            }
        });
    }

    private void initView() {
        btnConfirm = findViewById(R.id.btn_download);
        etUrl = findViewById(R.id.et_url);
        etUrl.setText("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, REQUEST_PERMISSION_CODE);
            } else {
                isDownload = true;
            }
        } else {
            isDownload = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadBroadcast);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isDownload = true;
            } else {
                Toast.makeText(this, "Không được cấp quyền", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registerBroadcast() {
        downloadBroadcast = new DownloadBroadcast();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadBroadcast, intentFilter);
    }
}