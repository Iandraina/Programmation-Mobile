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
import android.widget.TextView;

public class Information extends AppCompatActivity {

    int cptA1 = 0;
    int cptA2 = 0;
    int cptR1 = 0;
    int cptR2 = 0;
    int cptF1 = 0;
    int cptF2 = 0;
    TextView text1, text2;
    Button location,photo;
    EditText editAssist1, editAssist2, editRebound1, editRebound2, editFault1, editFault2, score1, score2;
    Button btnAP1, btnAP2,btnAM1, btnAM2, btnRP1, btnRP2, btnRM1, btnRM2, btnFP1, btnFP2, btnFM1, btnFM2;

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
        score1 = (EditText) findViewById(R.id.textView7);
        score2 = (EditText) findViewById(R.id.textView8);

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

        editAssist1 = (EditText) findViewById(R.id.editAssist);
        editAssist2 = (EditText) findViewById(R.id.editAssist2);
        editRebound1 = (EditText) findViewById(R.id.editRebound);
        editRebound2 = (EditText) findViewById(R.id.editRebound2);
        editFault1 = (EditText) findViewById(R.id.editFault);
        editFault2 = (EditText) findViewById(R.id.editFault2);

        btnAM1 = (Button) findViewById(R.id.btnAM);
        btnAM2 = (Button) findViewById(R.id.btnAM2);
        btnAP1 = (Button) findViewById(R.id.btnAP);
        btnAP2 = (Button) findViewById(R.id.btnAP2);
        btnRM1 = (Button) findViewById(R.id.btnRM);
        btnRM2 = (Button) findViewById(R.id.btnRM2);
        btnRP1 = (Button) findViewById(R.id.btnRP);
        btnRP2 = (Button) findViewById(R.id.btnRP2);
        btnFM1 = (Button) findViewById(R.id.btnFM);
        btnFM2 = (Button) findViewById(R.id.btnFM2);
        btnFP1 = (Button) findViewById(R.id.btnFP);
        btnFP2 = (Button) findViewById(R.id.btnFP2);

    }

    public void location() {
        Intent i = new Intent (Information.this, MapsActivity.class );
        startActivity(i);
    }

    public void photoClick() {
        Intent i = new Intent(Information.this, Photo.class);
        startActivity(i);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnAP:
                cptA1++;
                editAssist1.setText(String.valueOf(cptA1));
                break;
            case R.id.btnAM:
                if(cptA1>=1){
                    cptA1--;
                    editAssist1.setText((String.valueOf(cptA1)));
                }
                break;
            case R.id.btnAP2:
                cptA2++;
                editAssist2.setText(String.valueOf(cptA2));
                break;
            case R.id.btnAM2:
                if(cptA2>=1){
                    cptA2--;
                    editAssist2.setText((String.valueOf(cptA2)));
                }
                break;
            case R.id.btnRP:
                cptR1++;
                editRebound1.setText(String.valueOf(cptR1));
                break;
            case R.id.btnRM:
                if(cptR1>=1){
                    cptR1--;
                    editRebound1.setText((String.valueOf(cptR1)));
                }
                break;
            case R.id.btnRP2:
                cptR2++;
                editRebound2.setText(String.valueOf(cptR2));
                break;
            case R.id.btnRM2:
                if(cptR2>=1){
                    cptR2--;
                    editRebound2.setText((String.valueOf(cptR2)));
                }
                break;
            case R.id.btnFP:
                cptF1++;
                editFault1.setText(String.valueOf(cptF1));
                break;
            case R.id.btnFM:
                if(cptF1>=1){
                    cptF1--;
                    editFault1.setText((String.valueOf(cptF1)));
                }
                break;
            case R.id.btnFP2:
                cptF2++;
                editFault2.setText(String.valueOf(cptF2));
                break;
            case R.id.btnFM2:
                if(cptF2>=1){
                    cptF2--;
                    editFault2.setText((String.valueOf(cptF2)));
                }
                break;
        }
    }
}

