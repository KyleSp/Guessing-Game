package com.test.kspurloc.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private Intent intent;
    private Button buttonSetMax;
    private Button buttonBack;
    private EditText editTextMax;
    private TextView textViewMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intent = getIntent();
        buttonSetMax = (Button) findViewById(R.id.buttonSetMax);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        editTextMax = (EditText) findViewById(R.id.editTextMax);
        textViewMax = (TextView) findViewById(R.id.textViewMax);

        textViewMax.setText("Maximum Value: " + MainActivity.max);

        buttonSetMax.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newMax = checkValidInput(editTextMax);
                if (newMax > 0) {
                    MainActivity.max = newMax;
                    textViewMax.setText("Maximum Value: " + newMax);
                } else {
                    editTextMax.setText("");
                }
            }
        });

        buttonBack.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
