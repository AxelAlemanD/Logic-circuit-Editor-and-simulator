package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;

public class Point extends Figure {
    private Figure gate;
    private String type;
    private int radius;
    private int diffX;
    private int diffY;
    public Point connectedPoint;
    public ConnectionLine connectedLine;
    public Boolean status = false;

    public Point(int pointId, String name, String type, int x, int y, int radius, Figure gate) {
        this.id = pointId;
        this.name = name;
        this.type = type;
        this.xAxies = x;
        this.yAxies = y;
        this.radius = radius;
        this.gate = gate;
        this.diffX = gate.xAxies - this.xAxies;
        this.diffY = gate.xAxies - this.yAxies;

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Draw the figure
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawCircle(this.xAxies, this.yAxies,this.radius,paint);
        canvas.drawText(this.name, this.xAxies-4, this.yAxies+22,paint);
    }

    /**
     * Check that you are clicking inside the figure
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     * @return id
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int onDown(int touchX, int touchY){
        if(this.type.equalsIgnoreCase("output")
                && (Math.pow(this.radius, 2) + 100) > (Math.pow(touchX - this.xAxies, 2) + Math.pow(touchY - this.yAxies, 2)) )
            MainActivity.selectInput(this);
        return -1;
    }

    /**
     * Modify the position of the point according to the position of the bind gate
     * @param xAxiesGate position of the bind gate on the X axis
     * @param yAxiesGate position of the bind gate on the Y axis
     */
    public void onMoveGate(int xAxiesGate, int yAxiesGate){
        this.xAxies = xAxiesGate - this.diffX;
        this.yAxies = yAxiesGate - this.diffY;
    }

    public Figure getGate(){ return this.gate; }
    public String getType(){ return this.type; }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Point> getInputPoints(){
        return MainActivity.allPoints.stream().filter(point ->
                point.getType().equalsIgnoreCase("input")
        ).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Point> getOutputPoints(){
        return MainActivity.allPoints.stream().filter(point ->
                point.getType().equalsIgnoreCase("output")
        ).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Point> getOtherPoints(Point outputPoint){
        return MainActivity.allPoints.stream().filter(point ->
                validatePoint(outputPoint, point)
        ).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Point> getGatePoints(Figure gate){
        return MainActivity.allPoints.stream().filter(point ->
                point.gate == gate
        ).collect(Collectors.toList());
    }

    private static boolean validatePoint(Point outputPoint, Point point){
        return point.getGate().id != outputPoint.getGate().id // Validate that it does not belong to the same door
                && point.getType().equalsIgnoreCase("input") // Validate that it is of type Input
                && point.connectedPoint == null; // Validate that it is not assigned
    }

    @Override
    public String toString(){ return gate.name+": Point "+this.name; }
}

