package com.gzeinnumer.class_koding.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.network.ApiServices;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadStruckActivity extends AppCompatActivity {

    public static final String DATA = "data";
    private Uri filePath;

    private String path;

    @BindView(R.id.image_chooser)
    ImageView imageChooser;
    @BindView(R.id.btn_kirim)
    Button btnKirim;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;


    DataMateriItem dataMateriItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_struck);
        ButterKnife.bind(this);

        dataMateriItem = getIntent().getParcelableExtra(DATA);

        requestStoragePermission();

    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @OnClick({R.id.image_chooser, R.id.btn_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_chooser:
                actionImageChooser();
                break;
            case R.id.btn_kirim:
                setImageToAdmin(dataMateriItem);
                break;
        }
    }

    private void actionImageChooser() {
        showFileChooser();
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePath,imageprojection,null,null,null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                path = cursor.getString(indexImage);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageChooser.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //todo 1.7.1
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    //todo 1.7.2
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setImageToAdmin(DataMateriItem data) {
        String param1 = "";
        String param2 = "";
        String param3 = "";
        String param4 = "";

        //todo 1.8
        String path = getPath(filePath);

        File imagefile = new File(path);
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);
        RequestBody temp1 = RequestBody.create(MediaType.parse("text/plain"), param1);
        RequestBody temp2 = RequestBody.create(MediaType.parse("text/plain"), param2);
        RequestBody temp3 = RequestBody.create(MediaType.parse("text/plain"), param3);
        RequestBody temp4 = RequestBody.create(MediaType.parse("text/plain"), param4);

        //$_POST['image']
        String imagePost = "image";
        MultipartBody.Part partImage = MultipartBody.Part.createFormData(imagePost, imagefile.getName(),reqBody);

        ApiServices api = RetroServer.getInstance();


    }

}
