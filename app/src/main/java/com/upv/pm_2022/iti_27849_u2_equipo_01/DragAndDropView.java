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

import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.AndGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NandGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NorGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.NotGate;
import com.upv.pm_2022.iti_27849_u2_equipo_01.LogicGates.OrGate;

public class DragAndDropView extends SurfaceView implements SurfaceHolder.Callback {

	private DragAndDropThread thread;
	public static ArrayList<Figure> figures;
	private int figuraActiva;
	private Canvas canvas;
	
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
		figuraActiva = -1;
		
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
		this.canvas = canvas;
		for(Figure figure : figures){
			if(figure instanceof AndGate)
				figure.draw(canvas);
			else if(figure instanceof OrGate)
				figure.draw(canvas);
			else if(figure instanceof NotGate)
				figure.draw(canvas);
			else if(figure instanceof NandGate)
				figure.draw(canvas);
			else if(figure instanceof NorGate)
				figure.draw(canvas);
			else if(figure instanceof Point)
				figure.draw(canvas);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch(event.getAction()) {

			case MotionEvent.ACTION_DOWN:
				for(Figure figure : figures) {
					if(figuraActiva == -1) {
						if (figure instanceof AndGate)
							figuraActiva = figure.onDown(x, y);
						else if (figure instanceof OrGate)
							figuraActiva = figure.onDown(x, y);
						else if (figure instanceof NotGate)
							figuraActiva = figure.onDown(x, y);
						else if (figure instanceof NandGate)
							figuraActiva = figure.onDown(x, y);
						else if (figure instanceof NorGate)
							figuraActiva = figure.onDown(x, y);
						else if (figure instanceof Point)
							figuraActiva = figure.onDown(x, y);
					}
				}
				break;

			case MotionEvent.ACTION_MOVE:
				if(figuraActiva != -1) {
					if(figures.get(figuraActiva) instanceof AndGate)
						figures.get(figuraActiva).onMove(x, y);
					else if(figures.get(figuraActiva) instanceof OrGate)
						figures.get(figuraActiva).onMove(x, y);
					else if(figures.get(figuraActiva) instanceof NotGate)
						figures.get(figuraActiva).onMove(x, y);
					else if(figures.get(figuraActiva) instanceof NandGate)
						figures.get(figuraActiva).onMove(x, y);
					else if(figures.get(figuraActiva) instanceof NorGate)
						figures.get(figuraActiva).onMove(x, y);
//					else if(figures.get(figuraActiva) instanceof Point)
//						((Point) figures.get(figuraActiva)).onMove(x, y, this.canvas);
				}
				break;

			case MotionEvent.ACTION_UP:
				figuraActiva = -1;
				break;
		}

		return true;
	}
}