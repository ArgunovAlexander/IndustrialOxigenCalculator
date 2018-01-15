//find oxygen flow

package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import static java.lang.String.format;

import java.util.Locale;



public class Variant1 extends AppCompatActivity {

    TextView incrOxyPer=(TextView) findViewById(R.id.incrOxyPer);
    TextView decrOxyPer=(TextView) findViewById(R.id.decrOxyPer);
    TextView incrAirFlow=(TextView) findViewById(R.id.incrAirFlow);
    TextView decrAirFlow=(TextView) findViewById(R.id.decrAirFlow);
    EditText inputOxyConc= (EditText) findViewById(R.id.inputOxyConc);
    EditText inputAirFlow=(EditText) findViewById(R.id.inputAirFlow);
    TextView onCalcOxyFlow=(TextView) findViewById(R.id.onCalcOxyFlow);
    TextView outputData=(TextView) findViewById(R.id.outputData);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant1);
        incrOxyPer.setOnClickListener(new StepperInputListener(inputOxyConc, 0.1d, "%.1f"));
        decrOxyPer.setOnClickListener(new StepperInputListener(inputOxyConc, -0.1d, "%.1f"));
        incrAirFlow.setOnClickListener(new StepperInputListener(inputAirFlow, 5, "%.0f"));
        decrAirFlow.setOnClickListener(new StepperInputListener(inputAirFlow, -5, "%.0f"));

        onCalcOxyFlow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                double oxyInAir=getIntent().getDoubleExtra("oxyInAir",20.7);
                double oxyPurity=getIntent().getDoubleExtra("oxyPur",99.5);
                double oxyConc=Oxygen.setParam(inputOxyConc);
                double airFlow=Oxygen.setParam(inputAirFlow);
                Oxygen o=new Oxygen.Builder
                    .oxyPurity(oxyPurity);
                    .oxyInAir(oxyInAir);
                    .oxyConc(oxyConc);
                    .airFlow(airFlow);
                    .build();
                o.printParam(outputData, "Расход кислорода = %.1f", o.calcOxyFlow());
            }
        });
    }
    class StepperInputListener implements OnClickListener {
        EditText editText;
        double step;
        String format;
        StepperInputListener (EditText editText, double step, String format) {
            this.editText=editText;
            this.step=step;
            this.format=format;
        }
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
