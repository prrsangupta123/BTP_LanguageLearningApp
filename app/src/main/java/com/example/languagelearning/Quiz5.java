package com.example.languagelearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.languagelearning.R;
import com.example.languagelearning.QuestionClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.*;
public class Quiz5 extends AppCompatActivity {

    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view

    TextView tvQueConter, tvTimer, tvQue;

    Button btOpt1, btOpt2, btOpt3, btOpt4;

    //list for que
    ArrayList<QuestionClass> queList = new ArrayList<>();
    ArrayList<String> ansList=new ArrayList<>();

    int conter = 0;
    static int score= 0;

    CountDownTimer tm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);

        // creating a new array list.

        // initializing our views.
        tvQue = findViewById(R.id.tvQue);
        tvQueConter = findViewById(R.id.tvQueConter);
        tvTimer = findViewById(R.id.tvTimer);

        btOpt1 = findViewById(R.id.btOpt1);
        btOpt2 = findViewById(R.id.btOpt2);
        btOpt3 = findViewById(R.id.btOpt3);
        btOpt4 = findViewById(R.id.btOpt4);

        // calling a method to load our API.
        getDataFromAPI();
        // calling a method to load our API.

    }

    private void getDataFromAPI() {

        // creating a string variable for URL.
        String url ="https://script.googleusercontent.com/macros/echo?user_content_key=fpy11_xzeXMLI_IY5EHuLv_hRVs3xcivSaalZuPMyeGwfpHLoo8Usdxch5tlm6WIPIdu2h6Ukg6GEd4xNjpXBb93As6HAcIaOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUJJbN7MxLSqfIfKRzvx-FkDL_yPQqz0pBf0CqjwXfvGYxBTTVu962N2uHADTQdpyr5y7FLqOV0TmOdRNbgKvOpg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
        //String url = "https://spreadsheets.google.com/feeds/list/1Wa6NTIaN3i81uDOKa7bf_u6lDVWReDEto41FPSOVhuI/od6/public/values?alt=json";
        // String url = "https://docs.google.com/spreadsheets/d/1rw49V9V6cVgnCEwao6SOtFj_cmC-niTO9hSu6VL_S3k/edit?usp=drivesdk";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Quiz5.this);

        // creating a variable for our JSON object request and passing our URL to it.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //JSONObject feedObj = response.getJSONObject("feed");
                    JSONArray entryArray = response.getJSONArray("Sheet5");

                    for(int i=0;i<20;i++){
                        Random rand=new Random();
                        int j=rand.nextInt(7);
                        int a=rand.nextInt(19);
                        while(a==i){
                            a=rand.nextInt(19);
                        }
                        int b=rand.nextInt(19);
                        while(b==i || a==b){
                            b=rand.nextInt(19);
                        }
                        int c=rand.nextInt(19);
                        while(c==i || c==b || c==a){
                            c=rand.nextInt(19);
                        }
                        JSONObject entryObj = entryArray.getJSONObject(i);
                        JSONObject A=entryArray.getJSONObject(a);
                        JSONObject B=entryArray.getJSONObject(b);
                        JSONObject C=entryArray.getJSONObject(c);

                        String firstName=entryObj.getString("email");
                        String email=entryObj.getString("first_name");
                        String lastName = A.getString("first_name");
                        String avatar = B.getString("first_name");
                        String av =C.getString("first_name");

                        if(j==0){
                            queList.add(new QuestionClass(firstName, av,email,lastName,avatar,email));
                        }
                        else if(j==1){
                            queList.add(new QuestionClass(firstName, email,lastName,avatar,av,email));
                        }
                        else if(j==2){
                            queList.add(new QuestionClass(firstName, lastName,email,avatar,av,email));

                        }
                        else if(j==3){
                            queList.add(new QuestionClass(firstName,lastName,avatar,email,av,email));

                        }
                        else if(j==4){
                            queList.add(new QuestionClass(firstName,lastName,avatar,av,email,email));
                        }
                        else if(j==5){
                            queList.add(new QuestionClass(firstName, email,avatar,lastName,av,email));
                        }
                        else if(j==6){
                            queList.add(new QuestionClass(firstName, email,avatar,av,lastName,email));

                        }
                        else if(j==7){
                            queList.add(new QuestionClass(firstName, email,lastName,av,avatar,email));

                        }
                        //queList.add(new QuestionClass(firstName, email,lastName,avatar,av, email));


                    }
                    //init conter
                    conter = 0;
                    loadQuetions(conter);

                    tvTimer.setText("12");
                    //countdown time
                    tm = new CountDownTimer(12 * 1000, 1000) {


                        @Override
                        public void onTick(long millisUntilFinished) {
                            tvTimer.setText("" + millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(Quiz5.this, "Quiz Over", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Fin.class));
                        }
                    };


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handline on error listner method.
                Toast.makeText(Quiz5.this, "Fail to get data..", Toast.LENGTH_LONG).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
    }
    public void loadQuetions(int n) {

        final QuestionClass q = queList.get(n);

        tvQueConter.setText((n + 1) + "/" + queList.size());

        tvTimer.setText("" + 12);
        if (tm != null) {
            tm.start();
        }

        tvQue.setText("#" + (n + 1) + "Choose Right word for" +" "+ q.getQue());
        btOpt1.setText("" + q.getOpt1());
        btOpt2.setText("" + q.getOpt2());
        btOpt3.setText("" + q.getOpt3());
        btOpt4.setText("" + q.getOpt4());

        btOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btOpt1.getText().equals(q.getRightOpt())) {
                    Toast.makeText(Quiz5.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        score++;
                        loadQuetions(conter);
                    } else {
                        Toast.makeText(Quiz5.this, "All Que Completed!", Toast.LENGTH_LONG).show();
                        String s=String.valueOf(score);
                        Toast.makeText(Quiz5.this,s, Toast.LENGTH_LONG).show();

                    }


                } else {
                    Toast.makeText(Quiz5.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        loadQuetions(conter);
                    }
                }


            }
        });

        //btn2
        btOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btOpt2.getText().equals(q.getRightOpt())) {

                    Toast.makeText(Quiz5.this, "Correct Answer", Toast.LENGTH_LONG).show();

                    if (conter < (queList.size() - 1)) {
                        // tm.cancel();
                        conter++;
                        score++;
                        loadQuetions(conter);
                    } else {
                        Toast.makeText(Quiz5.this, "All Que Completed!", Toast.LENGTH_LONG).show();
                        String s=String.valueOf(score);
                        Toast.makeText(Quiz5.this,s, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Quiz5.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        loadQuetions(conter);
                    }
                }

            }
        });

        //btn 3
        btOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btOpt3.getText().equals(q.getRightOpt())) {

                    Toast.makeText(Quiz5.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        score++;
                        loadQuetions(conter);
                    } else {
                        Toast.makeText(Quiz5.this, "All Que Completed!", Toast.LENGTH_LONG).show();
                        String s=String.valueOf(score);
                        Toast.makeText(Quiz5.this,s, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Quiz5.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        loadQuetions(conter);
                    }
                }

            }
        });

        //btn4
        btOpt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btOpt4.getText().equals(q.getRightOpt())) {

                    Toast.makeText(Quiz5.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        loadQuetions(conter);
                    } else {
                        Toast.makeText(Quiz5.this, "All Que Completed!", Toast.LENGTH_LONG).show();
                        String s=String.valueOf(score);
                        Toast.makeText(Quiz5.this,s, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Quiz5.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    if (conter < (queList.size() - 1)) {
                        //tm.cancel();
                        conter++;
                        loadQuetions(conter);
                    }
                }

            }
        });

    }
    public static Integer getScore(){
        return score;
    }
}





