package com.example.mayank.ieteapp1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class FullImage extends AppCompatActivity {
    // ImageView i1;
    TextView title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //i1 = (ImageView)findViewById(R.id.image1);

        title = (TextView)findViewById(R.id.title);
        //description = (TextView)findViewById(R.id.description);

        String titleresult = getIntent().getExtras().getString("Title");
        //String Descrresult = getIntent().getExtras().getString("Description");

        title.setText(titleresult);
        //description.setText(Descrresult);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
