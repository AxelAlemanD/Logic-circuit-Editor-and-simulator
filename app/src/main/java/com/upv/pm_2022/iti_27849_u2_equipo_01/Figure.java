/*
 * Author: Meta @ vidasconcurrentes
 * Related to: http://vidasconcurrentes.blogspot.com/2011/06/detectando-drag-drop-en-un-canvas-de.html
 */

package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.graphics.Canvas;

public abstract class Figure {

	protected int id;
	protected int x;
	protected int y;

	public void draw(Canvas canvas){ }

	public int onDown(int touchX, int touchY){
		return this.id;
	}

	public void onMove(int touchX, int touchY){ }
}
