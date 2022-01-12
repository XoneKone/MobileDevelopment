package ru.kubsu.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ru.kubsu.database.DatabaseAdapter;
import ru.kubsu.database.Gamer;

public class WinnerActivity extends AppCompatActivity {
    private ListView userList;
    private ArrayList<Gamer> gamers;
    ArrayAdapter<Gamer> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        userList = findViewById(R.id.list);
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
        arrayAdapter = new ArrayAdapter<>(WinnerActivity.this, android.R.layout.simple_list_item_1, gamers);
        userList.setAdapter(arrayAdapter);

    }

    public void setData() {
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();
        gamers = adapter.getGamers();
        adapter.close();

    }

}
