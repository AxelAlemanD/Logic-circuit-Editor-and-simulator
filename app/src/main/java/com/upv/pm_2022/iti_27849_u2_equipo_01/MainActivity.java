package com.upv.pm_2022.iti_27849_u2_equipo_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.SwitchControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.AndGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NandGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NorGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NotGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.OrGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.OutputControls.OutputControl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private static ArrayAdapter<Point> adapter;
    private int gate_id = 0;
    private int point_id = 0;
    Figure gate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
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
        gate.addPoint(new Point(point_id++, "A", "output",917, 553, 10, gate));
        gate.addPoint(new Point(point_id++, "B", "input",760, 505, 10, gate));
        gate.addPoint(new Point(point_id++, "C", "input",760, 597, 10, gate));
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

    public static void selectInput(Point outputPoint){
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.select_point);

        ((EditText) dialog.findViewById(R.id.outputPoint)).setText(outputPoint.toString());
        ((EditText) dialog.findViewById(R.id.outputPoint)).setEnabled(false);

        ArrayList<Point> points = new ArrayList<Point>();
        for (Figure figure: DragAndDropView.figures){
            for (Figure point: figure.getPoints()) {
                if( ((Point) point).getGate().id != outputPoint.getGate().id
                        && ((Point) point).getType().equalsIgnoreCase("input")
                        && ((Point) point).connectedPoint == null)
                    points.add((Point) point);
            }
        }

        Spinner spPoints = (Spinner) dialog.findViewById(R.id.spPoints);
        adapter = new ArrayAdapter<Point>(context, android.R.layout.simple_spinner_item, points);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPoints.setAdapter(adapter);

        dialog.show();

        ((Button) dialog.findViewById(R.id.saveBtn)).setOnClickListener(v -> {

            // If it has an assigned line it is removed
            if(outputPoint.connectedLine != null){
                outputPoint.connectedLine.pointA.connectedPoint = null;
                outputPoint.connectedLine.pointB.connectedPoint = null;
                DragAndDropView.lines.remove(outputPoint.connectedLine);
            }

            outputPoint.connectedPoint = (Point) spPoints.getSelectedItem();
            ((Point) spPoints.getSelectedItem()).connectedPoint = outputPoint;

            ConnectionLine conectionLine = new ConnectionLine( outputPoint, ((Point) spPoints.getSelectedItem()) );
            outputPoint.connectedLine = conectionLine;
            ((Point) spPoints.getSelectedItem()).connectedLine = conectionLine;

            DragAndDropView.lines.add(conectionLine);

            dialog.hide();
        });

        ((Button) dialog.findViewById(R.id.cancelBtn)).setOnClickListener(v ->
            dialog.hide()
        );
    }
}