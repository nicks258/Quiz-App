package com.rayqube.kioskquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadQuestionActivity extends AppCompatActivity {
  RequestQueue queue;
  DbHelper helper;
  ProgressBar progressBar;
  private int progressStatus = 0;
  private TextView textView;
  String url = "http://rayqube.com/projects/kiosk_quiz/rest/getquestion";
  private Handler handler = new Handler();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Logger.addLogAdapter(new AndroidLogAdapter());
    setContentView(R.layout.activity_load_question);
    helper = new DbHelper(LoadQuestionActivity.this);
    queue = Volley.newRequestQueue(this);
    textView = findViewById(R.id.textView);
    progressBar = findViewById(R.id.progressBar);
//    loadQuestions();
  }



}
