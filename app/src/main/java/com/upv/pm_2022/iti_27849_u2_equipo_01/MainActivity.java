package com.upv.pm_2022.iti_27849_u2_equipo_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.AndGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NandGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NorGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NotGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.OrGate;

public class MainActivity extends AppCompatActivity {

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // want fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout content = findViewById(R.id.content);
        content.addView(new DragAndDropView(this));

        findViewById(R.id.addAndGateBtn).setOnClickListener( view -> {
            addAndGate();
        });

        findViewById(R.id.addOrGateBtn).setOnClickListener( view -> {
            addOrGate();
        });

        findViewById(R.id.addNotGateBtn).setOnClickListener( view -> {
            addNotGate();
        });

        findViewById(R.id.addNandGateBtn).setOnClickListener( view -> {
            addNandGate();
        });

        findViewById(R.id.addNorGateBtn).setOnClickListener( view -> {
            addNorGate();
        });

        findViewById(R.id.startSimulation).setOnClickListener( view -> {
            Toast.makeText(getApplicationContext(), "Start simulation", Toast.LENGTH_SHORT).show();
        });
    }

    public void addAndGate(){
        DragAndDropView.figures.add(new AndGate(id++,400,500));
    }
    public void addOrGate(){
        DragAndDropView.figures.add(new OrGate(id++,800,500));
    }
    public void addNotGate(){
        DragAndDropView.figures.add(new NotGate(id++,800,500));
    }
    public void addNandGate(){
        DragAndDropView.figures.add(new NandGate(id++,400,500));
    }
    public void addNorGate(){
        DragAndDropView.figures.add(new NorGate(id++,800,500));
    }
}