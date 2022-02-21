package com.example.flappybird.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flappybird.MenueActivity;
import com.example.flappybird.R;
import com.example.flappybird.profile.data.DatabaseRepository;
import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.user.UserDao;
import com.example.flappybird.profile.data.GameDatabase;

/**
 * @author René Beiermann
 * In diesem Abschnitt wird das Registrieren des Nutzer realisiert.
 * Wird aufgerufen bei erstem Starten der App.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;
    private DatabaseRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        editText = findViewById(R.id.chooseName);
        btn = findViewById(R.id.submit);
        repo = new DatabaseRepository(getApplication());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Wenn Knopf gedrückt wird überprüft ob Name gültig ist.
             * Stimmt der Name kann zurück ins Menü geführt werden, wenn nicht wird der Nutzer hingewiesen einen gültigen Namen einzutragen.
             */
            public void onClick(View view) {
                String registeredName = editText.getText().toString();
                if (registeredName.length() > 0) {
                    repo.insertUsername(registeredName);
                    Intent intent = new Intent(RegisterActivity.this, MenueActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Name is not allowed to be Empty", Toast.LENGTH_LONG);
                }
            }
        });
    }
}