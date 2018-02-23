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
    textView = findViewById(R.id.textView);
    queue = Volley.newRequestQueue(this);
    progressBar = findViewById(R.id.progressBar);
    loadQuestions();
  }


  private void loadQuestions(){

    JsonArrayRequest postRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      new Response.Listener<JSONArray>() {
        String question;
        String jsonOption1,jsonOption2,jsonOption3,jsonOption4,jsonOption5,answer;
        @Override
        public void onResponse(JSONArray response) {

          for (int i=0;i<response.length();i++)
          {
            if (i==1)
            {
              try {
                JSONObject jsonObject = response.getJSONObject(i);
//                    Logger.json(jsonObject.toString());
                answer = jsonObject.getString("answer");
                question = jsonObject.getString("question");

                JSONArray options = jsonObject.getJSONArray("options");

                for (int y=1;y<options.length();y++)
                {
                  jsonOption1 = options.getString(0);
                  jsonOption2 = options.getString(1);
                  jsonOption3 = options.getString(2);
                  jsonOption4 = options.getString(3);

                }



              } catch (JSONException e) {
                e.printStackTrace();
              }
            }
            else {
              try {
                JSONObject jsonObject = response.getJSONObject(i);
//                    Logger.json(jsonObject.toString());
                answer = jsonObject.getString("answer");
                question = jsonObject.getString("question");

                JSONArray options = jsonObject.getJSONArray("options");

                for (int y = 1; y < options.length(); y++) {
                  jsonOption1 = options.getString(0);
                  jsonOption2 = options.getString(1);
                  jsonOption3 = options.getString(2);
                  jsonOption4 = options.getString(3);
                  jsonOption5 = options.getString(4);

                }


              } catch (JSONException e) {
                e.printStackTrace();
              }
            }
            Logger.i("question-> " + question + "- " + "jsonOption1"+"->" + jsonOption1+"- jsonOption2" + jsonOption2 +" ->"+  jsonOption3 +" ="+  jsonOption4+ "->" +answer);
            Question q1 = new Question(question, jsonOption1, jsonOption2, jsonOption3, jsonOption4,jsonOption5,answer);
            helper.addQuestionToDB(q1);
          }
          Intent mainActivityIntent = new Intent(LoadQuestionActivity.this,MainActivity.class);
          startActivity(mainActivityIntent);
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Logger.i("Error",error.toString());
      }
    }
    );
    queue.add(postRequest);


  }
}
