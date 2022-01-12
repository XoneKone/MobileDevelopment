package ru.kubsu.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_SCORE};
        return database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public ArrayList<Gamer> getGamers() {
        ArrayList<Gamer> gamers = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SCORE));
            gamers.add(new Gamer(id, name, score));
        }
        cursor.close();
        return gamers;
    }

    public long getCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public Gamer getGamer(long id) {
        Gamer gamer = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SCORE));
            gamer = new Gamer(id, name, score);
        }
        cursor.close();
        return gamer;
    }

    public long insert(Gamer gamer) {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, gamer.getName());
        cv.put(DatabaseHelper.COLUMN_SCORE, gamer.getScore());

        return database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId) {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Gamer gamer) {

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + gamer.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, gamer.getName());
        cv.put(DatabaseHelper.COLUMN_SCORE, gamer.getScore());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}