package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public abstract class TicTacToeFragment extends Fragment
implements View.OnClickListener {


    Button aButtons[][] = new Button[3][3];
    boolean player1Turn = true;
    int round;
    public TicTacToeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        aButtons[0][0] = view.findViewById(R.id.button00);
        aButtons[0][1] = view.findViewById(R.id.button01);
        aButtons[0][2] = view.findViewById(R.id.button02);
        aButtons[1][0] = view.findViewById(R.id.button10);
        aButtons[1][1] = view.findViewById(R.id.button11);
        aButtons[1][2] = view.findViewById(R.id.button12);
        aButtons[2][0] = view.findViewById(R.id.button20);
        aButtons[2][1] = view.findViewById(R.id.button21);
        aButtons[2][2] = view.findViewById(R.id.button22);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                aButtons[row][col].setOnClickListener(this);
            }
        }


        // in here link the controls with view.findViewById
        // You will also need this.getActivity().getSharedPreferences if you use things like shared preferences.
        // this.getActivity may also be helpful for communicating between fragments
        return view;
    }
    @Override
    public void onClick(View v) {
        //Button btnClicked = (Button) v;
        // btnClicked.setText("X");

        if (!((Button) v).getText().toString().equals("")) {

            return;
        }

        if (player1Turn) {

            ((Button) v).setText("X");

        } else {
            ((Button) v).setText("O");

        }

        round++;
        if (Check()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (round == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }


        // Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show();
    }

    public boolean Check() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = aButtons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }


    public void player1Wins() {


        newGame();

    }

    public void player2Wins() {

       // Toast.makeText(this, "O wins!", Toast.LENGTH_SHORT).show();
        newGame();
    }

    public void draw() {
       // Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        newGame();

    }

    public void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aButtons[i][j].setText("");
            }
        }

        round = 0;
        player1Turn = true;
    }

}
