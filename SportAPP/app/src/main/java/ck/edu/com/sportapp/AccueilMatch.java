package ck.edu.com.sportapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccueilMatch extends AppCompatActivity {

    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_match);
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

        begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMatch();
            }
        });

    }

    public void startMatch(){
        EditText t1 = (EditText) findViewById(R.id.team1);
        EditText t2 = (EditText) findViewById(R.id.team2);
        String nT1 = t1.getText().toString();
        while(nT1.isEmpty()) {
            Toast.makeText(this,"Enter team 1's name", Toast.LENGTH_SHORT).show();
            return;
        }

        String nT2 = t2.getText().toString();
        while(nT2.isEmpty()) {
            Toast.makeText(this,"Enter team 2's name", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle args = new Bundle();
        args.putString("team1", nT1);
        args.putString("team2", nT2);

        Intent i = new Intent(AccueilMatch.this, Information.class);
        i.putExtras(args);
        startActivity(i);
    }

}
