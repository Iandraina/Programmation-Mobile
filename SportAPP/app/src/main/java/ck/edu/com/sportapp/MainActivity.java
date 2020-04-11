package ck.edu.com.sportapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Déclaration des variables
    Database myDB;
    Button stats, match;
    StringBuffer stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //Initialisation des variables
        match = (Button) findViewById(R.id.newMatch);
        stats = (Button) findViewById(R.id.stat);
        myDB = new Database(this);
        stringBuffer = new StringBuffer();

        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewMatch();
            }
        });
    }

    //Commencer une activité (nouvel enregistrement de match)
    public void startNewMatch(){
        Intent i = new Intent(this, AccueilMatch.class);
        startActivity(i);
    }




    //regarder les statistiques (les informations sur les match déjà sauvegardé localement)
    public void seeStat (View view) {
        Cursor cursor = myDB.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show!", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()) {
                stringBuffer.append("Teams : "+cursor.getString(1)+"\n");
                stringBuffer.append("Score : "+cursor.getString(2)+"\n");
                stringBuffer.append("Assist : "+cursor.getString(3)+"\n");
                stringBuffer.append("Fault : "+cursor.getString(4)+"\n");
                stringBuffer.append("Rebound : "+cursor.getString(5)+"\n");
                stringBuffer.append("\n");
                stringBuffer.append("----------------------------------------------\n");
                stringBuffer.append("\n");
            }

            popUpStat("Registered matches data", stringBuffer.toString());
        }
    }

    public void popUpStat(String title, String mes) {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setCancelable(true);
        popUp.setTitle(title);
        popUp.setMessage(mes);
        popUp.show();
    }
}
