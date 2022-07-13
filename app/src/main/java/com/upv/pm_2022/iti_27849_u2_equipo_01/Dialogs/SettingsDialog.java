package com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.upv.pm_2022.iti_27849_u2_equipo_01.MainActivity;
import com.upv.pm_2022.iti_27849_u2_equipo_01.R;

public class SettingsDialog extends Dialog {
    public Activity activity;
    private EditText sampleRateEt;

    public SettingsDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setContentView(R.layout.settings);
        setTitle("Settings");
        sampleRateEt = findViewById(R.id.sampleRate);
        sampleRateEt.setText(String.valueOf(MainActivity.sampleRate));

        findViewById(R.id.saveBtn).setOnClickListener(v -> {
            if(is_valid()) {
                MainActivity.sampleRate = Integer.parseInt(sampleRateEt.getText().toString());
                dismiss();
            }
            else
                sampleRateEt.setError("The value must be greater than 0");
        });

        findViewById(R.id.cancelBtn).setOnClickListener(v ->
                dismiss()
        );
    }

    private boolean is_valid(){
        if(sampleRateEt.getText().toString().equals(""))
            return false;
        else return Integer.parseInt(sampleRateEt.getText().toString()) > 0;
    }
}
