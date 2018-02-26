package breakout.model;

import breakout.model.GameWorld;

public class Ball {

	public double x, y, r;
	public double velX, velY;
	private final GameWorld gw;

	/**
	 * Constructor.
	 * 
	 * @param gw
	 *            GameWorld, the ball is to be used in.
	 * @param x
	 *            x-Coordinate.
	 * @param y
	 *            y-Coordinate.
	 * @param r
	 *            radius.
	 */
	public Ball(GameWorld gw, double x, double y, double r) {
		this.gw = gw;
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * Update the ball location. Check, wether it will collide with a wall. If yes,
	 * the movement direction is changed.
	 * 
	 * @param delta
	 *            Time-delta in seconds.
	 */
	public void update(double delta) {

		// Move the ball
		x += velX * delta;
		y += velY * delta;

		// Check for collision with x-axis.
		if (x < r) {
			x = r;
			// change moving direction to +
			velX = Math.abs(velX);
		} else if (x > gw.width - r) {
			x = gw.width - r;
			// change moving direction to -
			velX = -Math.abs(velX);
		}

		// Check for collision with y-axis.
		if (y < r) {
			y = r;
			// change moving direction to +
			velY = Math.abs(velY);
		} else if (y > gw.height - r) {
			y = gw.height - r;
			// change moving direction to -
			velY = -Math.abs(velY);
		}
	}

}
