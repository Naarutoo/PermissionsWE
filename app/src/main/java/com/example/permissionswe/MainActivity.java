package com.example.permissionswe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUESTCODE = 0;
    private Button mbtnRequestPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnRequestPermission = findViewById(R.id.btnRequestPermission);

        mbtnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUESTCODE);
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            showToast("Both the permissions Granted");
        } else if (grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[1])){
                showToast("Camera Granted but Storage Denied");
            }
            else{
                showToast("camera granted but storage denied by  selecting do not show again");
            }
        }
        else if (grantResults[0]==PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            showToast("Camera Denied but Permission Granted");
        }
        else if (grantResults[0]==PackageManager.PERMISSION_DENIED && grantResults[1]==PackageManager.PERMISSION_DENIED){
            String[] permissions1 = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUESTCODE);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}