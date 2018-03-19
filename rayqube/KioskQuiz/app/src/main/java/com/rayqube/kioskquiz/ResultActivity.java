package com.rayqube.kioskquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.List;
import java.util.Map;





public class ResultActivity extends AppCompatActivity {
    String mailId;
    SharedPreferences sharedpreferences;
    int score;
    String url = "http://rayqube.com/projects/kiosk_quiz/rest/save_responce";
    DbHelper helper;
    SimpleDateFormat simpleDateFormat;
    TextView question1,question2,question3,question4,question5,answer1,answer2,answer3,answer4,answer5;
    RequestQueue queue;
    ProgressBar progressBar;

    String format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        Logger.addLogAdapter(new AndroidLogAdapter());
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        sharedpreferences = ResultActivity.this.getSharedPreferences("location", Context.MODE_PRIVATE);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        answer1   = findViewById(R.id.answer1);
        answer2   = findViewById(R.id.answer2);
        answer3   = findViewById(R.id.answer3);
        answer4   = findViewById(R.id.answer4);
        answer5   = findViewById(R.id.answer5);
        progressBar = findViewById(R.id.progressBar);
        helper = new DbHelper(this);
        setQuestionAndAnswers();
//        ImageView img = (ImageView)findViewById(R.id.img1);
        queue = Volley.newRequestQueue(ResultActivity.this);
        Button submit = findViewById(R.id.submit_button);
        simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy-hh-mm-ss");
        format = simpleDateFormat.format(new Date());

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            score = b.getInt("score");
            mailId = b.getString("EMAIL_ID");

            ratingBar.setRating(score);
            scoreTxtView.setText(String.valueOf(score));
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (mailId!=null )
                {
                    People people = new People();
                    people.setMailId(mailId);
                    people.setScore(String.valueOf(score));
                    people.setTime(format);
                    people.setName(mailId);
                    Logger.i("Time->" + format);
                    helper.addScoreDetail(people);
                }
                sendResponse();

            }
        });
    }
    private void restartQuiz(){

        Intent welcomeIntent = new Intent(ResultActivity.this,ThankYouActivity.class);
        startActivity(welcomeIntent);
        finish();
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
                String location = sharedpreferences.getString("location_key",null);
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name",location);
                params.put("email", mailId);
                params.put("score", String.valueOf(score));
                params.put("submitted_on", format);
                return params;
            }
        };
        queue.add(postRequest);
    }
    private void setQuestionAndAnswers(){

        List<Question> questions = helper.getAllQuestions();

        int i;
        for ( i=0;i<questions.size();i++){

//            Log.i("Quest-." , question.getQuestion());
            if (i==0){
                Question question ;
                question = questions.get(i);
                question1.setText("Q 01. "+question.getQuestion());
                answer1.setText(question.getAnswer());
            }
            if (i==1){
                Question question ;
                question = questions.get(i);
                question2.setText("Q 02. "+question.getQuestion());
                answer2.setText(question.getAnswer());
            }
            if (i==2){
                Question question ;
                question = questions.get(i);
                question3.setText("Q 03. "+question.getQuestion());
                answer3.setText(question.getAnswer());
            }
            if (i==3){
                Question question ;
                question = questions.get(i);
                question4.setText("Q 04. "+question.getQuestion());
                answer4.setText(question.getAnswer());
            }
            if (i==4){
                Question question ;
                question = questions.get(i);
                question5.setText("Q 05. "+question.getQuestion());
                answer5.setText(question.getAnswer());
            }
        }
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