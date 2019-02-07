package nl.hva.msi.level1applicationhigherlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonReset;
    private Button buttonHigher;
    private Button buttonLower;
    private TextView textViewScore;
    private TextView textViewHighscore;
    private TextView textViewLastThrow;

    private int valuePrevious = 0;
    private int valueCurrent = 0;
    private int score = 0;
    private int highscore = 0;
    private ImageView dice;


    private void rollDice() {
        valuePrevious = valueCurrent;
        valueCurrent = new Random().nextInt(6) + 1;

        switch (valueCurrent) {
            case 1:
                dice.setImageDrawable(getDrawable(R.drawable.dice1));
                break;
            case 2:
                dice.setImageDrawable(getDrawable(R.drawable.dice2));
                break;
            case 3:
                dice.setImageDrawable(getDrawable(R.drawable.dice3));
                break;
            case 4:
                dice.setImageDrawable(getDrawable(R.drawable.dice4));
                break;
            case 5:
                dice.setImageDrawable(getDrawable(R.drawable.dice5));
                break;
            case 6:
                dice.setImageDrawable(getDrawable(R.drawable.dice6));
                break;
        }
    }

    private void checkInput(boolean chooseHigher) {
        rollDice();

        if (valueCurrent == valuePrevious) {
            throwAgain();
        } else if (chooseHigher && valueCurrent > valuePrevious) {
            answerCorrect();
        } else if (chooseHigher && !(valueCurrent > valuePrevious)) {
            answerIncorrect();
        } else if (!chooseHigher && valueCurrent < valuePrevious) {
            answerCorrect();
        } else if (!chooseHigher && !(valueCurrent < valuePrevious)) {
            answerIncorrect();
        }
        updateUI();
    }

    private void updateUI() {
        textViewScore.setText("Score: " + score);

        textViewHighscore.setText("HighScore: " + highscore);

        textViewLastThrow.setText("Last throw: " + valuePrevious);
    }

    private void answerIncorrect() {
        Toast.makeText(this, "Game over your out!", Toast.LENGTH_SHORT).show();
        if (score > highscore) {
            highscore = score;
        }
        score = 0;
    }

    private void answerCorrect() {
        Toast.makeText(this, "That's correct!", Toast.LENGTH_SHORT).show();
        score++;
    }

    private void throwAgain() {
        Toast.makeText(this, "Throw again pls...", Toast.LENGTH_SHORT).show();
    }

    private void reset() {
        score = 0;
        highscore = 0;
        valuePrevious = 0;
        updateUI();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewScore = findViewById(R.id.textViewScore);
        textViewHighscore = findViewById(R.id.textViewHighscore);
        textViewLastThrow = findViewById(R.id.textViewLastThrow);

        dice = findViewById(R.id.imageViewDice);
        buttonHigher = findViewById(R.id.buttonHigher);
        buttonLower = findViewById(R.id.buttonLower);
        buttonReset = findViewById(R.id.buttonReset);

        rollDice();
        updateUI();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        buttonLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput(false);
            }
        });

        buttonHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput(true);
            }
        });


    }
}
