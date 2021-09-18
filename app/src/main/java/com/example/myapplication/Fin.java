package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Quiz1;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Fin extends AppCompatActivity {
    int score=Quiz1.getScore();
    String s=String.valueOf(score);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin);
        TextView result = findViewById(R.id.textView7);
        // creating a new array list.
        result.setText(s);
        //getDatafromAPI();

        //calling a method to load our API.

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


}
