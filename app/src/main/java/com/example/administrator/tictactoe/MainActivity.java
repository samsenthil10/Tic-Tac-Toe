package com.example.administrator.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0=cross,1=red.
    int activePlayer=0;
    boolean gameIsActive= true;
    // gameState 2 means unplayed.
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive==true){
            gameState[tappedCounter]=activePlayer;
            if(activePlayer==0){

                counter.setImageResource(R.drawable.cross);
                activePlayer=1;}
            else{

                counter.setImageResource(R.drawable.ring);
                activePlayer=0;}
            counter.animate().alpha(1f).setDuration(1000);
            for(int[] winningPosition: winningPositions){
                String winner="Player One";
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2){
                    //someone has won
                    gameIsActive=false;
                    if(gameState[winningPosition[0]]==1){

                        winner="Player Two";
                    }
                    TextView winnerPlayer= (TextView)findViewById(R.id.winnerPlayer);
                    winnerPlayer.setText(winner+" Has Won");
                    LinearLayout play1 = (LinearLayout)findViewById(R.id.play1);
                    LinearLayout play2 = (LinearLayout)findViewById(R.id.play2);
                    play1.animate().alpha(1f).setDuration(1000);
                    play2.animate().alpha(1f).setDuration(1000);
                }else{
                    boolean gameIsOver=true;
                    for(int counterState :gameState){
                        if(counterState==2){gameIsOver=false;}

                    }
                    if(gameIsOver){
                        TextView winnerPlayer= (TextView)findViewById(R.id.winnerPlayer);
                        winnerPlayer.setText("Well Played ! It's A Draw");
                        LinearLayout play1 = (LinearLayout)findViewById(R.id.play1);
                        LinearLayout play2 = (LinearLayout)findViewById(R.id.play2);
                        play1.animate().alpha(1f).setDuration(1000);
                        play2.animate().alpha(1f).setDuration(1000);}
                }
            }

        }}

    public void newGame(View view)
    {

        LinearLayout play1 = (LinearLayout)findViewById(R.id.play1);
        LinearLayout play2 = (LinearLayout)findViewById(R.id.play2);
        play1.animate().alpha(0f);
        play2.animate().alpha(0f);
        activePlayer=0;
        gameIsActive=true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout2 = (GridLayout) findViewById(R.id.gridLayout2);
        for(int i=0;i<gridLayout2.getChildCount();i++)
        {
            ((ImageView) gridLayout2.getChildAt(i)).setImageResource(0);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
