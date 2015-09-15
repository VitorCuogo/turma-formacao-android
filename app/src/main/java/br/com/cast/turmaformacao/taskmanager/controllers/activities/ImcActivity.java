package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;

public class ImcActivity extends AppCompatActivity{

    private EditText editTextPeso;
    private EditText editTextAltura;
    private Button   buttonCalcular;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        bindEditTextPeso();
        bindEditTextAltura();
        bindButtonCalcular();
    }

    private void bindButtonCalcular() {
        final Button buttonCalcular = (Button)findViewById(R.id.buttonCalcular);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double peso = Double.valueOf(editTextPeso.getText().toString());
                double altura = Double.valueOf(editTextAltura.getText().toString());

                double imc = peso/(altura*altura);

                String message = getResources().getString(R.string.msg_reply,imc);
                Toast.makeText(ImcActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void bindEditTextAltura() {
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
    }

    private void bindEditTextPeso() {
        editTextPeso = (EditText)findViewById(R.id.editTextPeso);
    }
}
