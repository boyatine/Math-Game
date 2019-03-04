package com.example.midterm1q1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.abs;

public class GameActivity extends AppCompatActivity {
    TextView timerTextView, scoreTextView, questionTextView, alertTextView, finalScoreTextView;
    Button answer1Button, answer2Button, answer3Button, answer4Button, restartButton;
    Toast toast;
    CountDownTimer countDownTimer;
    int totalScore = 0;
    int questionsLeft = 10;
    int rightAnswerLoc, min, max, fakeRangeMin, fakeRangeMax, divMin;
    String arithmeticMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        alertTextView = findViewById(R.id.alertTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        answer4Button = findViewById(R.id.answer4Button);
        restartButton = findViewById(R.id.restartButton);

        Intent i = getIntent();
        String difficultyChosen = i.getStringExtra("difficulty");
        arithmeticMode = i.getStringExtra("mode");

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( rightAnswerLoc == 1 )
                    correctAnswer();
                else
                    wrongAnswer();
                createNewQuestion(min, max, arithmeticMode);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( rightAnswerLoc == 2 )
                    correctAnswer();
                else
                    wrongAnswer();
                createNewQuestion(min, max, arithmeticMode);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( rightAnswerLoc == 3 )
                    correctAnswer();
                else
                    wrongAnswer();
                createNewQuestion(min, max, arithmeticMode);
            }
        });
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( rightAnswerLoc == 4 )
                    correctAnswer();
                else
                    wrongAnswer();
                createNewQuestion(min, max, arithmeticMode);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        switch (arithmeticMode) {
            case "+":
                switch (difficultyChosen) {
                    case "Easy":
                        min = 1;
                        max = 8;
                        fakeRangeMin = -4;
                        fakeRangeMax = 4;
                        createNewQuestion(min, max, "+");
                        break;
                    case "Medium":
                        min = 1;
                        max = 16;
                        fakeRangeMin = -8;
                        fakeRangeMax = 8;
                        createNewQuestion(min, max, "+");
                        break;
                    case "Hard":
                        min = 3;
                        max = 24;
                        fakeRangeMin = -12;
                        fakeRangeMax = 12;
                        createNewQuestion(min, max, "+");
                        break;
                }
                break;

            case "-":
                switch (difficultyChosen) {
                    case "Easy":
                        min = 1;
                        max = 8;
                        fakeRangeMin = -4;
                        fakeRangeMax = 4;
                        createNewQuestion(min, max, "-");
                        break;
                    case "Medium":
                        min = 1;
                        max = 16;
                        fakeRangeMin = -8;
                        fakeRangeMax = 8;
                        createNewQuestion(min, max, "-");
                        break;
                    case "Hard":
                        min = 3;
                        max = 24;
                        fakeRangeMin = -12;
                        fakeRangeMax = 12;
                        createNewQuestion(min, max, "-");
                        break;
                }
                break;

            case "*":
                switch (difficultyChosen) {
                    case "Easy":
                        min = 1;
                        max = 5;
                        fakeRangeMin = -5;
                        fakeRangeMax = 5;
                        createNewQuestion(min, max, "*");
                        break;
                    case "Medium":
                        min = 3;
                        max = 10;
                        fakeRangeMin = -20;
                        fakeRangeMax = 20;
                        createNewQuestion(min, max, "*");
                        break;
                    case "Hard":
                        min = 5;
                        max = 15;
                        fakeRangeMin = -40;
                        fakeRangeMax = 40;
                        createNewQuestion(min, max, "*");
                        break;
                }
                break;

            case "/":
                switch (difficultyChosen) {
                    case "Easy":
                        min = 2;
                        max = 51;
                        divMin = 2;
                        fakeRangeMin = -4;
                        fakeRangeMax = 4;
                        createNewQuestion(min, max, "/");
                        break;
                    case "Medium":
                        min = 11;
                        max = 71;
                        divMin = 2;
                        fakeRangeMin = -8;
                        fakeRangeMax = 8;
                        createNewQuestion(min, max, "/");
                        break;
                    case "Hard":
                        min = 35;
                        max = 101;
                        divMin = 3;
                        fakeRangeMin = -12;
                        fakeRangeMax = 12;
                        createNewQuestion(min, max, "/");
                        break;
                }
                break;

        }

        startTimer();
        createNewQuestion(min, max, arithmeticMode);
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    public void updateTimer(int secondsLeft) {
        String secondString = Integer.toString(secondsLeft);

        if (secondsLeft <= 9)
            secondString = "0" + secondString;

        timerTextView.setText(secondString + " sec");
    }

    private void createNewQuestion(int min, int max, String mode) {
        int realAnswer, fakeAnswer1, fakeAnswer2, fakeAnswer3, number1, number2;
        fakeAnswer1 = fakeAnswer2 = fakeAnswer3 = 0; // for initialization

        Random rn = new Random();
        if ( questionsLeft >= 0 ) {
            switch (mode) {
                case "+":
                    number1 = rn.nextInt(max - min + 1) + min;
                    rn = new Random();
                    number2 = rn.nextInt(max - min + 1) + min;
                    realAnswer = number1 + number2;
                    break;
                case "-":
                    number1 = rn.nextInt(max - (min + 1) + 1) + (min + 1);
                    rn = new Random();
                    number2 = rn.nextInt(max - min + 1) + min;
                    while ( number2 >= number1 ) {
                        rn = new Random();
                        number2 = rn.nextInt(max - min + 1) + min;
                    }
                    realAnswer = number1 - number2;
                    break;
                case "*":
                    number1 = rn.nextInt(max - min + 1) + min;
                    rn = new Random();
                    number2 = rn.nextInt(max - min + 1) + min;
                    realAnswer = number1 * number2;
                    break;
                case "/":
                    number1 = rn.nextInt(max - min + 1) + min;
                    while ( isTooEasy(number1) ) {
                        rn = new Random();
                        number1 = rn.nextInt(max - min + 1) + min;
                    }
                    rn = new Random();
                    number2 = rn.nextInt(max - divMin + 1) + divMin;
                    while ( number2 >= number1 || number1 % number2 != 0 ) {
                        rn = new Random();
                        number2 = rn.nextInt(max - divMin + 1) + divMin;
                    }
                    realAnswer = number1 / number2;
                    break;
                default: // should not happen, needed this to get rid of error
                    number1 = 0;
                    number2 = 0;
                    realAnswer = 0;
            }

            questionTextView.setText(number1 + " " + arithmeticMode + " " + number2 + " = ?");

            while ( fakeAnswer1 == 0 || fakeAnswer1 == realAnswer ) {
                rn = new Random();
                fakeAnswer1 = abs(realAnswer + rn.nextInt(fakeRangeMax - fakeRangeMin + 1) + fakeRangeMin);
            }
            while ( fakeAnswer2 == 0 || fakeAnswer2 == realAnswer || fakeAnswer2 == fakeAnswer1 ) {
                rn = new Random();
                fakeAnswer2 = abs(realAnswer + rn.nextInt(fakeRangeMax - fakeRangeMin + 1) + fakeRangeMin);
            }
            while ( fakeAnswer3 == 0 || fakeAnswer3 == realAnswer || fakeAnswer3 == fakeAnswer2 || fakeAnswer3 == fakeAnswer1 ) {
                rn = new Random();
                fakeAnswer3 = abs(realAnswer + rn.nextInt(fakeRangeMax - fakeRangeMin + 1) + fakeRangeMin);
            }

            rn = new Random();
            rightAnswerLoc = rn.nextInt(4 - 1 + 1) + 1;

            switch (rightAnswerLoc) {
                case 1:
                    answer1Button.setText("" + realAnswer);
                    answer2Button.setText("" + fakeAnswer1);
                    answer3Button.setText("" + fakeAnswer2);
                    answer4Button.setText("" + fakeAnswer3);
                    break;
                case 2:
                    answer1Button.setText("" + fakeAnswer1);
                    answer2Button.setText("" + realAnswer);
                    answer3Button.setText("" + fakeAnswer2);
                    answer4Button.setText("" + fakeAnswer3);
                    break;
                case 3:
                    answer1Button.setText("" + fakeAnswer2);
                    answer2Button.setText("" + fakeAnswer1);
                    answer3Button.setText("" + realAnswer);
                    answer4Button.setText("" + fakeAnswer3);
                    break;
                case 4:
                    answer1Button.setText("" + fakeAnswer3);
                    answer2Button.setText("" + fakeAnswer1);
                    answer3Button.setText("" + fakeAnswer2);
                    answer4Button.setText("" + realAnswer);
                    break;
            }

            questionsLeft--;
        }

        else
            endGame();
    }

    boolean isTooEasy(int n) {
        if ( n % 2 == 0 )
            return true;

        for ( int i = 3; i * i <= n; i += 2 ) {
            if ( n % i == 0 )
                return false;
        }

        return true;
    }

    public void correctAnswer() {
        totalScore++;

        toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,1000);
        toast.show();

        scoreTextView.setText(totalScore + "/10");
    }

    public void wrongAnswer() {
        toast = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,1000);
        toast.show();

        scoreTextView.setText(totalScore + "/10");
    }

    private void endGame() {
        timerTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);
        questionTextView.setVisibility(View.GONE);
        alertTextView.setVisibility(View.GONE);
        answer1Button.setVisibility(View.GONE);
        answer2Button.setVisibility(View.GONE);
        answer3Button.setVisibility(View.GONE);
        answer4Button.setVisibility(View.GONE);

        finalScoreTextView.setText("You scored " + totalScore + "/10!");
        finalScoreTextView.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.VISIBLE);
    }
}