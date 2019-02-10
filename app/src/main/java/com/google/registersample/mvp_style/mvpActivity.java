package com.google.registersample.mvp_style;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.registersample.MainActivity;
import com.google.registersample.R;
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

public class mvpActivity extends AppCompatActivity implements Contract.View {

    Presenter presenter = new Presenter();

    RoundedImageView avatar;
    TextView choosePic, result;
    EditText userV, passV;
    AppCompatCheckBox remember;
    Button signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        presenter.attachView(this);
        bind();

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            userRegistered();

            }
        });

        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog
                getPermissions();
                showAlertDialog(v);
            }
        });

    }


    public void showAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setSingleChoiceItems(new String[]{"Open Gallery", "Open Camera", "Remove current image"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            EasyImage.openGallery(mvpActivity.this, 0);
                            dialog.dismiss();
                        }
                        if (which == 1) {
                            EasyImage.openCamera(mvpActivity.this, 0);
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

    private void bind() {
        avatar = findViewById(R.id.avatar);
        choosePic = findViewById(R.id.choosePic);
        userV = findViewById(R.id.user);
        passV = findViewById(R.id.pass);
        remember = findViewById(R.id.remember);
        result = findViewById(R.id.result);
        signIn_btn = findViewById(R.id.signIn_btn);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                Glide.with(mvpActivity.this).load(imageFile).into(avatar);
            }
        });
    }


    @Override
    public void userRegistered() {
        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUserLoaded(String user) {
        Toast.makeText(this, "user is "+user, Toast.LENGTH_SHORT).show();
    }
}
