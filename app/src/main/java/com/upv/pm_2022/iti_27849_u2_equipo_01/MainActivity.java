package com.upv.pm_2022.iti_27849_u2_equipo_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    public static int xAxiesGraph = -1;
    public static int sampleRate = 5;
    public static Boolean is_running = false;
    private Intent intentSimulationService;
    private Intent intentGenerateGraphService;
    private static ArrayList<Point> points;
    public static ArrayList<Entry> outputValues;
    OutputGraphDialog outputGraphDialog;
    SettingsDialog settingsDialog;

    Figure gate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        // want fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((LinearLayout) findViewById(R.id.content)).addView(new DragAndDropView(this));

        outputValues = new ArrayList<>();

        settingsDialog = new SettingsDialog(this);
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
        gate.addPoint(new Point(point_id++, "A", "output",900, 553, 10, gate));
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
        if(!is_running){
            Toast.makeText(context, "Start simulation", Toast.LENGTH_SHORT).show();
            ((FloatingActionButton) findViewById(R.id.startSimulationBtn)).setImageResource(R.drawable.ic_baseline_pause_24);
            intentSimulationService = new Intent(this, SimulationService.class);
            startService(intentSimulationService);
            intentGenerateGraphService = new Intent(this, GenerateGraphService.class);
            startService(intentGenerateGraphService);
            is_running = true;
            enableOrDisableButtons(is_running);
        }
        else {
            Toast.makeText(context, "Stop simulation", Toast.LENGTH_SHORT).show();
            ((FloatingActionButton) findViewById(R.id.startSimulationBtn)).setImageResource(R.drawable.ic_baseline_play_arrow_24);
            is_running = false;
            enableOrDisableButtons(is_running);
        }
    }

    public void cleanDisplay(View view){
        Toast.makeText(context, "Clean display", Toast.LENGTH_SHORT).show();
        DragAndDropView.figures.clear();
        DragAndDropView.lines.clear();
        outputValues.clear();
        xAxiesGraph = -1;
        this.gate_id = 0;
        this.point_id = 0;
    }

    public void enableOrDisableButtons(Boolean status){
        ((FloatingActionButton) findViewById(R.id.clearDisplayBtn)).setEnabled(!status);
//        ((FloatingActionButton) findViewById(R.id.showGraphBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addAndGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNandGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNorGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNotGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addOrGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addSwitchControlBtn)).setEnabled(!status);
    }

    public void showOutputGraph(View view){
        outputGraphDialog = new OutputGraphDialog(this);
        outputGraphDialog.show();
    }

    public static void selectInput(Point outputPoint){
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.select_point);

        ((EditText) dialog.findViewById(R.id.outputPoint)).setText(outputPoint.toString());
        dialog.findViewById(R.id.outputPoint).setEnabled(false);

        points = new ArrayList<Point>();
        // Get all points
        for (Figure figure: DragAndDropView.figures){
            for (Figure point: figure.getPoints()) {
                // Validate points
                if( ((Point) point).getGate().id != outputPoint.getGate().id // Validate that it does not belong to the same door
                        && ((Point) point).getType().equalsIgnoreCase("input") // Validate that it is of type Input
                        && ((Point) point).connectedPoint == null) // Validate that it is not assigned
                    points.add((Point) point);
            }
        }

        Spinner spPoints = dialog.findViewById(R.id.spPoints);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, points);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPoints.setAdapter(adapter);

        dialog.show();

        dialog.findViewById(R.id.saveBtn).setOnClickListener(v -> {
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

            dialog.dismiss();
        });

        dialog.findViewById(R.id.cancelBtn).setOnClickListener(v ->
            dialog.dismiss()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                showSettings();
                return true;
            case R.id.about:
                showDialogAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings(){
        settingsDialog.show();
    }

    private void showDialogAbout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.about, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}