package com.example.andres.activitymonitor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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

    private CalculationService _calculationService;
    private Resources _resource;
    private User _user;
    private int _goal;
    private EditText _goalField;
    private TextView _progressText;
    private ProgressBar _progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_activity_view);
        Intent i = getIntent();

        NumberFormat decimal = DecimalFormat.getNumberInstance();
        NumberFormat percent = DecimalFormat.getPercentInstance();
        _resource = getResources();
        _user = (User)i.getSerializableExtra(InformationInputView.EXTRA_USER);
        _calculationService = new CalculationService(_user);
        _goalField = (EditText) findViewById(R.id.calories_to_burn);
        _goal = Integer.parseInt(_goalField.getText().toString());
        _progressText = (TextView) findViewById(R.id.progress_value);
        _progressText.setText((int)_calculationService.calculateBMR() + "/" + _goal + "\t\t" +
                percent.format(_calculationService.calculateBMR() / _goal));
        _progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        _progressBar.setMax(_goal);
        if (_calculationService.calculateBMR() < _goal) {
            _progressBar.setProgress((int) _calculationService.calculateBMR());
            _progressText.setText(_progressBar.getProgress() + " / " + _progressBar.getMax() +
                    " " + percent.format(_calculationService.calculateBMR() / _goal));
        } else {
            _progressBar.setProgress(_progressBar.getMax());
            _progressText.setText(_progressBar.getProgress() + " / " + _progressBar.getMax() +
                    " " + percent.format(_calculationService.calculateBMR() / _goal));
            Toast.makeText(this, R.string.goal_complete, Toast.LENGTH_LONG).show();
        }


        ((TextView) findViewById(R.id.username)).setText(_user.getName());
        ((TextView)findViewById(R.id.age)).setText(_resource.getString(R.string.age) + " " +
                String.valueOf(_user.getAge()));
        ((TextView)findViewById(R.id.height)).setText(_resource.getString(R.string.height) + " " +
                String.valueOf(_user.getHeight()) + _user.getHeightUnits());
        ((TextView)findViewById(R.id.gender)).setText(_resource.getString(R.string.gender) + " " +
                _user.getGender());
        ((TextView)findViewById(R.id.weight)).setText(_resource.getString(R.string.weight) + " " +
                String.valueOf(_user.getBodyWeight()) + _user.getWeightUnits());
        ((TextView)findViewById(R.id.bmr_label)).setText(String.valueOf(decimal.format(
                _calculationService.calculateBMR())) + " " + _resource.getString(R.string.calories));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate_activity_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
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

    /**
     *
     * @param view
     */
    public void calculate(final View view){
        EditText runField = (EditText) findViewById(R.id.distance_running);
        EditText walkField = (EditText) findViewById(R.id.distance_walking);
        ToggleButton runUnitButton = (ToggleButton) findViewById(R.id.run_unit_button);
        ToggleButton walkUnitButton = (ToggleButton) findViewById(R.id.walk_unit_button);


        if(walkField.getText().length() == 0) {
           walkField.setText("0");
        }
        if(runField.getText().length() == 0) {
            runField.setText("0");
        }
        if(_goalField.getText().length() == 0) {
            Toast.makeText(this, R.string.calorie_goal_required, Toast.LENGTH_LONG).show();
        } else {
            String runUnits = runUnitButton.isChecked() ? "km" : "mi";
            String walkUnits = walkUnitButton.isChecked() ? "km" : "mi";

            double runDistance = Double.parseDouble(runField.getText().toString());
            double walkDistance = Double.parseDouble(walkField.getText().toString());


            double caloriesBurnedRunning = _calculationService
                    .calculateRunBurn(runUnits, runDistance);
            double caloriesBurnedWalking = _calculationService
                    .calculateWalkBurn(walkUnits, walkDistance);
            double totalCaloriesBurned = _calculationService
                    .calculateTotalBurn(caloriesBurnedRunning, caloriesBurnedWalking);
            double burnedPercent = totalCaloriesBurned/_goal;
            NumberFormat percent = DecimalFormat.getPercentInstance();
            if (totalCaloriesBurned < _goal) {
                _progressBar.setProgress((int) totalCaloriesBurned);
                _progressText.setText(_progressBar.getProgress() + " / " + _progressBar.getMax() +
                        " " + percent.format(burnedPercent));
                Toast.makeText(this, R.string.lazy_bum, Toast.LENGTH_LONG).show();
            } else {
                _progressBar.setProgress(_progressBar.getMax());
                _progressText.setText(totalCaloriesBurned + " / " + _progressBar.getMax() +
                        " " + percent.format(burnedPercent));
                Toast.makeText(this, R.string.goal_complete, Toast.LENGTH_LONG).show();
            }
        }
    }
}
