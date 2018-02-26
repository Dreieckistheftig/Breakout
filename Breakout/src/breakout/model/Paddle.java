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

}
