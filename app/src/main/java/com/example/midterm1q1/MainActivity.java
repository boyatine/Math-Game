package com.example.midterm1q1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    RadioGroup difficultyRadioGroup;
    RadioButton difficultyRadioButton;
    String arithmeticMode = ""; // empty for not chosen
    String difficultyChosen;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = difficultyRadioGroup.getCheckedRadioButtonId();

                if (arithmeticMode.equals("")) {
                    toast = Toast.makeText(getApplicationContext(), "Please select a mode from the menu", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,1000);
                    toast.show();
                    return;
                }

                if (selectedId == -1) {
                    toast = Toast.makeText(getApplicationContext(), "Please select a difficulty", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,1000);
                    toast.show();
                    return;
                }

                difficultyRadioButton = findViewById(selectedId);
                difficultyChosen = difficultyRadioButton.getText().toString();
                startGame(arithmeticMode);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.resourcefile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        String selected = "You selected ";

        switch (item.getItemId()) {
            case R.id.additionMenu:
                selected += "addition";
                arithmeticMode = "+";
                break;
            case R.id.subtractionMenu:
                selected += "subtraction";
                arithmeticMode = "-";
                break;
            case R.id.multiplicationMenu:
                selected += "multiplication";
                arithmeticMode = "*";
                break;
            case R.id.divisionMenu:
                selected += "division";
                arithmeticMode = "/";
                break;
        }

        toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,1000);
        toast.show();

        return true;
    }

    private void startGame(String arithmeticMode) {
        Intent nextScreen = new Intent(getApplicationContext(), GameActivity.class);
        nextScreen.putExtra("difficulty", difficultyChosen);
        nextScreen.putExtra("mode", arithmeticMode);
        startActivity(nextScreen);
    }
}
