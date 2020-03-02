package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            String id = sharedPreferences.getString(usernameKey, "");
            //Intent intent = new Intent(this, LoginActivity.class);
            //intent.putExtra("id", id);

            //startActivity(intent);
            afterLoginActivity(id);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void clickFunction(View view) {
        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);

        String loginid = usernameField.getText().toString();
        String loginpswd = passwordField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", loginid).apply();

        afterLoginActivity(loginid);
    }

    public void afterLoginActivity(String id) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("id", id);
        //intent.putExtra("password", password);

        startActivity(intent);
    }
}
