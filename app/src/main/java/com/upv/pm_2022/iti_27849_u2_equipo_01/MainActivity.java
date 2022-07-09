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
    Point outputPoint;
    Point inputPointA;
    Point inputPointB;

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

        outputPoint = new Point(gate,517, 553, 10, point_id++, "A");
        inputPointA = new Point(gate,360, 530, 10, point_id++, "B");
        inputPointB = new Point(gate,360, 575, 10, point_id++, "C");

        gate.addPoint(outputPoint);
        gate.addPoint(inputPointA);
        gate.addPoint(inputPointB);

        Toast.makeText(context, "Puerta AND agregada", Toast.LENGTH_SHORT).show();
    }

    public void addOrGate(View view){
        gate = new OrGate(gate_id++,800,500);

        DragAndDropView.figures.add(gate);

        outputPoint = new Point(gate,917, 553, 10, point_id++, "A");
        inputPointA = new Point(gate,760, 505, 10, point_id++, "B");
        inputPointB = new Point(gate,760, 597, 10, point_id++, "C");

        gate.addPoint(outputPoint);
        gate.addPoint(inputPointA);
        gate.addPoint(inputPointB);

        Toast.makeText(context, "Puerta OR agregada", Toast.LENGTH_SHORT).show();
    }

    public void addNotGate(View view){
        gate = new NotGate(gate_id++,800,500);

        DragAndDropView.figures.add(gate);

        outputPoint = new Point(gate,890, 553, 10, point_id++, "A");
        inputPointA = new Point(gate,760, 553, 10, point_id++, "B");

        gate.addPoint(outputPoint);
        gate.addPoint(inputPointA);

        Toast.makeText(context, "Puerta NOT agregada", Toast.LENGTH_SHORT).show();
    }

    public void addNandGate(View view){
        gate = new NandGate(gate_id++,400,500);

        DragAndDropView.figures.add(gate);

        outputPoint = new Point(gate,530, 553, 10, point_id++, "A");
        inputPointA = new Point(gate,360, 530, 10, point_id++, "B");
        inputPointB = new Point(gate,360, 575, 10, point_id++, "C");

        gate.addPoint(outputPoint);
        gate.addPoint(inputPointA);
        gate.addPoint(inputPointB);

        Toast.makeText(context, "Puerta NAND agregada", Toast.LENGTH_SHORT).show();
    }

    public void addNorGate(View view){
        gate = new NorGate(gate_id++,800,500);

        DragAndDropView.figures.add(gate);

        outputPoint = new Point(gate,930, 553, 10, point_id++, "A");
        inputPointA = new Point(gate,760, 505, 10, point_id++, "B");
        inputPointB = new Point(gate,760, 597, 10, point_id++, "C");

        gate.addPoint(outputPoint);
        gate.addPoint(inputPointA);
        gate.addPoint(inputPointB);

        Toast.makeText(context, "Puerta NOR agregada", Toast.LENGTH_SHORT).show();
    }

    public void addSwitchControl(View view){
        gate = new SwitchControl(gate_id++,800,500);

        DragAndDropView.figures.add(gate);

        Toast.makeText(context, "Switch agregado", Toast.LENGTH_SHORT).show();
    }

    public void addOutputControl(View view){
        gate = new OutputControl(gate_id++,800,500);

        DragAndDropView.figures.add(gate);

        Toast.makeText(context, "Control de salida agregado", Toast.LENGTH_SHORT).show();
    }

    public void startSimulation(View view){
        Toast.makeText(getApplicationContext(), "Start simulation", Toast.LENGTH_SHORT).show();
    }

    public static void selectInput(Point point){
        Toast.makeText(context, point.toString(), Toast.LENGTH_SHORT).show();
    }
}