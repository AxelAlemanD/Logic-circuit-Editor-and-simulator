/*
 * Author: Meta @ vidasconcurrentes
 * Related to: http://vidasconcurrentes.blogspot.com/2011/06/detectando-drag-drop-en-un-canvas-de.html
 */

package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public abstract class Figure {

	protected int id;
	protected int xAxies;
	protected int yAxies;
	protected int width = 100;
	protected int height = 100;
	protected Paint paint;
	protected String name;
	protected ArrayList<Figure> points;

	public void draw(Canvas canvas){ }

	public int onDown(int touchX, int touchY){
		return this.id;
	}

	public void onMove(int touchX, int touchY){ }

	public void addPoint(Point point){	}

	public ArrayList<Figure> getPoints(){ return points; }

	public Boolean getOutput(){ return true; }

	public void active(){ this.paint.setColor(Color.BLUE); }

	public void disable(){ this.paint.setColor(Color.DKGRAY); }
}
