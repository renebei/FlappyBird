package com.example.flappybird.profile;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.flappybird.MenueActivity;
import com.example.flappybird.R;
import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.user.UserDao;
import com.example.flappybird.profile.data.GameDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;
    private UserDao UDao;
    private GameDatabase Udb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Udb = GameDatabase.getInstance(this);
        UDao = Udb.UserDao();
        editText = findViewById(R.id.chooseName);
        btn = findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registeredName = editText.getText().toString();
                if (registeredName != null) {
                    User u = new User(registeredName);
                    UDao.insert(u);
                    Intent intent = new Intent(RegisterActivity.this, MenueActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}