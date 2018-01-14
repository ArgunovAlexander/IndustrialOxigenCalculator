package com.argunov.industrialoxigencalculator;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import android.view.View;

import static java.lang.String.format;


public class Oxygen {
    //double oxyInAir=getIntent().getDoubleExtra("oxyInAir",20.7);
    //double oxyPurity=getIntent().getDoubleExtra("oxyPur",99.5);
    private double oxyConc;
    private double oxyFlow;
    private double oxyInAir;
    private double oxyPurity;
    private double airFlow;
    private double furnaceOxyConc;
    
    public static class Builder{
        private double oxyConc=0;    
        private double oxyFlow=0;    
        private double oxyInAir=0;    
        private double oxyPurity=0;    
        private double airFlow=0;    
        private double furnaceOxyConc=0;
        public Oxygen.Builder oxyConc(double oxyConc) {
            this.oxyConc=oxyConc;
            return this;
        }
        public Oxygen.Builder oxyFlow(double oxyFlow) {
            this.oxyFlow=oxyFlow;
            return this;
        }
        public Oxygenr build() {
            return new Oxygen(this);
        }
    }
    
    private Oxygen() {
        super();
    }
    private Oxygen(Oxygen.Builder builder){
        airFlow=builder.airFlow;
        oxyConc=builder.oxyConc;
        oxyFlow=builder.oxyFlow;
        oxyInAir=builder.oxyInAir;
        oxyPurity=builder.oxyPurity;
        furnaceOxyConc=builder.furnaceOxyConc;
    }

    double calcOxyFlow(EditText oxyConcEtTxt, EditText airFlowEtTxt) {
        oxyConc=setParam(oxyConcEtTxt);
        airFlow=setParam(airFlowEtTxt);
        return (airFlow*(oxyConc-oxyInAir))/(oxyPurity-oxyInAir);
    }

    double calcOxyConc(EditText oxyFlowEtTxt, EditText airFlowEtTxt) {
        oxyFlow=setParam(oxyFlowEtTxt);
        airFlow=setParam(airFlowEtTxt);
        double oxygenInAir=airFlow*oxyInAir/100;
        double oxygenInOxygen=oxyFlow*oxyPurity/100;
        return (oxygenInAir+oxygenInOxygen)/(airFlow+oxyFlow)*100;
    }

    double calcAirDissipation (EditText furnaceOxyConcEtTxt) {
        furnaceOxyConc=setParam(furnaceOxyConcEtTxt);
        return oxyFlow*(oxyPurity-oxyInAir)/(furnaceOxyConc-oxyInAir)-airFlow;
    }

    double setParam(EditText editText) {
        return isEmpty(editText) ? 0 :Double.valueOf(editText.getText().toString()) ;
    }

    boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            dispToast();
            return true;
        }
    }

    void dispToast() {
        //Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG ).show();
    }

    void printParam(TextView textView, String formattedMessage, double param){
        hideKeyboard();
        String message=format(Locale.US,formattedMessage, param);
        textView.setText(message);
    }

    void hideKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
    }
}
