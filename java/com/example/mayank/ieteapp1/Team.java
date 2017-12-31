package com.example.mayank.ieteapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Team extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        TextView tv1=(TextView)findViewById(R.id.tv);
        tv1.setMovementMethod(new ScrollingMovementMethod());
    }
}
