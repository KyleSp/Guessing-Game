package com.test.kspurloc.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private Intent intent;
    private Button buttonSetMax;
    private EditText editTextMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intent = getIntent();
        buttonSetMax = (Button) findViewById(R.id.buttonSetMax);
        editTextMax = (EditText) findViewById(R.id.editTextMax);

        buttonSetMax.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newMax = checkValidInput(editTextMax);
                MainActivity.max = newMax;
            }
        });
    }

    public int checkValidInput(EditText editTextMax) {
        int newMax;
        try {
            newMax = Integer.parseInt(editTextMax.getText().toString());
        } catch (NumberFormatException e) {
            newMax = -1;
        }
        return newMax;
    }
}
