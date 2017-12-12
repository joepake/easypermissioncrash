package com.test.easypermissionscrash;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private final static int RC_CAMERA_AND_STORAGE = 1009;

    @AfterPermissionGranted(RC_CAMERA_AND_STORAGE)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
        } else {
            // Do not have permissions, request them now
            //Crash here
            EasyPermissions.requestPermissions(this, "Request permissions Camera and Storage",
                    RC_CAMERA_AND_STORAGE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("MainActivity", "onCreate");

        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodRequiresTwoPermission();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("MainActivity", "onDestroy");
    }
}
