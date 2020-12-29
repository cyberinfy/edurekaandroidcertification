package com.tcs.assignmentfive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    EditText editTextPersonName, editTextPassword;
    File file;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        file = new File(getExternalFilesDir("assignmentfive"),"creds.txt");
        Log.d("tag",file.getAbsolutePath());
        editTextPersonName = (EditText) findViewById(R.id.editTextPersonName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No mounted external storage found", Toast.LENGTH_SHORT).show();
        }
        getDataFromSP();
        String creds = readFromFile(getApplicationContext());
        if(creds.length()>0) {
            ((EditText) findViewById(R.id.editTextPersonName)).setText(creds.substring(10,creds.indexOf("\nPassword: ")));
            ((EditText) findViewById(R.id.editTextPassword)).setText(creds.substring(creds.indexOf("\nPassword: ")+11));
            Toast.makeText(this, "You already login", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUsername(){
        String creds = readFromFile(getApplicationContext());
        String username = "Username: "+((EditText)findViewById(R.id.editTextPersonName)).getText().toString();
        String password = "Password: "+creds.substring(creds.indexOf("\nPassword: ")+11);
        creds = username+"\n"+password;
        writeToFile(creds,getApplicationContext());
        Log.d("tag",readFromFile(getApplicationContext()));
    }
    public void onLogin(View view){
        String username = "Username: "+((EditText)findViewById(R.id.editTextPersonName)).getText().toString();
        String password = "Password: "+((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        String creds = username+"\n"+password;
        writeToFile(creds,view.getContext());
        Log.d("tag",readFromFile(getApplicationContext()));
    }
    private void writeToFile(String data, Context context) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {
        String retrievedData = "";
        try {
            InputStream inputStream = new FileInputStream(file);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }
                inputStream.close();
                retrievedData = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return retrievedData;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Loading Preferences",Toast.LENGTH_LONG).show();
                Intent intentSetPref = new Intent(getApplicationContext(), PreferencesActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentSetPref);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void getDataFromSP() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefList = sharedPreferences.getString("PREF_LIST", "no selection");
        String prefUsername = sharedPreferences.getString("PREF_USERNAME", editTextPersonName.getText().toString());
        if(prefList!="no selection"){
            constraintLayout.setBackgroundColor(Color.parseColor(prefList));
        }
        if(prefUsername!=editTextPersonName.getText().toString() && prefUsername.length()>0){
            editTextPersonName.setText(prefUsername);
            updateUsername();
        }
    }
}