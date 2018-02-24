package com.rayqube.kioskquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


public class LoginActivity extends AppCompatActivity {
    EditText name,mailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        mailId = findViewById(R.id.email);
        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameString = name.getText().toString();
                String mailString = mailId.getText().toString();
                if (isValidEmail(mailString) && nameString.length()>=2) {
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mainIntent.putExtra("NAME", nameString);
                    mainIntent.putExtra("EMAIL_ID", mailString);
                    startActivity(mainIntent);
                    LoginActivity.this.finish();
                }
               else {
                    new MaterialDialog.Builder(LoginActivity.this)
                            .title("Name/Email not valid")
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
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
