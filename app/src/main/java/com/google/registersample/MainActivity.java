package com.google.registersample;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RoundedImageView avatar;
    TextView choosePic;
    EditText user, pass;
    AppCompatCheckBox remember;
    Button signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();


        signIn_btn.setOnClickListener(this);
        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog
                getPermissions();
                showAlertDialog(v);
            }
        });


    }


    private void bind() {
        avatar = findViewById(R.id.avatar);
        choosePic = findViewById(R.id.choosePic);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        remember = findViewById(R.id.remember);
        signIn_btn = findViewById(R.id.signIn_btn);
    }


    @Override
    public void onClick(View v) {
        if (user.length() == 0) {
            user.setError("Enter username");

        }
        if (pass.length() == 0) {
            user.setError("Enter password");

        } else if (pass.length() != 0 & user.length() != 0)

            startAnimation();
    }


    private void startAnimation() {

    }


    public void showAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setSingleChoiceItems(new String[]{"Open Gallery", "Open Camera", "Remove current image"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            EasyImage.openGallery(MainActivity.this, 0);
                            dialog.dismiss();
                        }
                        if (which == 1) {
                            EasyImage.openCamera(MainActivity.this, 0);
                            dialog.dismiss();
                        }

                        if (which == 2) {
                        //    avatar.setVisibility(View.INVISIBLE);
                            avatar.setImageResource(android.R.color.transparent);
                            dialog.dismiss();
                        }
                    }
                });
        builder.show();
    }

    void getPermissions() {

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                Glide.with(MainActivity.this).load(imageFile).into(avatar);
            }
        });
    }


}
