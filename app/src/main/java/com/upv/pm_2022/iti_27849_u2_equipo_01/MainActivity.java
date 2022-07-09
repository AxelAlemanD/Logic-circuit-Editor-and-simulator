package com.upv.pm_2022.iti_27849_u2_equipo_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.SwitchControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.AndGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NandGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NorGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NotGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.OrGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.OutputControls.OutputControl;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private int gate_id = 0;
    private int point_id = 0;
    Figure gate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        // want fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((LinearLayout) findViewById(R.id.content)).addView(new DragAndDropView(this));
    }

    public void addAndGate(View view){
        gate = new AndGate(gate_id++,400,500);
        DragAndDropView.figures.add(gate);
        gate.addPoint(new Point(point_id++, "A", "output",517, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "input",360, 530, 10, gate));
        gate.addPoint(new Point(point_id++, "C", "input",360, 575, 10, gate));
        showMessageElementAdded();
    }

    public void addOrGate(View view){
        gate = new OrGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        gate.addPoint(new Point(point_id++, "A", "input",917, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "output",760, 505, 10, gate));
        gate.addPoint(new Point(point_id++, "C", "output",760, 597, 10, gate));
        showMessageElementAdded();
    }

    public void addNotGate(View view){
        gate = new NotGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        gate.addPoint(new Point(point_id++, "A", "output",890, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "input",760, 553, 10, gate));
        showMessageElementAdded();
    }

    public void addNandGate(View view){
        gate = new NandGate(gate_id++,400,500);
        DragAndDropView.figures.add(gate);
        gate.addPoint(new Point(point_id++, "A", "output",530, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "input",360, 530, 10, gate));
        gate.addPoint(new Point(point_id++, "C", "input",360, 575, 10, gate));
        showMessageElementAdded();
    }

    public void addNorGate(View view){
        gate = new NorGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        gate.addPoint(new Point(point_id++, "A", "output",930, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "input",760, 505, 10, gate));
        gate.addPoint(new Point(point_id++, "C", "input",760, 597, 10, gate));
        showMessageElementAdded();
    }

    public void addSwitchControl(View view){
        gate = new SwitchControl(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        showMessageElementAdded();
    }

    public void addOutputControl(View view){
        gate = new OutputControl(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        showMessageElementAdded();
    }

    public void showMessageElementAdded(){
        Toast.makeText(getApplicationContext(),
                this.gate.name + " added",
                Toast.LENGTH_SHORT).show();
    }

    public void startSimulation(View view){
        Toast.makeText(getApplicationContext(), "Start simulation", Toast.LENGTH_SHORT).show();
    }

    public static void selectInput(Point point){
        Toast.makeText(context, point.toString(), Toast.LENGTH_SHORT).show();
    }
}