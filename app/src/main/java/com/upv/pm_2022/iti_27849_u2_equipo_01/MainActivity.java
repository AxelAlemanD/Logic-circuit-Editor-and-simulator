package com.upv.pm_2022.iti_27849_u2_equipo_01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import com.github.mikephil.charting.data.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs.ClockDurationDialog;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs.OutputGraphDialog;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs.SettingsDialog;
import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.ClockControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.SwitchControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.AndGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NandGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NorGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NotGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.OrGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Services.GenerateGraphService;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Services.SimulationService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private int gate_id = 0;
    private int point_id = 0;
    public static int xAxiesGraph = -1;
    public static int sampleRate = 5;
    public Boolean is_running = false;
    private static ArrayList<Point> otherDoorPoints;
    public static ArrayList<Point> allPoints = new ArrayList<>();
    private static ArrayAdapter<Point> adapter;
    private Intent intentSimulationService;
    private Intent intentGenerateGraphService;
    public static OutputGraphDialog outputGraphDialog;
    private SettingsDialog settingsDialog;
    private ClockDurationDialog clockDurationDialog;
    private Figure gate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        // want fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((LinearLayout) findViewById(R.id.content)).addView(new DragAndDropView(this));

        settingsDialog = new SettingsDialog(this);
        clockDurationDialog = new ClockDurationDialog(this);
        outputGraphDialog = new OutputGraphDialog(this);
        outputGraphDialog.show();
        outputGraphDialog.hide();
    }

    public void addAndGate(View view){
        gate = new AndGate(gate_id++,400,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",517, 553, 10, gate));
        allPoints.add(new Point(point_id++, "B", "input",360, 530, 10, gate));
        allPoints.add(new Point(point_id++, "C", "input",360, 575, 10, gate));
        showMessageElementAdded();
    }

    public void addOrGate(View view){
        gate = new OrGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",917, 553, 10, gate));
        allPoints.add(new Point(point_id++, "B", "input",760, 505, 10, gate));
        allPoints.add(new Point(point_id++, "C", "input",760, 597, 10, gate));
        showMessageElementAdded();
    }

    public void addNotGate(View view){
        gate = new NotGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",890, 553, 10, gate));
        allPoints.add(new Point(point_id++, "B", "input",760, 553, 10, gate));
        showMessageElementAdded();
    }

    public void addNandGate(View view){
        gate = new NandGate(gate_id++,400,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",530, 553, 10, gate));
        allPoints.add(new Point(point_id++, "B", "input",360, 530, 10, gate));
        allPoints.add(new Point(point_id++, "C", "input",360, 575, 10, gate));
        showMessageElementAdded();
    }

    public void addNorGate(View view){
        gate = new NorGate(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",930, 553, 10, gate));
        allPoints.add(new Point(point_id++, "B", "input",760, 505, 10, gate));
        allPoints.add(new Point(point_id++, "C", "input",760, 597, 10, gate));
        showMessageElementAdded();
    }

    public void addSwitchControl(View view){
        gate = new SwitchControl(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",900, 553, 10, gate));
        showMessageElementAdded();
    }

    public void addClockControl(View view){
        gate = new ClockControl(gate_id++,800,500);
        DragAndDropView.figures.add(gate);
        allPoints.add(new Point(point_id++, "A", "output",915, 533, 10, gate));
        showDefineClockDurationDialog((ClockControl) gate);
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
            SimulationService.shouldContinue = true;
            GenerateGraphService.shouldContinue = true;
            intentSimulationService = new Intent(this, SimulationService.class);
            intentGenerateGraphService = new Intent(this, GenerateGraphService.class);
            startService(intentSimulationService);
            startService(intentGenerateGraphService);
        }
        else {
            Toast.makeText(context, "Stop simulation", Toast.LENGTH_SHORT).show();
            ((FloatingActionButton) findViewById(R.id.startSimulationBtn)).setImageResource(R.drawable.ic_baseline_play_arrow_24);

            SimulationService.shouldContinue = false;
            GenerateGraphService.shouldContinue = false;
        }
        is_running = !is_running;
        enableOrDisableButtons(is_running);
    }

    public void cleanDisplay(View view){
        Toast.makeText(context, "Clean display", Toast.LENGTH_SHORT).show();
        DragAndDropView.figures.clear();
        DragAndDropView.lines.clear();
        allPoints.clear();
        xAxiesGraph = -1;
        this.gate_id = 0;
        this.point_id = 0;
    }

    public void enableOrDisableButtons(Boolean status){
        ((FloatingActionButton) findViewById(R.id.clearDisplayBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addAndGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNandGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNorGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addNotGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addOrGateBtn)).setEnabled(!status);
        ((Button) findViewById(R.id.addSwitchControlBtn)).setEnabled(!status);
    }

    public void showOutputGraph(View view){
        outputGraphDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void selectInput(Point outputPoint){

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.select_point);

        ((EditText) dialog.findViewById(R.id.outputPoint)).setText(outputPoint.toString());
        dialog.findViewById(R.id.outputPoint).setEnabled(false);

        otherDoorPoints = new ArrayList<>();
        otherDoorPoints.addAll(Point.getOtherPoints(outputPoint));

        Spinner spPoints = dialog.findViewById(R.id.spPoints);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, otherDoorPoints);
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

    private void showDefineClockDurationDialog(ClockControl clockControl){
        clockDurationDialog.newClock = clockControl;
        clockDurationDialog.show();
    }
}