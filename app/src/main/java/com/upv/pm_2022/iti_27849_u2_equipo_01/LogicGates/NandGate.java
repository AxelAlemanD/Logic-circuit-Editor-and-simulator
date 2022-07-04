package com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.upv.pm_2022.iti_27849_u2_equipo_01.Figure;

public class NandGate extends Figure {
    private int ancho;
    private int alto;
    private Paint paint = new Paint();

    public NandGate(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.ancho = 200;
        this.alto = 100;
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2.5f);
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
        path.moveTo(this.x, this.y);

        /**
         *  ---------------
         */
        path.lineTo(this.x+50, this.y);

        /**
         *
         *  ---------------
         *                |
         *                |
         */
        path.lineTo(this.x+50, this.y+50);

        /**
         *
         *  ---------------
         *                |
         *                |----
         */
        path.lineTo(this.x+120, this.y+50);
        path.lineTo(this.x+120, this.y+55);
        path.lineTo(this.x+50, this.y+55);

        /**
         * ---------------
         *                |
         *                |----
         *                |
         */
        path.lineTo(this.x+50, this.y+100);

        /**
         * ----------------
         *                |
         *                |----
         *                |
         *  ---------------
         */
        path.lineTo(this.x, this.y+100);

        /**
         * ----------------
         *                |
         *                |----
         *  |             |
         *  ---------------
         */
        path.lineTo(this.x, this.y+75);

        /**
         *     ----------------
         *                    |
         *                    |----
         *  ----|             |
         *      ---------------
         */
        path.lineTo(this.x-30, this.y+75);
        path.lineTo(this.x-30, this.y+80);
        path.lineTo(this.x, this.y+80);

        /**
         *     ----------------
         *                    |
         *      |             |----
         *  ----|             |
         *      ---------------
         */
        path.lineTo(this.x, this.y+25);

        /**
         *     ----------------
         *  ----|             |
         *      |             |----
         *  ----|             |
         *      ---------------
         */
        path.lineTo(this.x-30, this.y+25);
        path.lineTo(this.x-30, this.y+30);
        path.lineTo(this.x, this.y+30);

        /**
         *      ---------------
         *  ----|             |
         *      |             |----
         *  ----|             |
         *      ---------------
         */
        path.lineTo(this.x, this.y);

        path.moveTo(this.x+50, this.y);
        // Draw curve
        path.cubicTo(this.x+75, this.y,
                this.x+125, this.y+65,
                this.x+50,this.y+100);

        path.addCircle(this.x+95, this.y+52, 10, Path.Direction.CW);

        canvas.drawPath(path, paint);
    }

    /**
     * Check that you are clicking inside the figure
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     * @return id
     */
    public int onDown(int touchX, int touchY){
        if(touchX > this.x && touchX < this.x+this.ancho &&
                touchY > this.y && touchY < this.y+this.alto)
            return this.id;
        return -1;
    }

    /**
     * Modify the position of the figure according to the position of the finger
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     */
    public void onMove(int touchX, int touchY){
        this.x = touchX - this.ancho/2;
        this.y = touchY - this.alto/2;
    }
}

