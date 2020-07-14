package com.example.mycat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enter = findViewById (R.id.button);

        enter.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                Intent a = new Intent( MainActivity.this,sign_in_Activity.class);
                startActivity(a);
            }
        });
    }
}