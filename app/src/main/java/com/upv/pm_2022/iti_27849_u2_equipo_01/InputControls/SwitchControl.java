package com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.upv.pm_2022.iti_27849_u2_equipo_01.Figure;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Point;

import java.util.ArrayList;
import java.util.List;

public class SwitchControl extends Figure {

    private Boolean is_active = false;

    public SwitchControl(int id, int x, int y) {
        this.id = id;
        this.xAxies = x;
        this.yAxies = y;
        this.width = 50;
        this.name = "SWITCH " + this.id;

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL); // Figura solida
    }

    /**
     * Draw the figure
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas){
        Path path = new Path();
        /**
         *  -
         */
        path.moveTo(this.xAxies, this.yAxies);

        /**
         *  ----------
         */
        path.lineTo(this.xAxies +50, this.yAxies);

        /**
         *
         *  ----------
         *           |
         *           |
         */
        path.lineTo(this.xAxies +50, this.yAxies +50);

        /**
         *
         *  ----------
         *           |
         *           |----
         */
        path.lineTo(this.xAxies +95, this.yAxies +50);
        path.lineTo(this.xAxies +95, this.yAxies +55);
        path.lineTo(this.xAxies +50, this.yAxies +55);

        /**
         *
         *  ----------
         *           |
         *           |----
         *           |
         */
        path.lineTo(this.xAxies+50, this.yAxies +100);

        /**
         *
         *  ----------
         *           |
         *           |----
         *           |
         * ----------
         */
        path.lineTo(this.xAxies, this.yAxies +100);

        /**
         *
         *  ----------
         * |         |
         * |         |----
         * |         |
         * ----------
         */
        path.lineTo(this.xAxies, this.yAxies);

        canvas.drawPath(path, paint);
        canvas.drawText(this.name, this.xAxies, this.yAxies+120, paint);
    }

    /**
     * Check that you are clicking inside the figure
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     * @return id
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int onDown(int touchX, int touchY){
        if(touchX > this.xAxies && touchX < this.xAxies +this.width &&
                touchY > this.yAxies && touchY < this.yAxies +this.height) {

            this.is_active = !is_active;
            Point.getGatePoints(this).get(0).status = this.is_active;
            this.activate();

            return this.id;
        }
        return -1;
    }

    /**
     * Modify the position of the figure according to the position of the finger
     * @param touchX position of the tap on the X axis
     * @param touchY position of the tap on the Y axis
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onMove(int touchX, int touchY){
        this.xAxies = touchX - this.width /2;
        this.yAxies = touchY - this.height /2;

        // Update position of the points
        for(Point point : Point.getGatePoints(this)){
            point.onMoveGate(this.xAxies, this.yAxies+300);
        }
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Point> getPoints(){
        return Point.getGatePoints(this);
    }

    @Override
    public Boolean getOutput() {
        return this.is_active;
    }

    public void activate(){
        if(is_active)
            this.paint.setColor(Color.BLUE);
        else
            this.paint.setColor(Color.BLACK);
    }
}

