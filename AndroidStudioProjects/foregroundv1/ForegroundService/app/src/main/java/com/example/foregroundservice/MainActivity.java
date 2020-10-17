package com.example.foregroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    public static final String INTENT_KEY = "inputExtra";
    private EditText editTextInput; // only for demo




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText)findViewById(R.id.editText);
    }

    // starts the service
    public void startService(View view)
    {
        String input = editTextInput.getText().toString(); // only for demo
        Intent serviceIntent = new Intent(this, MyForegroundService.class); // second parameter is the class we want to open
        // give the intent data to pass
        serviceIntent.putExtra(INTENT_KEY, input);
        // start the service
        startService(serviceIntent);
    }

    // stop service
    public void stopService(View view)
    {
        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        stopService(serviceIntent);
    }
}