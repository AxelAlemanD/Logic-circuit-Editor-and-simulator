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

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        // want fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout content = findViewById(R.id.content);
        content.addView(new DragAndDropView(this));
    }

    public void addAndGate(View view){
        int gateId = id++;
        DragAndDropView.figures.add(new AndGate(gateId,400,500));

        Point outputPoint = new Point(gateId,517, 553, 10, id++);
        Point inputPointA = new Point(gateId,360, 530, 10, id++);
        Point inputPointB = new Point(gateId,360, 575, 10, id++);

        DragAndDropView.figures.add(outputPoint);
        DragAndDropView.figures.add(inputPointA);
        DragAndDropView.figures.add(inputPointB);
    }

    public void addOrGate(View view){
        int gateId = id++;

        DragAndDropView.figures.add(new OrGate(gateId,800,500));

        Point outputPoint = new Point(gateId,917, 553, 10, id++);
        Point inputPointA = new Point(gateId,760, 505, 10, id++);
        Point inputPointB = new Point(gateId,760, 597, 10, id++);

        DragAndDropView.figures.add(outputPoint);
        DragAndDropView.figures.add(inputPointA);
        DragAndDropView.figures.add(inputPointB);
    }

    public void addNotGate(View view){
        int gateId = id++;

        DragAndDropView.figures.add(new NotGate(gateId,800,500));

        Point outputPoint = new Point(gateId,890, 553, 10, id++);
        Point inputPointA = new Point(gateId,760, 553, 10, id++);

        DragAndDropView.figures.add(outputPoint);
        DragAndDropView.figures.add(inputPointA);
    }

    public void addNandGate(View view){
        int gateId = id++;

        DragAndDropView.figures.add(new NandGate(gateId,400,500));

        Point outputPoint = new Point(gateId,530, 553, 10, id++);
        Point inputPointA = new Point(gateId,360, 530, 10, id++);
        Point inputPointB = new Point(gateId,360, 575, 10, id++);

        DragAndDropView.figures.add(outputPoint);
        DragAndDropView.figures.add(inputPointA);
        DragAndDropView.figures.add(inputPointB);
    }

    public void addNorGate(View view){
        int gateId = id++;

        DragAndDropView.figures.add(new NorGate(gateId,800,500));

        Point outputPoint = new Point(gateId,930, 553, 10, id++);
        Point inputPointA = new Point(gateId,760, 505, 10, id++);
        Point inputPointB = new Point(gateId,760, 597, 10, id++);

        DragAndDropView.figures.add(outputPoint);
        DragAndDropView.figures.add(inputPointA);
        DragAndDropView.figures.add(inputPointB);
    }

    public void addSwitchControl(View view){
        int gateId = id++;

        DragAndDropView.figures.add(new SwitchControl(gateId,800,500));
    }

    public void startSimulation(View view){
        Toast.makeText(getApplicationContext(), "Start simulation", Toast.LENGTH_SHORT).show();
    }

    public static void selectInput(Point point){
        Toast.makeText(context, point.toString(), Toast.LENGTH_SHORT).show();
    }
}