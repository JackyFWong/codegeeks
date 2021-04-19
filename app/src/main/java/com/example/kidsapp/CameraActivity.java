package com.example.kidsapp;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.SeekBar;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.text.SimpleDateFormat;

        import static android.Manifest.permission.CAMERA;

public class CameraActivity extends AppCompatActivity {


    private static final int CAMERA_REQUEST = 1;
    private ImageView imageView;
    private Context mContext;
    private Button save;
    private SharedPreferences.Editor prefEditor;
    private EditText kid_name;
    private SeekBar age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button capture = findViewById(R.id.camera_button);
        save = findViewById(R.id.save_button);
        imageView = findViewById(R.id.iv_image);
        mContext = getApplicationContext();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREFS_FILE, 0);
        prefEditor = pref.edit();


        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

                    // permission granted
                    // continue the action
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);


                } else {
                    // permission not granted
                    // ask for the permission
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[]{CAMERA}, 1);

                }


            }
        });

        save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = null;
                try {
                    f = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                getApplicationContext().sendBroadcast(mediaScanIntent);
            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

                    // permission granted
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);


                } else {
                    // permission denied
                    Toast.makeText(this, "You cannot capture photo without permission", Toast.LENGTH_SHORT).show();
                }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
                ContentValues imageValues = new ContentValues();
                imageValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "ProfilePic");
                imageValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                imageValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

                // Insert a new row into the MediaStore
                ContentResolver resolver = mContext.getContentResolver();
                Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageValues);
                OutputStream outStream = null;
                try {
                    if (uri == null) {
                        throw new IOException("Failed to insert MediaStore row");
                    }
                    // Save the image using the URI
                    outStream=resolver.openOutputStream(uri);
                    photo.compress(Bitmap.CompressFormat.JPEG,100,outStream);

                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public  File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(System.currentTimeMillis());
        File storageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/");
        if (!storageDir.exists())
            storageDir.mkdirs();
        File image = File.createTempFile(
                timeStamp,                   /* prefix */
                ".jpeg",                     /* suffix */
                storageDir                   /* directory */
        );
        return image;
    }

    public void SaveDetails(View view) {
        kid_name=findViewById(R.id.kid_name);
        age=findViewById(R.id.age_bar);

        prefEditor.putString("Name",kid_name.getText().toString());
        prefEditor.putInt("Age",age.getProgress());
        prefEditor.commit();
        finish();


    }

}


