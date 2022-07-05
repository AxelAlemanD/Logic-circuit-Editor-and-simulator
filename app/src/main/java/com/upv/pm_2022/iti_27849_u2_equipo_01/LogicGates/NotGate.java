package com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.upv.pm_2022.iti_27849_u2_equipo_01.Figure;

public class NotGate extends Figure {

    public NotGate(int id, int x, int y) {
        this.id = id;
        this.xAxies = x;
        this.yAxies = y;

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Draw the figure
     * @param canvas
     */
    public void draw(Canvas canvas){
        Path path = new Path();
        /**
         *  -
         */
        path.moveTo(this.xAxies, this.yAxies);

        // Diagonal
        path.lineTo(this.xAxies +50, this.yAxies +50);
        // Linea Salida
        path.lineTo(this.xAxies +80, this.yAxies +50);
        path.lineTo(this.xAxies +80, this.yAxies +55);
        path.lineTo(this.xAxies +50, this.yAxies +55);
        // Diagonal inferior
        path.lineTo(this.xAxies, this.yAxies +100);

        // Linea entrada inferior
        path.lineTo(this.xAxies, this.yAxies +75);
        path.lineTo(this.xAxies -30, this.yAxies +75);
        path.lineTo(this.xAxies -30, this.yAxies +80);
        path.lineTo(this.xAxies, this.yAxies +80);

        // Linea entrada superior
        path.lineTo(this.xAxies, this.yAxies +25);
        path.lineTo(this.xAxies -30, this.yAxies +25);
        path.lineTo(this.xAxies -30, this.yAxies +30);
        path.lineTo(this.xAxies, this.yAxies +30);

        path.lineTo(this.xAxies, this.yAxies);

        path.addCircle(this.xAxies +55, this.yAxies +52, 10, Path.Direction.CW);

        canvas.drawPath(path, paint);
    }

    /**
     * Check that you are clicking inside the figure
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     * @return id
     */
    public int onDown(int touchX, int touchY){
        if(touchX > this.xAxies && touchX < this.xAxies +this.weight &&
                touchY > this.yAxies && touchY < this.yAxies +this.height)
            return this.id;
        return -1;
    }

    /**
     * Modify the position of the figure according to the position of the finger
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     */
    public void onMove(int touchX, int touchY){
        this.xAxies = touchX - this.weight /2;
        this.yAxies = touchY - this.height /2;
    }
}

