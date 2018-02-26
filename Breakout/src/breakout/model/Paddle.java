package breakout.model;

import breakout.model.GameWorld;

public class Paddle {

	public double x;
	public final double y, pw, ph;
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
	 * Move the paddle by the given value. DO NOT USE 0.
	 * 
	 * @param direction
	 *            If true the paddle moves to the right, if false it moves to the
	 *            left.
	 * @param speed
	 *            Number of pixel the paddle moves.
	 */
	public void move(boolean direction, double speed) {
		if (direction == true) {
			if ((x + pw) < gw.width) {
				x += speed;
			}
		} else if (direction == false) {
			if (x > 0) {
				x -= speed;
			}
		}
	}

}
