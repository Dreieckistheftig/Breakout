package breakout.model;

import breakout.model.GameWorld;

public class Paddle {

	private int x;
	private final double y, pw, ph;
	private final GameWorld gw;

	/**
	 * Constructor for the paddle.
	 * 
	 * @param gw
	 *            GameWorld to be used.
	 * @param x
	 *            x-coordinate of the paddle.
	 * @param y
	 *            y-coordinate of the paddle. Can't be changed after initialization.
	 */
	public Paddle(GameWorld gw, double x, double y, double width, double height) {
		this.gw = gw;
		this.x = (int) x;
		this.y = y;
		this.pw = width;
		this.ph = height;
	}
	
	/**
	 * Move the paddle by the given value.
	 * 
	 * @param direction
	 *            If true the paddle moves to the right, if false it moves to the
	 *            left.
	 */
	public void move(boolean direction) {
		move(direction, 1);
	}
	
	/**
	 * Move the paddle by the given value.
	 * 
	 * @param direction
	 *            If true the paddle moves to the right, if false it moves to the
	 *            left.
	 * @param speed
	 *            Number of pixel the paddle moves.
	 */
	private void move(boolean direction, int speed) {
		// Move right
		if (direction == true) {
			if ((x + pw) < gw.getWidth()) {
				x += speed;
			}
			
		// Move left
		} else if (direction == false) {
			if (x > 0) {
				x -= speed;
			}
		}
	}

	/**
	 * Moves the paddle to the mouse location but stays inside the frame borders.
	 * 
	 * @param mouseX
	 * 			The mouse location.
	 */
	public void moveTo(int mouseX) {
		// Move left
		if ((mouseX - ((int) pw / 2 + x)) < 0) {
			while (x > 0 && mouseX != ((int) pw / 2 + x)) {
				move(false, 1);
			}
		
		//Move right
		} else {
			while ((x + (int) pw) < gw.getWidth() && mouseX != ((int) pw / 2 + x)) {
				move(true, 1);
			}
		}
	}
	
	/**
	 * Getter for x.
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter for x.
	 * 
	 * @param x
	 *            x
	 */
	public void setX(double x) {
		this.x = (int) x;
	}

	/**
	 * Getter for y.
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Getter for PaddleWidth.
	 * 
	 * @return pw
	 */
	public double getPw() {
		return pw;
	}

	/**
	 * Getter for PaddleHeight.
	 * 
	 * @return ph
	 */
	public double getPh() {
		return ph;
	}
}
