package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {

	protected int id;
	protected int xAxies;
	protected int yAxies;
	protected int width = 100;
	protected int height = 100;
	protected Paint paint;
	protected String name;

	public void draw(Canvas canvas){ }

	public int onDown(int touchX, int touchY){
		return this.id;
	}

	public void onMove(int touchX, int touchY){ }

	@RequiresApi(api = Build.VERSION_CODES.N)
	public List<Point> getPoints(){ return Point.getGatePoints(this); }

	public Boolean getOutput(){ return true; }

	public void active(){ this.paint.setColor(Color.BLUE); }

	public void disable(){ this.paint.setColor(Color.DKGRAY); }
}
