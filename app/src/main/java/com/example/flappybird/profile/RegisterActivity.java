package com.example.flappybird.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.flappybird.R;
import com.example.flappybird.profile.data.User;
import com.example.flappybird.profile.data.UserDao;
import com.example.flappybird.profile.data.UserDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;
    private UserDao UDao;
    private UserDatabase Udb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Udb = UserDatabase.getInstance(this);
        UDao = Udb.UserDao();
        editText = findViewById(R.id.inputText);
        btn = findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registeredName = editText.getText().toString();
                if (registeredName != null) {
                    User u = new User(registeredName);
                    UDao.insert(u);
                    Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}