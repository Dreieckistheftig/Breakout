package breakout.model;

import breakout.model.GameWorld;

public class Paddle {

	private double x;
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
		this.x = x;
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
	 * @param speed
	 *            Number of pixel the paddle moves.
	 */
	public void move(boolean direction, double speed) {
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
		//TODO
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
		this.x = x;
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
