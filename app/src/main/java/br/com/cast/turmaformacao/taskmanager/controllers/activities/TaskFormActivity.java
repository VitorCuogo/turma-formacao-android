package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class TaskFormActivity extends AppCompatActivity{

    public static final String PARAM_TASK = "PARAM_TASK";
    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextName();
        bindEditTextDescription();
        bindButtonSave();
    }

    private void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = (Task) extras.getParcelable(PARAM_TASK);
        }
        this.task = task == null ? new Task() : this.task;
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);
                if(!FormHelper.requiredFields(requiredMessage, editTextName)){
                    bindTask();
                    TaskBusinessService.save(task);
                    Toast.makeText(TaskFormActivity.this, R.string.msg_save_sucess, Toast.LENGTH_LONG).show();
                    TaskFormActivity.this.finish();
                }
                //Toast.makeText(TaskFormActivity.this, "Save", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void bindTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextname);
        editTextName.setText(task.getName() == null ? "" : task.getName());
    }
}
