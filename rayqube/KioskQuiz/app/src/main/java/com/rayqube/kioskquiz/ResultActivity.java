package com.rayqube.kioskquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;





public class ResultActivity extends AppCompatActivity {
    String name,mailId;
    int score;
    String url = "http://rayqube.com/projects/kiosk_quiz/rest/save_responce";
    DbHelper helper;
    SimpleDateFormat simpleDateFormat;
    RequestQueue queue;
    ProgressBar progressBar;
    String format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView nameTv = findViewById(R.id.name);
        TextView emailTv = findViewById(R.id.email);
        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        Logger.addLogAdapter(new AndroidLogAdapter());
        progressBar = findViewById(R.id.progressBar);
//        ImageView img = (ImageView)findViewById(R.id.img1);
        queue = Volley.newRequestQueue(ResultActivity.this);
        Button submit = findViewById(R.id.submit_button);
        simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy-hh-mm-ss");
        format = simpleDateFormat.format(new Date());
        helper = new DbHelper(this);
        Bundle b = getIntent().getExtras();
        if (b!=null) {
            score = b.getInt("score");
            name = b.getString("EMAIL_ID");
            mailId = b.getString("NAME");
            nameTv.setText(name);
            emailTv.setText(mailId + " for more information");
            ratingBar.setRating(score);
            scoreTxtView.setText(String.valueOf(score));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (name!=null && mailId!=null )
                {

                    People people = new People();
                    people.setName(name);
                    people.setMailId(mailId);
                    people.setScore(String.valueOf(score));
                    people.setTime(format);
                    Logger.i("Time->" + format);
                    helper.addScoreDetail(people);
                }
                sendResponse();

            }
        });
    }
    private void restartQuiz(){

        Intent welcomeIntent = new Intent(ResultActivity.this,WelcomeActivity.class);
        startActivity(welcomeIntent);
    }

    private void sendResponse(){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        progressBar.setVisibility(View.GONE);
                        restartQuiz();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        progressBar.setVisibility(View.GONE);
                        restartQuiz();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", mailId);
                params.put("score", String.valueOf(score));
                params.put("submitted_on", format);
                return params;
            }
        };
        queue.add(postRequest);
    }
}




//        if(score == 0){
//            img.setImageResource(R.drawable.score_0);
//        }else if(score == 1){
//            img.setImageResource(R.drawable.score_1);
//        }else if(score == 2){
//            img.setImageResource(R.drawable.score_2);
//        }else if(score == 3){
//            img.setImageResource(R.drawable.score_3);
//        }else if(score == 4){
//            img.setImageResource(R.drawable.score_4);
//        }else if(score == 5){
//            img.setImageResource(R.drawable.score_5);
//        }