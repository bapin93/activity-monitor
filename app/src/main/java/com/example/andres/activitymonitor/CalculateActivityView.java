package com.example.andres.activitymonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalculateActivityView extends Activity {

    private static final double KM_IN_MILE = 0.621;
    private static final double RUNNING_CONSTANT = 0.63;
    private static final double WALKING_CONSTANT = 0.30;

    private CalculationService _calculationService;
    private User _user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_activity_view);
        Intent i = getIntent();
        _user = (User)i.getSerializableExtra(InformationInputView.EXTRA_USER);
        _calculationService = new CalculationService(_user);
        NumberFormat decimal = DecimalFormat.getNumberInstance();
                ((TextView) findViewById(R.id.username)).setText(_user.getName());
        ((TextView)findViewById(R.id.age)).setText("Age: " + _user.getAge());
        ((TextView)findViewById(R.id.height)).setText("Height: " +
                String.valueOf(_user.getHeight()) + _user.getHeightUnits());
        ((TextView)findViewById(R.id.gender)).setText("Gender: " + _user.getGender());
        ((TextView)findViewById(R.id.weight)).setText("Weight: " +
                String.valueOf(_user.getBodyWeight()) + _user.getWeightUnits());
        ((TextView)findViewById(R.id.bmr_label)).setText(String.valueOf(decimal
                .format(_calculationService.calculateBMR())) + " calories");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate_activity_view, menu);
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

    public void calculate(View view){
        EditText goalField = (EditText) findViewById(R.id.calories_to_burn);
        EditText runField = (EditText) findViewById(R.id.distance_running);
        EditText walkField = (EditText) findViewById(R.id.distance_walking);
        TextView progressText = (TextView) findViewById(R.id.progress_value);
        ToggleButton runUnitButton = (ToggleButton) findViewById(R.id.run_unit_button);
        ToggleButton walkUnitButton = (ToggleButton) findViewById(R.id.walk_unit_button);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        if(walkField.getText().length() == 0) {
           walkField.setText("0");
        }
        if(runField.getText().length() == 0) {
            runField.setText("0");
        }
        if(goalField.getText().length() == 0) {
            Toast.makeText(this, R.string.calorie_goal_required, Toast.LENGTH_LONG).show();
        } else {
            String runUnits = runUnitButton.isChecked() ? "km" : "mi";
            String walkUnits = walkUnitButton.isChecked() ? "km" : "mi";
            int goal = Integer.parseInt(goalField.getText().toString());
            double runDistance = Double.parseDouble(runField.getText().toString());
            double walkDistance = Double.parseDouble(walkField.getText().toString());
            progressBar.setMax(goal);

            double caloriesBurnedRunning = _calculationService
                    .calculateRunBurn(runUnits, runDistance);
            double caloriesBurnedWalking = _calculationService
                    .calculateWalkBurn(walkUnits, walkDistance);
            double totalCaloriesBurned = _calculationService.calculateBMR() + _calculationService
                    .calculateTotalBurn(caloriesBurnedRunning, caloriesBurnedWalking);
            if (totalCaloriesBurned < goal) {
                progressBar.setProgress((int) totalCaloriesBurned);
                progressText.setText(progressBar.getProgress() + "/" + progressBar.getMax());
            } else {
                progressBar.setProgress(progressBar.getMax());
                progressText.setText(progressBar.getProgress() + "/" + progressBar.getMax());
            }
        }
    }
}
