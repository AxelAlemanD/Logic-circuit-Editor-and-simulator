/*
 * Author: Meta @ vidasconcurrentes
 * Related to: http://vidasconcurrentes.blogspot.com/2011/06/detectando-drag-drop-en-un-canvas-de.html
 */

package com.upv.pm_2022.iti_27849_u2_equipo_01;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DragAndDropView extends SurfaceView implements SurfaceHolder.Callback {

	private DragAndDropThread thread;
	public static ArrayList<Figure> figures;
	public static ArrayList<Figure> lines;
	private int activeFigure;
	
	public DragAndDropView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// nothing here
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		figures = new ArrayList<Figure>();
		lines = new ArrayList<Figure>();
		activeFigure = -1;
		
		thread = new DragAndDropThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {

			}
		}
	}

	/**
	 * Draw the figures on the canvas
	 * @param canvas
	 */
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		for(Figure figure : figures) {
			figure.draw(canvas);
			for(Figure point : figure.getPoints())
				point.draw(canvas);
		}
		for (Figure line : lines){
			line.draw(canvas);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch(event.getAction()) {

			case MotionEvent.ACTION_DOWN:
				for(Figure figure : figures) {
					if(activeFigure == -1) {
						activeFigure = figure.onDown(x, y);
						for(Figure point : figure.getPoints())
							point.onDown(x, y);
					}
				}
				break;

			case MotionEvent.ACTION_MOVE:
				if(activeFigure != -1)
					figures.get(activeFigure).onMove(x, y);
				break;

			case MotionEvent.ACTION_UP:
				activeFigure = -1;
				break;
		}
		return true;
	}
}