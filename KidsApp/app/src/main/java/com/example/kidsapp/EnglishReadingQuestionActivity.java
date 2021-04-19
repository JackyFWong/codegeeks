package com.example.kidsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class EnglishReadingQuestionActivity extends AppCompatActivity {
    private final int Reqcode=100;
    TextView txtDictated;
    private Button answer_button;
    String answer="Joe Biden";
    private String guess;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_reading_question);

        txtDictated=findViewById(R.id.txtDictated);
        answer_button=findViewById(R.id.ans_submit);

        builder = new AlertDialog.Builder(this);

        answer_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (guess.equals(answer)) {
                    builder.setTitle("Correct Answer");
                    builder.setMessage("You are Awesome")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if(guess!=answer){
                    builder.setTitle("Wrong Answer");
                    builder.setMessage("Keep Trying")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onResume();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Reqcode: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtDictated.setText(result.get(0));
                    guess=result.get(0);

                }
                break;
            }
        }
    }

    public void taptoDictate(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Need to Speak");

        try{
            startActivityForResult(intent,Reqcode);
        }
        catch(ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Device Not Supported",
                    Toast.LENGTH_LONG).show();

        }

    }




}
