package ru.kubsu.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SudokuActivity extends AppCompatActivity {
    private Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        int level = 1;
        Bundle args = getIntent().getExtras();
        if (args != null) {
            level = args.getInt("countOfPassValues");
        }

        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.chronometer.start();
        GameEngine.getInstance().createGrid(this, level);
    }

    public void checkGame(View view) {
        if (GameEngine.getInstance().checkGrid()) {
            long score = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            System.out.println(score);
            Intent intent = new Intent(getApplicationContext(), GamerActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Неправильное решение/Есть пустые клетки!", Toast.LENGTH_SHORT).show();
        }
    }

}
