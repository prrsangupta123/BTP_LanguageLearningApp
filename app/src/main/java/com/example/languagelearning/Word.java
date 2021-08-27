package com.example.languagelearning;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class Word extends AppCompatActivity {

    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view
    private ProgressBar loadingPB;
    private TextView FirstName;
    private TextView LastName;
    private TextView Email;
    private ImageView Avatar;

    int counter=0;
    ArrayList<WordClass> queList = new ArrayList<>();
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        // creating a new array list.
        FirstName = findViewById(R.id.firstName);
        LastName = findViewById(R.id.secondTextView);
        Email = findViewById(R.id.thirdTextView);
        Avatar = findViewById(R.id.imageView);
        button3 = findViewById(R.id.button3);
        // calling a method to load our API.
        getDataFromAPI();
        /*button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Word2.class));

            }
        });*/

    }

    private void getDataFromAPI() {

        // creating a string variable for URL.
        String url= "https://script.googleusercontent.com/macros/echo?user_content_key=hrg9YszZUBLq3N4RQT-W9P_PqKORZA7KovMZAKJ_sWxM2ARq6IvzdSD2xf0ZiHAUMqnMvMVUSjxgxWVgDVgNZzJXF5-2RDLWOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUJJbN7MxLSqfIfKRzvx-FkDL_yPQqz0pBf0CqjwXfvGYxBTTVu962N2uHADTQdpyr5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
        //String url = "https://script.googleusercontent.com/macros/echo?user_content_key=fDpMbUTAW54p0Mv9aQCoJJ9jxTImVBRP-hsHAbphRDZPGj2_o0Vu1A69gnTBRIWafaWEomSV03UOnDwnqj4XpB4gMMhoFKc2OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUD5nZx6M7Ox4TxU6o7HZsyFSV-aY2SDShqUixyuDd6otmXw9ULCYJKtvxYwb91JSp5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
        // String url = "https://docs.google.com/spreadsheets/d/1rw49V9V6cVgnCEwao6SOtFj_cmC-niTO9hSu6VL_S3k/edit?usp=drivesdk";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Word.this);

        // creating a variable for our JSON object request and passing our URL to it.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    //JSONObject feedObj = response.getJSONObject("feed");
                    JSONArray entryArray = response.getJSONArray("Sheet1");
                    int i=0;
                    for( i = 0; i < 10; i++) {
                        //int i=10;
                        JSONObject entryObj = entryArray.getJSONObject(i);
                        String firstName=entryObj.getString("first_name");
                        String lastName=entryObj.getString("last_name");
                        String email = entryObj.getString("email");
                        String avatar = entryObj.getString("avatar");

                        queList.add(new WordClass(firstName, lastName, email, avatar));
                    /*Picasso.get().load(avatar).into(Avatar);

                    FirstName.setText(firstName);
                    LastName.setText(lastName);
                    Email.setText(email);*/

                        //queList.add(new QuestionClass(firstName, email,lastName,avatar,av, email));


                    }
                    //init conter
                    loadWords(counter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*for(int i=0; i<10; i++) {
                        //Random rand = new Random();
                        //int i=rand.nextInt(10);
                        JSONObject entryObj = entryArray.getJSONObject(i);
                        //String firstName = entryObj.getJSONObject("gsx$firstname").getString("$t");
                        //String lastName = entryObj.getJSONObject("gsx$lastname").getString("$t");
                        String email = entryObj.getJSONObject("gsx$email").getString("$t");
                        //String avatar = entryObj.getJSONObject("gsx$avatar").getString("$t");
                        //String av = entryObj.getJSONObject("gsx$av").getString("$t");
                        //queList.add(new QuestionClass(firstName, email,lastName,avatar,av, email));
                        ansList.add(email);
                    /*int j=rand.nextInt(10);
                    JSONObject entryObj1 = entryArray.getJSONObject(j);
                    String firstName1 = entryObj.getJSONObject("gsx$firstname").getString("$t");
                    String lastName1 = entryObj.getJSONObject("gsx$lastname").getString("$t");
                    String email1 = entryObj.getJSONObject("gsx$email").getString("$t");
                    String avatar1 = entryObj.getJSONObject("gsx$avatar").getString("$t");
                    queList.add(new QuestionClass(firstName1, lastName1, email1,
                            avatar1, "ans 4", lastName1));
                    int k=rand.nextInt(10);
                    JSONObject entryObj11 = entryArray.getJSONObject(k);
                    String firstName11 = entryObj.getJSONObject("gsx$firstname").getString("$t");
                    String lastName11 = entryObj.getJSONObject("gsx$lastname").getString("$t");
                    String email11 = entryObj.getJSONObject("gsx$email").getString("$t");
                    String avatar11 = entryObj.getJSONObject("gsx$avatar").getString("$t");
                    queList.add(new QuestionClass(firstName11, lastName11, email11,
                            avatar11, "ans 4", "ans 3"));
                    }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handline on error listner method.
                Toast.makeText(Word.this, "Fail to get data..", Toast.LENGTH_LONG).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
    }


    public void loadWords(int n) {

            final WordClass q = queList.get(n);
            String firstName=q.getFirstName();
            String lastName=q.getLastName();
            String email=q.getEmail();
            String avatar=q.getAvatar();

        Picasso.get().load(avatar).into(Avatar);
        FirstName.setText(firstName);
        LastName.setText(lastName);
        Email.setText(email);
        button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (counter < queList.size() - 1) {
                        counter++;
                        loadWords(counter);
                    }
                    //startActivity(new Intent(getApplicationContext(),Word2.class));

                    else {
                        startActivity(new Intent(getApplicationContext(),Quiz1.class));
                    }
                }
            });




    }

}


