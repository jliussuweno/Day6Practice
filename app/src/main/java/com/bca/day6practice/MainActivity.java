package com.bca.day6practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText et_password;
    Button button_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_password = findViewById(R.id.editTextPassword);
        button_go = findViewById(R.id.button);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = et_password.getText().toString().trim();

                if (password.isEmpty()){
                    Context context = getApplicationContext();
                    CharSequence text = "Password Tidak Boleh Kosong";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.d("TAG", "onClick if main: "+ password);
                } else {
                    Intent intent = new Intent(MainActivity.this, ChildActivity.class);
                    Log.d("TAG", "onClick else main: "+ password);
                    intent.putExtra("Password", password);
                    startActivityForResult(intent, 100);
                }


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            char[] chars = data.getStringExtra("Password").toCharArray();
            StringBuilder sb = new StringBuilder();
            for(char c : chars){
                if(Character.isDigit(c)){
                    sb.append(c);
                }
            }

            int length = String.valueOf(sb).length();

            Log.d("TAG", "onActivityResult: " + sb + length);
            if (length != 0){
                Context context = getApplicationContext();
                CharSequence text = data.getStringExtra("Password") + " Mengandung Integer";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = data.getStringExtra("Password") + " Tidak Mengandung Integer";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        } else {
            Context context = getApplicationContext();
            CharSequence text = data.getStringExtra("Password") + " Gagal StartActivity";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}