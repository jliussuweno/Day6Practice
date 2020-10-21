package com.bca.day6practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    TextView tv_password;
    Button button_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        tv_password = findViewById(R.id.textViewChild);
        button_send = findViewById(R.id.buttonSend);

        Intent intent = getIntent();

        String password = intent.getStringExtra("Password");
        tv_password.setText(password);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent replyIntent = new Intent();
                if (password.length() == 0){
                    Log.d("TAG", "onClick if: "+ password);
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    Log.d("TAG", "onClick else: "+ password);
                    replyIntent.putExtra("Password", password);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }
}