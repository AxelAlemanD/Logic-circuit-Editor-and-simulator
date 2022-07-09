package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Point extends Figure {
    private int radius;
    private int gateId;
    private int diffX;
    private int diffY;

    public Point(int gateId, int x, int y, int radius, int pointId, String name) {
        this.gateId = gateId;
        this.id = pointId;
        this.xAxies = x;
        this.yAxies = y;
        this.name = name;
        this.diffX = DragAndDropView.figures.get(this.gateId).xAxies - this.xAxies;
        this.diffY = DragAndDropView.figures.get(this.gateId).xAxies - this.yAxies;
        this.radius = radius;

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
    public int onDown(int touchX, int touchY){
        if((Math.pow(this.radius, 2) + 100) > (Math.pow(touchX - this.xAxies, 2) + Math.pow(touchY - this.yAxies, 2))) {
            MainActivity.selectInput(this);
            return this.id;
        }
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

    /**
     * Modify the position of the figure according to the position of the finger
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     */
//    public void onMove(int touchX, int touchY, Canvas canvas){
//        canvas.drawLine(this.xAxies, this.yAxies, touchX, touchY, paint);
//        MainActivity.selectInput(this);
//    }

    @Override
    public String toString(){
        return "ID: "+this.id+"\n"+
                "GateID: "+this.gateId+"\n"+
                "Radius: "+this.radius+"\n"+
                "xAxies: "+this.xAxies +"\n"+
                "yAxies: "+this.yAxies;
    }
}

