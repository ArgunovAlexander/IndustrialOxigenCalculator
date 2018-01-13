//find oxygen flow

package com.argunov.industrialoxigencalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.Locale;

import static java.lang.String.format;


public class Variant1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant1);
        (TextView) findViewById(R.id.incrOxyPer).setOnClickListener(
            new StepperInputListener((EditText) findViewById(R.id.inputEnrichAirOxyConc),0.1d,"%.1f");
        (TextView) findViewById(R.id.decrOxyPer).setOnClickListener(
            new StepperInputListener((EditText) findViewById(R.id.inputEnrichAirOxyConc),-0.1d,"%.1f");
        (TextView) findViewById(R.id.incrAirFlow).setOnClickListener(
            new StepperInputListener((EditText) findViewById(R.id.inputBlastFurnaceAirFlow),5,"%.0f");
        (TextView) findViewById(R.id.decrAirFlow).setOnClickListener(
            new StepperInputListener((EditText) findViewById(R.id.inputBlastFurnaceAirFlow),-5,"%.0f");
     }      
     
       (TextView) findViewByID(R.id.onCalcOxyFlow).setOnClickListener(new OnClickListener() {
            @override    
           public void onClick(View view){
               Oxygen o=new Oxygen();
               o.printParam((TextView) findViewById(R.id.), "Расход кислорода = %.1f",o.calcOxyFlow());
              }
       });  
      
 class StepperInputListener implements OnClickListener {
    StepperInputListener (EditText editText, double step, String format) {}
    @override
    public void onClick(View view){
        incrementView();
        }
    private void incrementView() {
        //hideKeyboard();
        double value=Double.valueOf(editText.getText().toString());
        value+=step;
        editText.setText(format(Locale.US,format,value));
    }
    }
}
