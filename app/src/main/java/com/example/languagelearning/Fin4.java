package com.example.languagelearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.languagelearning.Quiz1;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Fin4 extends AppCompatActivity {
    int score=Quiz4.getScore();
    String s=String.valueOf(score);
    private TextView Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin);
        Result= findViewById(R.id.textView7);
        // creating a new array list.
        Result.setText(s);
        //getDatafromAPI();

        //calling a method to load our API.

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


}

