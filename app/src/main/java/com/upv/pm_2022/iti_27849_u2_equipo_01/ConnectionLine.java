package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ConnectionLine extends Figure {
    public Point pointA;
    public Point pointB;

    public ConnectionLine(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
    }

    /**
     * Draw the figure
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawLine(this.pointA.xAxies, this.pointA.yAxies, this.pointB.xAxies, this.pointB.yAxies, paint);
    }
}

