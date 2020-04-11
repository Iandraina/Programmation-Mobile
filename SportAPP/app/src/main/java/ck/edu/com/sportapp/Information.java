package ck.edu.com.sportapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Information extends AppCompatActivity {
    //Déclaration des variables
    Database myDB;
    int cptA1 = 0;
    int cptA2 = 0;
    int cptR1 = 0;
    int cptR2 = 0;
    int cptF1 = 0;
    int cptF2 = 0;
    TextView text1, text2;
    Button location,photo;
    EditText editAssist1, editAssist2, editRebound1, editRebound2, editFault1, editFault2, score1, score2;
    Button btnAP1, btnAP2,btnAM1, btnAM2, btnRP1, btnRP2, btnRM1, btnRM2, btnFP1, btnFP2, btnFM1, btnFM2, saveD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDB = new Database(this);
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

        //Initialisation des variables
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
        saveD = (Button) findViewById(R.id.btnsave);

    }

    //Fonction qui gère la localisation
    public void location() {
        Intent i = new Intent (Information.this, MapsActivity.class );
        startActivity(i);
    }

    //Fonction qui gère l'appareil photo
    public void photoClick() {
        Intent i = new Intent(Information.this, Photo.class);
        startActivity(i);
    }

    //Fonction qui gère le clique des boutons
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnAP :
                cptA1++;
                editAssist1.setText(String.valueOf(cptA1));
                break;
            case R.id.btnAM :
                if(cptA1>=1){
                    cptA1--;
                    editAssist1.setText((String.valueOf(cptA1)));
                }
                break;
            case R.id.btnAP2 :
                cptA2++;
                editAssist2.setText(String.valueOf(cptA2));
                break;
            case R.id.btnAM2 :
                if(cptA2>=1){
                    cptA2--;
                    editAssist2.setText((String.valueOf(cptA2)));
                }
                break;
            case R.id.btnRP :
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

    //Fonction qui permet de sauvegarder les données dans la base locale
    public void saveData(View view){
        String teams = text1.getText().toString()+ "   VS   "+ text2.getText().toString();
        String score = score1.getText().toString()+ "   |   "+ score2.getText().toString();
        String assist = editAssist1.getText().toString()+"   |   "+editAssist2.getText().toString();
        String fault = editFault1.getText().toString()+"   |   "+editFault2.getText().toString();
        String rebound = editRebound1.getText().toString()+"   |   "+editRebound2.getText().toString();

        System.out.println(myDB.countElement());
        if(myDB.countElement() < 5) {
            if(myDB.insertData(teams, score, assist,fault,rebound) == true){
                Toast.makeText(Information.this, "Data inserted successfully!",Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(Information.this, "Data insertion failed!",Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(Information.this, "No more local space available!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
        if (id == R.id.accueil) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }
        else if(id == R.id.accueilMatch){
            Intent i = new Intent(this, AccueilMatch.class);
            startActivity(i);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

