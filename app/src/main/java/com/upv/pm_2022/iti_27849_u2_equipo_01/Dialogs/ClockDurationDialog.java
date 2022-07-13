package com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.ClockControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.R;

public class ClockDurationDialog extends Dialog {
    public Activity activity;
    private EditText clockDurationEt;
    public ClockControl newClock;

    public ClockDurationDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setContentView(R.layout.clock_duration);
        setTitle("Clock duration");
        clockDurationEt = findViewById(R.id.clockDuration);
        clockDurationEt.setText(String.valueOf(this.newClock.duration));

        findViewById(R.id.saveBtn).setOnClickListener(v -> {
            if(is_valid()) {
                this.newClock.duration = Integer.parseInt(clockDurationEt.getText().toString());
                dismiss();
            }
            else
                clockDurationEt.setError("The value must be greater than 0");
        });

        findViewById(R.id.cancelBtn).setOnClickListener(v -> {
            this.newClock.duration = 2;
            dismiss();
        });
    }

    private boolean is_valid(){
        if(clockDurationEt.getText().toString().equals(""))
            return false;
        else return Integer.parseInt(clockDurationEt.getText().toString()) > 0;
    }
}
