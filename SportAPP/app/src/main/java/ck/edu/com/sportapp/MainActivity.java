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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database myDB;
    Button stats, match;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

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

        match = (Button) findViewById(R.id.newMatch);
        stats = (Button) findViewById(R.id.stat);
        myDB = new Database(this);
        listItem = new ArrayList<>();

        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewMatch();
            }
        });
    }

    public void startNewMatch(){
        Intent i = new Intent(this, AccueilMatch.class);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void seeStat (View view) {
        Cursor cursor = myDB.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show!", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()) {
                listItem.add("Teams : "+cursor.getString(1)+"\n");
                listItem.add("Score : "+cursor.getString(2)+"\n");
                listItem.add("Assist : "+cursor.getString(3)+"\n");
                listItem.add("Fault : "+cursor.getString(4)+"\n");
                listItem.add("Rebound : "+cursor.getString(5)+"\n");
            }

            popUpStat("Data", listItem.toString());
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
