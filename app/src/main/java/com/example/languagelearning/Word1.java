package com.example.languagelearning;
import android.content.Intent;
import android.os.Bundle;
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
public class Word1 extends AppCompatActivity {

    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view
    private ProgressBar loadingPB;
    private TextView FirstName;
    private TextView LastName;
    private TextView Email;
    private ImageView Avatar;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word1);

        // creating a new array list.
        FirstName=findViewById(R.id.firstName);
        LastName=findViewById(R.id.secondTextView);
        Email=findViewById(R.id.thirdTextView);
        Avatar=findViewById(R.id.imageView);
        button3 = findViewById(R.id.button3);
        getDataFromAPI();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Word2.class));

            }
        });


        // calling a method to load our API.

    }

    private void getDataFromAPI() {

        // creating a string variable for URL.
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=Z9mNhwoBEcWp6Vh5hObmFMYjD79BxuK51Ojlql-DKQ4mbNkn59QUTZUk21-8QJ86G80kWmItUJvKeGx9T3CvhNy6DAxhGWtjOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUD5nZx6M7Ox4TxU6o7HZsyFSV-aY2SDShqUixyuDd6otmXw9ULCYJKtvxYwb91JSp5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
       // String url = "https://spreadsheets.google.com/feeds/list/1sLfeViM58v2qakNlzd4ACR8-usvuVGzRwm_AN3BBPnM/od6/public/values?alt=json";
       // String url = "https://docs.google.com/spreadsheets/d/1rw49V9V6cVgnCEwao6SOtFj_cmC-niTO9hSu6VL_S3k/edit?usp=drivesdk";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Word1.this);

        // creating a variable for our JSON object request and passing our URL to it.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //loadingPB.setVisibility(View.GONE);
                try {
                    JSONObject feedObj = response.getJSONObject("feed");
                    JSONArray entryArray = feedObj.getJSONArray("entry");
                    /*for(int i=0; i<entryArray.length(); i++){
                        JSONObject entryObj = entryArray.getJSONObject(i);
                        String firstName = entryObj.getJSONObject("gsx$firstname").getString("$t");
                        String lastName = entryObj.getJSONObject("gsx$lastname").getString("$t");
                        String email = entryObj.getJSONObject("gsx$email").getString("$t");
                        String avatar = entryObj.getJSONObject("gsx$avatar").getString("$t");
                        userModalArrayList.add(new UserModal(firstName, lastName, email, avatar));

                        // passing array list to our adapter class.
                        userRVAdapter = new UserRVAdapter(userModalArrayList, MainActivity.this);

                        // setting layout manager to our recycler view.
                        userRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        // setting adapter to our recycler view.
                        userRV.setAdapter(userRVAdapter);
                    }*/
                    //Random rand = new Random();
                    int i=0;//rand.nextInt(21);
                    JSONObject entryObj = entryArray.getJSONObject(i);
                    String firstName = entryObj.getJSONObject("gsx$firstname").getString("$t");
                    String lastName = entryObj.getJSONObject("gsx$lastname").getString("$t");
                    String email = entryObj.getJSONObject("gsx$email").getString("$t");
                    String avatar = entryObj.getJSONObject("gsx$avatar").getString("$t");
                    //Avatar.setImageBitmap(getBitmapFromURL(avatar));
                    //Avatar.setImageBitmap(DownloadImageTask.doInBackground(avatar));
                    Picasso.get().load(avatar).into(Avatar);

                    FirstName.setText(firstName);
                    LastName.setText(lastName);
                    Email.setText(email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handline on error listner method.
                Toast.makeText(Word1.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
    }
}

