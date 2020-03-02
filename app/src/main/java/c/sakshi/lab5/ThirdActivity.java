package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        EditText noteField = (EditText) findViewById(R.id.noteField);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = LoginActivity.notes.get(noteid);
            String noteContent = note.getContent();
            noteField.setText(noteContent);
        }
    }

    public void saveMethod(View view) {
        EditText noteField = (EditText) findViewById(R.id.noteField);
        String noteText = noteField.getText().toString();

        // Initialize SQLiteDatabase instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE,null);

        // Initialize DBHelper Class.
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) { // Add note.
            title = "NOTE_" + (LoginActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, noteText, date);
        } else { // Update note.
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, noteText, username);
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
