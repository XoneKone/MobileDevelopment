package ru.kubsu.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button easyButton;
    Button mediumButton;
    Button hardButton;
    Button quitButton;
    Button gamersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyButton = (Button) findViewById(R.id.easyLevelButton);
        mediumButton = (Button) findViewById(R.id.mediumLevelButton);
        hardButton = (Button) findViewById(R.id.hardLevelButton);
        gamersButton = (Button) findViewById(R.id.gamersButton);
        quitButton = (Button) findViewById(R.id.quitButton);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        gamersButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SudokuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int countOfPassValues;
        switch (v.getId()) {
            case R.id.easyLevelButton:
                countOfPassValues = 1;
                intent.putExtra("countOfPassValues", countOfPassValues);
                startActivity(intent);
                break;
            case R.id.mediumLevelButton:
                countOfPassValues = 30;
                intent.putExtra("countOfPassValues", countOfPassValues);
                startActivity(intent);
                break;
            case R.id.hardLevelButton:
                countOfPassValues = 60;
                intent.putExtra("countOfPassValues", countOfPassValues);
                startActivity(intent);
                break;
            case R.id.gamersButton:
                Intent intent2 = new Intent(this, WinnerActivity.class);
                startActivity(intent2);
                break;
            case R.id.quitButton:
                this.finish();
                break;
        }
    }
}