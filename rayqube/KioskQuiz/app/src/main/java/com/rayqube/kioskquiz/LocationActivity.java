package com.rayqube.kioskquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class LocationActivity extends AppCompatActivity {
    String place;
    EditText location;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        location = findViewById(R.id.location);

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i("Location->" + location.getText().toString());
        sharedPreferences = LocationActivity.this.getSharedPreferences("location",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                place = ;
                Logger.i("Location->" + location.getText().toString());
                editor.putString("location_key",location.getText().toString());
                editor.apply();
                editor.commit();
                Log.i("Location",sharedPreferences.getString("location_key","Data not there"));
                Intent disclamerIntent = new Intent(LocationActivity.this,DisclaimerActivity.class);
                startActivity(disclamerIntent);
                LocationActivity.this.finish();

            }
        });
    }
}
