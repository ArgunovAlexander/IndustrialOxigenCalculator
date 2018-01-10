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

        TextView incrOxyPercent=findViewById(R.id.incrOxyPer);
        incrOxyPercent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputEnrichAirOxyConc),0.1d,"%.1f");
            }
        });


        TextView decrOxyPercent=findViewById(R.id.decrOxyPer);
        decrOxyPercent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputEnrichAirOxyConc),-0.1d,"%.1f");
            }
        });


        TextView incrAirFlow=findViewById(R.id.incrAirFlow);
        incrAirFlow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputBlastFurnaceAirFlow),5,"%.0f");
            }
        });


        TextView decrAirFlow=findViewById(R.id.decrAirFlow);
        decrAirFlow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputBlastFurnaceAirFlow),-5,"%.0f");
            }
        });
    }

    public void onCalcOxyFlow(View view) {
        double air=getAirFlow();
        double oxyConc=getOxyConc();
        double oxyPur=getIntent().getDoubleExtra("oxyPur",99.5);
        double oxyInAir=getIntent().getDoubleExtra("oxyInAir",20.7);
        double oxyFlow=CalcOxy.calculateOxygenFlow(air,oxyConc,oxyInAir,oxyPur);
        printOxyFlow(oxyFlow);
    }

    private void printOxyFlow(double oxygenFlow){
        String message=format(Locale.US,getString(R.string.oxy_flow)+"%.1f",oxygenFlow);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
    }

    private double getOxyConc() {
        hideKeyboard();
        EditText oxyConc = findViewById(R.id.inputEnrichAirOxyConc);
        return isEmpty(oxyConc) ? 0 :Double.valueOf(oxyConc.getText().toString()) ;
    }

    private double getAirFlow() {
        EditText FurnaceAirFlow=findViewById(R.id.inputBlastFurnaceAirFlow);
        return isEmpty(FurnaceAirFlow)? 0 :Double.valueOf(FurnaceAirFlow.getText().toString());
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            dispToast();
            return true;
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void dispToast() {
        Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG ).show();
    }

    private void increment(EditText editText, double step, String format) {
        hideKeyboard();
        double value=Double.valueOf(editText.getText().toString());
        value+=step;
        editText.setText(format(Locale.US,format,value));
    }
}

