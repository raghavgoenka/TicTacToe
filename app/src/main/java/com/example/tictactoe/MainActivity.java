package com.example.tictactoe;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridLayout grid;
    TextView turn;
    int activep = 0;
    boolean gameactive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};





    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {
        turn=(TextView) findViewById(R.id.turn);
        ImageView counter = (ImageView) view;
        counter.getTag();
        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        turn.setText("Yellow's Turn Tap to play!");

        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activep;
            counter.setTranslationY(-1000f);
            if (activep == 0) {
                counter.setImageResource(R.drawable.yellow);
                activep = 1;

                turn.setText("Red's Turn Tap to play!");
                turn.setTextColor(Color.parseColor("#F11111"));
            } else {
                counter.setImageResource(R.drawable.red);
                activep = 0;

                turn.setText("Yellow's Turn Tap to play!");
                turn.setTextColor(Color.parseColor("#F9A825"));
            }
            counter.animate().translationYBy(1000f).rotation(720).setDuration(50);
            for (int[] winPos : winPos) {
                if (gamestate[winPos[0]] == gamestate[winPos[1]] && gamestate[winPos[1]] == gamestate[winPos[2]] && gamestate[winPos[0]] != 2) {
                    gameactive = false;
                    String winner = "Red";
                    if (gamestate[winPos[0]] == 0) {
                        winner = "Yellow";
                    }
                    {

                        TextView winnermessage = (TextView) findViewById(R.id.winner);
                        winnermessage.setText(winner + " has won" + " Wohoo !");

                        LinearLayout layout = findViewById(R.id.playagainlayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                } else {
                    boolean gameIsover = true;
                    for (int counterState : gamestate) {
                        if (counterState == 2) {
                            gameIsover = false;
                        }
                    }
                    if (gameIsover) {

                        TextView winnermessage = (TextView) findViewById(R.id.winner);
                        winnermessage.setText("It's a draw!");
                        LinearLayout layout = findViewById(R.id.playagainlayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridlayout);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }


    @SuppressLint("SetTextI18n")
    public void playAgain(View v)
    {
       turn.setText("Yellow's turn Tap to play!");
        turn.setTextColor(Color.parseColor("#F9A825"));
        gameactive=true;
        activep=0;

        for(int i=0;i<gamestate.length;i++) gamestate[i] = 2;

        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        LinearLayout layout = findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
    }
    }