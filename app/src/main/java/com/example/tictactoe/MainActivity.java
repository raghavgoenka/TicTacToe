package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridLayout grid;
    int activep = 0;
    boolean gameactive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // = new AlertDialog.Builder(this);


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        counter.getTag();
        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        //counter.setTranslationY(-1000f);
        //counter.setImageResource(R.drawable.yellow);
        //
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activep;
            counter.setTranslationY(-1000f);
            if (activep == 0) {
                counter.setImageResource(R.drawable.yellow);
                activep = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activep = 0;
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
                        //someone has won
                        TextView winnermessage = (TextView) findViewById(R.id.winner);
                        winnermessage.setText(winner + " has won" + " Wohoo !");

//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//                        alertDialogBuilder.setMessage(winner+" has won!!!\n DO you want to play again? ");
//                        alertDialogBuilder.setPositiveButton("yes",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface arg0, int arg1) {
//
//                                        gameactive=true;
//                                        activep=0;
//
//                                        for(int i=0;i<gamestate.length;i++){
//                                            gamestate[i]=2;
//                                        }
//                                        {
//                                            for(int  i=0;i<grid.getChildCount();i++)
//                                            {
//
//                                                ((ImageView)grid.getChildAt(i)).setImageResource(0);
//                                            }}
//                                        Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
//                                    }
//                                });
//
//                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//                        alertDialog.show();
                        LinearLayout layout = findViewById(R.id.playagainlayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                } else {
                    boolean gameIsover = true;
                    for (int counterState : gamestate) {
                        if (counterState == 2)
                            gameIsover = false;
                    }
                    if (gameIsover) {
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//                        alertDialogBuilder.setMessage("It is a Draw! Do you want to play again?");
//                        alertDialogBuilder.setPositiveButton("yes",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface arg0, int arg1) {
//
//                                        gameactive=true;
//                                        activep=0;
//
//                                        for(int i=0;i<gamestate.length;i++){
//                                            gamestate[i]=2;
//                                        }
//
//                                        for (int i = 0; i < grid.getChildCount(); i++) {
//                                            ((ImageView) grid.getChildAt(i)).setImageResource(0);
//                                        }
//
//                                        Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
//                                    }
//                                });
//
//                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//                        alertDialog.show();
//                    }
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
    }

    public void playAgain(View v)
    {
        gameactive=true;
        activep=0;

        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }

        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        LinearLayout layout = findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
    }
    }