package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridLayout grid;
    TextView turn;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    int activep = 0;
    boolean gameactive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    ArrayList<Integer> pos = new ArrayList<>();
    ArrayList<Integer> player = new ArrayList<>();


    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {
        turn = (TextView) findViewById(R.id.turn);
        ImageView counter = (ImageView) view;

        counter.getTag();
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        pos.add(tappedcounter);
        turn.setText("Yellow's Turn Tap to play!");

        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activep;
            counter.setTranslationY(-1000f);
            if (activep == 0) {
                counter.setImageResource(R.drawable.yellow);
                player.add(activep);
                activep = 1;

                turn.setText("Red's Turn Tap to play!");
                turn.setTextColor(Color.parseColor("#F11111"));
            } else {
                counter.setImageResource(R.drawable.red);
                player.add(activep);
                activep = 0;

                turn.setText("Yellow's Turn Tap to play!");
                turn.setTextColor(Color.parseColor("#F9A825"));
            }
            counter.animate().translationYBy(1000f).rotation(720).setDuration(10);
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
                    pos.clear();player.clear();
                } else {
                    boolean gameIsover = true;
                    for (int counterState : gamestate)
                        if (counterState == 2) {
                            gameIsover = false;
                            break;
                        }
                    if (gameIsover) {

                        TextView winnermessage = (TextView) findViewById(R.id.winner);
                        winnermessage.setText("It's a draw!");
                        LinearLayout layout = findViewById(R.id.playagainlayout);
                        layout.setVisibility(View.VISIBLE);
                        pos.clear();player.clear();
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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        }
    }


    @SuppressLint("SetTextI18n")
    public void playAgain(View v) {
        player.clear();
        pos.clear();
        turn.setText("Yellow's turn Tap to play!");
        turn.setTextColor(Color.parseColor("#F9A825"));
        gameactive = true;
        activep = 0;

        Arrays.fill(gamestate, 2);

        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        LinearLayout layout = findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("activep", activep);
        outState.putIntegerArrayList("pos", pos);
        outState.putIntegerArrayList("player", player);
        outState.putBoolean("gameactive", gameactive);
        outState.putIntArray("gamestate",gamestate);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        activep = savedInstanceState.getInt("activep");
        gameactive = savedInstanceState.getBoolean("gameactive");
        gamestate=savedInstanceState.getIntArray("gamestate");
        pos = savedInstanceState.getIntegerArrayList("pos");
        player = savedInstanceState.getIntegerArrayList("player");
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);
        if(player.size()>0) {
            for (int i = 0; i < pos.size(); i++) {
                int a = pos.get(i);
                Log.i("valueeeeeeeeeee", String.valueOf(a));
                switch (a) {
                    case 0:
                        change(i, img1);
                        break;
                    case 1:
                        change(i, img2);
                        break;
                    case 2:
                        change(i, img3);
                        break;
                    case 3:
                        change(i, img4);
                        break;
                    case 4:
                        change(i, img5);
                        break;
                    case 5:
                        change(i, img6);
                        break;
                    case 6:
                        change(i, img7);
                        break;
                    case 7:
                        change(i, img8);
                        break;
                    case 8:
                        change(i, img9);
                        break;
                }
            }
        }
        else {
            gameactive=true;
            activep=0;
            Arrays.fill(gamestate,2);
        }

    }

    private void change(int i, ImageView imge) {
        if (player.get(i) == 0) {
            imge.setImageResource(R.drawable.yellow);
        } else {
            imge.setImageResource(R.drawable.red);
        }
    }
}