package bonnett.kevin.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {0,0,0,0,0,0,0,0,0};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

//    0 = yellow; 1 = red

    int activePlayer = 1;

    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 0 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            }

            counter.setTranslationY(-1500);

            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] winningPosition : winningPositions) {
                int[] emptyGameState = {0,0,0,0,0,0,0,0,0};

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 0) {

                    gameActive = false;

                    String winner = "";
                    if (activePlayer == 2) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    if (winner == "Yellow") {
                        winnerTextView.setText(Html.fromHtml("<font color='#f4d742'>" + winner + " has won! </font>"));
                    } else {
                        winnerTextView.setText(Html.fromHtml("<font color='#f44441'>" + winner + " has won! </font>"));
                    }
                    winnerTextView.setVisibility(View.VISIBLE);

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);
                } else if (gameState[winningPosition[0]] != gameState[winningPosition[1]]
                                && gameState[winningPosition[1]] != gameState[winningPosition[2]]
                                && gameState[0] != 0
                                && gameState[1] != 0
                                && gameState[2] != 0
                                && gameState[3] != 0
                                && gameState[4] != 0
                                && gameState[5] != 0
                                && gameState[6] != 0
                                && gameState[7] != 0
                                && gameState[8] != 0) {
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText("It's a tie!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    public void playAgain(View view) {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageResource(0);
        }

        for (int i=0; i<gameState.length; i++){
            gameState[i] = 0;
        }

        activePlayer = 1;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
