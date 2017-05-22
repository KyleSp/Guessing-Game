package com.test.kspurloc.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean initialRun = true;

    private int MIN = 1;
    public static int max = 100;
    private Random rand = new Random();
    private int answer = rand.nextInt(max - MIN + 1) + MIN;

    private boolean replay = false;

    private int numGuesses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button guessButton = (Button) findViewById(R.id.guessButton);
        final EditText editTextGuess = (EditText) findViewById(R.id.editTextGuess);
        final TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        final TextView textViewNumGuesses = (TextView) findViewById(R.id.textViewNumGuesses);
        final TextView textViewGuessNumber = (TextView) findViewById(R.id.textViewGuessNumber);

        initialRun = false;

        guessButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (!replay) {
                    int guess = checkValidInput(editTextGuess);

                    if (guess >= 0) {
                        String text;
                        if (guess == answer) {
                            text = "You guessed correctly!";
                            guessButton.setText("Replay");
                            String numGuessesText = "Number of guesses: " + numGuesses;
                            textViewNumGuesses.setText(numGuessesText);
                            replay = true;
                        } else if (guess < answer) {
                            text = "The answer is bigger!";
                        } else {
                            text = "The answer is smaller!";
                        }

                        textViewResult.setText(text);
                        editTextGuess.setText("");

                        ++numGuesses;
                    }
                } else {
                    replay = false;
                    numGuesses = 0;

                    textViewGuessNumber.setText("Guess a Number from 1 to " + Integer.toString(max));

                    answer = rand.nextInt(max - MIN + 1) + MIN;

                    guessButton.setText("Guess");
                    textViewResult.setText("");
                    textViewNumGuesses.setText("");
                }
            }
        });
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
            goToSettingsActivity();
            return true;
        } else if (id == R.id.quit) {
            //this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int checkValidInput(EditText editTextGuess) {
        int guess;
        try {
            guess = Integer.parseInt(editTextGuess.getText().toString());
        } catch (NumberFormatException e) {
            guess = -1;
        }
        return guess;
    }

    public void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}