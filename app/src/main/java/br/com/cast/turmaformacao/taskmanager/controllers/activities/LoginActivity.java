package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cast.turmaformacao.taskmanager.R;

public class LoginActivity extends AppCompatActivity{

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button   buttonLogin;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                startActivity(redirectToTaskList);
                finish();

            }
        });
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
       editTextLogin = (EditText)findViewById(R.id.editTextLogin);
    }

}
