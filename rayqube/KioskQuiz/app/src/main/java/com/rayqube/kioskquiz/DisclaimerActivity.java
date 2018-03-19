package com.rayqube.kioskquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisclaimerActivity extends AppCompatActivity {
    RequestQueue queue;
    String url = "http://rayqube.com/projects/kiosk_quiz/rest/save_responce";
    ProgressBar progressBar;
    DbHelper helper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        Button acceptButton = findViewById(R.id.accept_button);
        helper =  new DbHelper(this);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.prize);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locatationIntent = new Intent(DisclaimerActivity.this,LocationActivity.class);
                startActivity(locatationIntent);
            }
        });
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(DisclaimerActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                DisclaimerActivity.this.finish();
            }
        });
        queue = Volley.newRequestQueue(this);
        ImageView sync_image = findViewById(R.id.sync_image);
        sync_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                List<People> peopleList =  new ArrayList<>();
                peopleList = helper.getPeopleScore();
                for (int i=0;i<peopleList.size();i++)
                {
                    People people =  new People();
                    people = peopleList.get(i);
                    sendResponse(people);
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
    private void sendResponse(final People people){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        progressBar.setVisibility(View.GONE);

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name",people.getMailId());
                params.put("email", people.getMailId());
                params.put("score", String.valueOf(people.getScore()));
                params.put("submitted_on", people.getTime());
                return params;
            }
        };
        queue.add(postRequest);
    }
}
