package de.cau.infprogoo.lighthouse;

import java.util.ArrayList;

public class Model {
	private int ballX;
	private int ballY;
	private int barX;
	private ArrayList<View> views;
	private static final double FRAME_TIME = 1;
	
	
	/**
	 * @return the ballX
	 */
	public int getBallX() {
		return ballX;
	}
	
	/**
	 * @return the ballY
	 */
	public int getBallY() {
		return ballY;
	}
	
	public void addView(View view) {
		views.add(view);
	}
	
	private void updateViews() {
		for (View view : views) {
			view.update(this);
		}
	}
}
