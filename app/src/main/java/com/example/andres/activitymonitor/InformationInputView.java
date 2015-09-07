package com.example.andres.activitymonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class InformationInputView extends Activity {

    public static final String EXTRA_USER = "com.example.andres.activitymonitor.USER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateGender(View view) {
        RadioButton femaleOption = (RadioButton) findViewById(R.id.female);
        RadioButton maleOption = (RadioButton) findViewById(R.id.male);

        switch(view.getId()) {
            case R.id.male:
                if(femaleOption.isChecked()) {
                    femaleOption.setChecked(false);
                }
                break;
            case R.id.female:
                if(maleOption.isChecked()) {
                    maleOption.setChecked(false);
                }
                break;
        }
    }

    public void processInformation(View view) {
        EditText nameField = (EditText) findViewById(R.id.username);
        EditText weightField = (EditText) findViewById(R.id.bodyweight);
        EditText ageField = (EditText) findViewById(R.id.age);
        EditText heightField = (EditText) findViewById(R.id.height);
        ToggleButton heightUnitButton = (ToggleButton) findViewById(R.id.height_unit);
        ToggleButton weightUnitButton = (ToggleButton) findViewById(R.id.weight_unit);
        RadioButton maleOption = (RadioButton) findViewById(R.id.male);
        RadioButton femaleOption = (RadioButton) findViewById(R.id.female);

        if(nameField.getText().length() == 0 || weightField.getText().length() == 0 ||
                ageField.getText().length() == 0 || heightField.getText().length() == 0 ||
                (!maleOption.isChecked() && !femaleOption.isChecked())) {
            Toast.makeText(this, R.string.incomplete_information, Toast.LENGTH_LONG).show();
        } else {
            String name = nameField.getText().toString().trim();
            double weight = Double.parseDouble(weightField.getText().toString());
            double height = Double.parseDouble(heightField.getText().toString());
            int age = Integer.parseInt(ageField.getText().toString());
            String heightUnit = heightUnitButton.isChecked() ? "cm" : "in";
            String weightUnit = weightUnitButton.isChecked() ? "kg" : "lbs";
            String gender = maleOption.isChecked() ? "Male" : "Female";

            User user = new User(name, weight, height, heightUnit, weightUnit, age, gender);

            Intent intent = new Intent(this, CalculateActivityView.class);
            intent.putExtra(EXTRA_USER, user);
            startActivity(intent);
        }

    }


}
