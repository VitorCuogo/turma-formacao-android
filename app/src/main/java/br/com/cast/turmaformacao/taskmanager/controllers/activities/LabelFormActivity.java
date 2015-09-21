package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;


public class LabelFormActivity extends AppCompatActivity {

    private Spinner spinnerColor;
    public static final String PARAM_TASK = "PARAM_TASK";
    private EditText editTextName;
    private EditText editTextDescription;
    private Label label;
    private Color colorSelect;
    private ListView listColorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);

        initLabel();
        bindSpinnerColor();
        bindTextName();
        bindEditTextDescription();
        bindListColorView();
    }

    private void bindListColorView() {
        listColorView = (ListView) findViewById(R.id.listViewTaskList);
    }

    private void initLabel() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.label = (Label) extras.getParcelable(PARAM_TASK);
        }
        this.label = label == null ? new Label() : this.label;
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(label.getDescription() == null ? "" : label.getDescription());
    }

    private void bindTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(label.getName());
    }


    private void bindLabel() {
        label.setName(editTextName.getText().toString());
        colorSelect = (Color) spinnerColor.getSelectedItem();
        label.setColor(colorSelect);
        label.setDescription(editTextDescription.getText().toString());
    }

    private void bindSpinnerColor() {

        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        Color[] list = Color.values();

        ColorListAdapter colorAdapter = new ColorListAdapter(LabelFormActivity.this,list);

        //  ArrayAdapter<Color> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerColor.setAdapter(colorAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (item.getItemId()) {
            case R.id.menu_save:
                onMenuSaveClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveClick() {
        String requiredMessage = LabelFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.requiredFields(requiredMessage, editTextName)) {
            bindLabel();
            LabelBusinessService.save(label);
            Toast.makeText(LabelFormActivity.this, LabelBusinessService.findAll().toString(), Toast.LENGTH_LONG).show();
        }

    }
}
