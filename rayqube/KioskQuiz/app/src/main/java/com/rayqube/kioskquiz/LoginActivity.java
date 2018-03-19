package com.rayqube.kioskquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    EditText mailId;
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
    EmailValidator emailValidator;
    ProgressBar progressBar;
    private int progressStatus = 0;

    private TextView textView;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mailId = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);
        queue = Volley.newRequestQueue(this);
        helper =  new DbHelper(this);
        emailValidator = new EmailValidator();
        Button startButton = findViewById(R.id.start_button);
        try {
            currentQuestion = questionList.get(quid);
//            setQuestionView();
        } catch (Exception e) {
//            progressBar.setVisibility(View.VISIBLE);
            loadQuestions();
            e.printStackTrace();
        }
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailString = mailId.getText().toString();
                if (emailValidator.validate(mailString)) {
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mainIntent.putExtra("EMAIL_ID", mailString);
                    startActivity(mainIntent);
                    LoginActivity.this.finish();
                }
               else {
                    new MaterialDialog.Builder(LoginActivity.this)
                            .title("Email not valid")
                            .content(R.string.error)
                            .positiveText(R.string.longer_positive)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    // TODO
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private void loadQuestions(){
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest postRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    String question;
                    String jsonOption1,jsonOption2,jsonOption3,jsonOption4,jsonOption5,answer;
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0;i<response.length();i++)
                        {
                            {
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
                                } catch (JSONException e) { e.printStackTrace(); }
                            }
//                  Logger.i("question-> " + question + "- " + "jsonOption1"+"->" + jsonOption1+"- jsonOption2" + jsonOption2 +" ->"+  jsonOption3 +" ="+  jsonOption4+ "->" +answer);
                            Question q1 = new Question(question, jsonOption1, jsonOption2, jsonOption3, jsonOption4,jsonOption5,answer);
                            helper.addQuestionToDB(q1);

                        }
                                progressBar.setVisibility(View.GONE);
//                        showSuccessDialog();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.i("Internet is not there ",error.toString());
                new MaterialDialog.Builder(LoginActivity.this)
                        .title("Internet is not there")
                        .content(R.string.success)
                        .positiveText(R.string.longer_positive)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                // TODO
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }
        );
        queue.add(postRequest);


    }
//    private void showSuccessDialog() {

//        new MaterialDialog.Builder(LoginActivity.this)
//                .title("Database Loaded")
//                .content(R.string.success)
//                .positiveText(R.string.longer_positive)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        // TODO
////                        currentQuestion = questionList.get(quid);
////                        setQuestionView();
//                        dialog.dismiss();
//                    }
//                })
//                .show();
//    }
}
