package ru.kubsu.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.kubsu.database.DatabaseAdapter;
import ru.kubsu.database.Gamer;

public class GamerActivity extends AppCompatActivity {

    private EditText nameBox;
    private EditText scoreBox;
    private Button delButton;

    private DatabaseAdapter adapter;
    private long userId = 0;
    private long userScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamer);

        nameBox = findViewById(R.id.name);
        scoreBox = findViewById(R.id.score);
        delButton = findViewById(R.id.deleteButton);
        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userScore = extras.getLong("score");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            Gamer gamer = adapter.getGamer(userId);
            nameBox.setText(gamer.getName());
            scoreBox.setText(String.valueOf(gamer.getScore()));
            adapter.close();
        } else {
            scoreBox.setText(String.valueOf(userScore));
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view) {

        String name = nameBox.getText().toString();

        Gamer user = new Gamer(userId, name, userScore);

        adapter.open();
        if (userId > 0) {
            adapter.update(user);
        } else {
            adapter.insert(user);
        }
        adapter.close();
        goHome();
    }

    public void delete(View view) {

        adapter.open();
        adapter.delete(userId);
        adapter.close();
        goHome();
    }

    private void goHome() {
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}