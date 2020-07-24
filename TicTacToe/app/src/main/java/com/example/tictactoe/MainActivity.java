package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1turn=true;
    private int roundcount;
    private int player1point;
    private int player2point;
    private TextView tvPlayer1;
    private TextView tvPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPlayer1 = findViewById(R.id.tv_p1);
        tvPlayer2 = findViewById(R.id.tv_p2);
        for (int i = 0; i < 3; i++){
            for (int j=0;j<3;j++){
                String buttonid="button_"+i+j;
                int resId= getResources().getIdentifier(buttonid,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1turn){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        roundcount++;
        if (checkForWin()){
            if(player1turn){
                player1Wins();
            }
            else{
                plyer2wins();
            }
        }
        else if (roundcount==9){
            draw();
        }
        else {
            player1turn=!player1turn;
        }
    }
    private boolean checkForWin(){
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])
            && field[i][0].equals(field[i][2])
            && !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")){
                return true;
            }
            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")){
                return true;
            }
        return false;
    }
    private void player1Wins(){
        player1point++;
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void plyer2wins(){
        player2point++;
        Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText(){
        tvPlayer1.setText("Player 1: "+player1point);
        tvPlayer2.setText("Player 2: "+player2point);
    }
    private void resetBoard(){
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }
    private void resetGame(){
        player1point=0;
        player2point=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundcount);
        outState.putInt("player1point",player1point);
        outState.putInt("player2point",player2point);
        outState.putBoolean("player1turn",player1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundcount=savedInstanceState.getInt("roundCount");
        player1point=savedInstanceState.getInt("player1point");
        player2point=savedInstanceState.getInt("player2point");
        player1turn=savedInstanceState.getBoolean("player1turn");
    }
}