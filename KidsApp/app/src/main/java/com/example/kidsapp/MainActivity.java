package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int IMAGE_PICKER =1 ;
    private Context mContext;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Button btn_imageSetter,insta_btn;
    private ImageView iv_image;
    private TextView name;

    String type = "text/*";
    // String filename ="";
    //String mediaPath = Environment.getExternalStorageDirectory() + filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_imageSetter=findViewById(R.id.btn_imageSetter);
        iv_image= findViewById(R.id.iv_image);
        insta_btn=findViewById(R.id.insta_btn);
        name= findViewById(R.id.name);
        mContext=getApplicationContext();

        pref = getApplicationContext().getSharedPreferences(Constants.PREFS_FILE, 0);
        editor = pref.edit();

        name.setText("Hi,"+pref.getString("Name", "user"));


        btn_imageSetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICKER);

            }
        });

        insta_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);

                // Set the MIME type
                share.setType(type);
                share.putExtra(Intent.EXTRA_TEXT, "Hey I'm using this cool app!");

                // Create the URI from the media
                /* File media = new File(filename);
                Uri uri = Uri.fromFile(media);

                // Add the URI to the Intent.
                share.putExtra(Intent.EXTRA_STREAM, uri);
                 */
                share.setPackage("com.instagram.android");

                // Broadcast the Intent.
                startActivity(Intent.createChooser(share, "Share to"));
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICKER && resultCode == RESULT_OK && null != data) {
            try {
                final Uri uriImage = data.getData();
                final InputStream inputStream = getContentResolver().openInputStream(uriImage);
                final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                iv_image.setImageBitmap(imageMap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "Image was not found", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void onEnglishReadingClick(View view) {
        Intent intent = new Intent(this, EnglishReadingQuestionActivity.class);
        startActivity(intent);
    }

    public void onEnglishSoundClick(View view) {
        Intent intent = new Intent(this, EnglishSoundQuestionActivity.class);
        startActivity(intent);
    }

    public void onMathClick(View view) {
        Intent intent = new Intent(this, MathQuestionActivity.class);
        startActivity(intent);
    }

    public void onScienceClick(View view) {
        Intent intent = new Intent(this, ScienceQuestionActivity.class);
        startActivity(intent);
    }

    public void onStoryClick(View view) {
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }

    public void onVideoClick(View view) {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_1:
                Intent intent=new Intent(this,CameraActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}