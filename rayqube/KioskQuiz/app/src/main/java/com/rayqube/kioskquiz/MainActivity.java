package com.rayqube.kioskquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  List<Question> questionList;
  int score = 0;
  int quid = 0;
  Question currentQuestion;
  String url = "http://rayqube.com/projects/kiosk_quiz/rest/getquestion";
  TextView txtQuestion;
  RadioButton rda,rdb,rdc,rdd,rde;
  Button butNext;
  RequestQueue queue;
  DbHelper helper;
  String name,emailId;
  ProgressBar progressBar;
  private int progressStatus = 0;
  private TextView textView;
  private Handler handler = new Handler();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////    setSupportActionBar(toolbar);
    queue = Volley.newRequestQueue(this);
    progressBar = findViewById(R.id.progressBar);
    Intent getIntent = getIntent();
    name = getIntent.getStringExtra("NAME");
    emailId = getIntent.getStringExtra("EMAIL_ID");
    DbHelper dbHelper = new DbHelper(this);
    questionList = dbHelper.getAllQuestions();
    textView = (TextView) findViewById(R.id.textView);
//    Collections.shuffle(questionList);
    currentQuestion = questionList.get(quid);
    dbHelper = new DbHelper(MainActivity.this);
    Logger.addLogAdapter(new AndroidLogAdapter());
    txtQuestion = (TextView)findViewById(R.id.question);
    rda     = findViewById(R.id.radio0);
    rdb     = findViewById(R.id.radio1);
    rdc     = findViewById(R.id.radio2);
    rdd     = findViewById(R.id.radio3);
    rde     = findViewById(R.id.radio4);
    rda.setChecked(false);
    rdb.setChecked(false);
    rdc.setChecked(false);
    rdd.setChecked(false);
    rde.setChecked(false);
    butNext = findViewById(R.id.button1);
    setQuestionView();

  }

  private void setQuestionView(){
    txtQuestion.setText(currentQuestion.getQuestion());
    rda.setText(currentQuestion.getOptA());
    rdb.setText(currentQuestion.getOptB());
    rdc.setText(currentQuestion.getOptC());
    rdd.setText(currentQuestion.getOptD());
    rde.setText(currentQuestion.getOptE());
    quid++;
  }

  public void btClick(View view){
    RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup1);
    RadioButton answer = (RadioButton)findViewById(grp.getCheckedRadioButtonId());
    if(currentQuestion.getAnswer().equals(answer.getText())){
      score++;
      grp.clearCheck();
      answer.setChecked(false);
      Log.d("Score", "Your score: "+score);
    }
    else {
      grp.clearCheck();
      answer.setChecked(false);
    }

    if(quid<5){
      currentQuestion = questionList.get(quid);
      setQuestionView();
    }else{
      Intent intent = new Intent(MainActivity.this, ResultActivity.class);
      Bundle b = new Bundle();
      b.putInt("score",score);
      b.putString("NAME",name);
      b.putString("EMAIL_ID",emailId);
      intent.putExtras(b);
      startActivity(intent);
      finish();
    }
  }

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    return true;
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }


}
