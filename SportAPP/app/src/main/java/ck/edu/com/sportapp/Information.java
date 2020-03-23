package ck.edu.com.sportapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    TextView text1, text2;
    Button location,photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        photo = (Button) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoClick();
            }
        });
        location = (Button) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });
        text1 = (TextView) findViewById(R.id.team1name);
        text2 = (TextView) findViewById(R.id.team2name);

        if(savedInstanceState == null) {
          Bundle extras =getIntent().getExtras();
          if(extras == null) {
              text1.setText("");
              text2.setText("");
          }
          else {
              text1.setText(extras.getString("team1"));
              text2.setText(extras.getString("team2"));
          }
        } else{
            text1.setText((String)savedInstanceState.getSerializable("team1"));
            text2.setText((String)savedInstanceState.getSerializable("team2"));
        }
    }

    public void location() {
        Intent i = new Intent (Information.this, MapsActivity.class );
        startActivity(i);
    }

    public void photoClick() {
        Intent i = new Intent(Information.this, Photo.class);
        startActivity(i);
    }

}
