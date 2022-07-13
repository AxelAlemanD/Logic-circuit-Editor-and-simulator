package com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.upv.pm_2022.iti_27849_u2_equipo_01.Figure;
import com.upv.pm_2022.iti_27849_u2_equipo_01.MainActivity;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Point;

import java.util.List;

public class ClockControl extends Figure {

    private Boolean is_active = false;
    public Paint innerPaint;
    public int duration;

    public ClockControl(int id, int x, int y) {
        this.id = id;
        this.xAxies = x;
        this.yAxies = y;
        this.width = 70;
        this.height = 70;
        this.name = "CLOCK " + this.id;
        this.duration = 2;

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE); // Figura solida

        this.innerPaint = new Paint();
        innerPaint.setAntiAlias(true);
        innerPaint.setColor(Color.DKGRAY);
        innerPaint.setStyle(Paint.Style.FILL); // Figura solida
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
        path.lineTo(this.xAxies +70, this.yAxies);

        /**
         *
         *  ----------
         *           |
         *           |
         */
        path.lineTo(this.xAxies +70, this.yAxies +30);

        /**
         *
         *  ----------
         *           |
         *           |----
         */
        path.lineTo(this.xAxies +105, this.yAxies +30);
        path.lineTo(this.xAxies +105, this.yAxies +35);
        path.lineTo(this.xAxies +70, this.yAxies +35);

        /**
         *
         *  ----------
         *           |
         *           |----
         *           |
         */
        path.lineTo(this.xAxies+70, this.yAxies +70);

        /**
         *
         *  ----------
         *           |
         *           |----
         *           |
         * ----------
         */
        path.lineTo(this.xAxies, this.yAxies +70);

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
        canvas.drawText(this.name, this.xAxies, this.yAxies+90, paint);
        canvas.drawText("Duration: " + this.duration, this.xAxies+10, this.yAxies-10, paint);

        Path innerPath = new Path();
        innerPath.lineTo(this.xAxies + 10, this.yAxies + 10);
        innerPath.lineTo(this.xAxies + 40, this.yAxies + 10);
        innerPath.lineTo(this.xAxies + 40, this.yAxies + 50);
        innerPath.lineTo(this.xAxies + 65, this.yAxies + 50);
        innerPath.lineTo(this.xAxies + 65, this.yAxies + 60);
        innerPath.lineTo(this.xAxies + 30, this.yAxies + 60);
        innerPath.lineTo(this.xAxies + 30, this.yAxies + 20);
        innerPath.lineTo(this.xAxies + 10, this.yAxies + 20);
        innerPath.lineTo(this.xAxies + 10, this.yAxies + 10);
        canvas.drawPath(innerPath, innerPaint);
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Boolean getOutput() {
        this.is_active = !is_active;
        Point.getGatePoints(this).get(0).status = this.is_active;
        this.activate();

        return this.is_active;
    }

    public void activate(){
        if(is_active)
            this.innerPaint.setColor(Color.BLUE);
        else
            this.innerPaint.setColor(Color.BLACK);
    }
}

