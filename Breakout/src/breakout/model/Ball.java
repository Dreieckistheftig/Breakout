package breakout.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
	 * Update the ball location. Check, whether it will collide with a wall. If yes,
	 * the movement direction is changed.
	 * 
	 * @param delta
	 *            Time-delta in seconds.
	 */
	public void update(double delta) {

		// Move the ball
		double xOld = x;
		double yOld = y;

		x += velX * delta;
		y += velY * delta;

		// Check for collision with a wall.
		checkWallCollision(x, y, xOld, yOld);

		// Check for collision with the paddle.
		checkPaddleCollision(x, y, xOld, yOld);

		// Check for collision with the bricks.
		if (checkBrickCollision(x, y) == true) {
			x = xOld;
			y = yOld;
		}

	}

	private boolean checkWallCollision(double x, double y, double xOld, double yOld) {
		// Check for collision with x-axis.
		if (x < 0) {
			// x = r;
			x = xOld;
			y = yOld;
			// change moving direction to +

			velX = Math.abs(velX);
			return true;
		} else if ((x + 2 * r) > gw.width) {
			x = xOld;
			y = yOld;
			// change moving direction to -
			velX = -Math.abs(velX);
			return true;
		}

		// Check for collision with y-axis.
		if (y < 0) {
			x = xOld;
			y = yOld;
			// change moving direction to +
			velY = Math.abs(velY);
		} else if (y > (gw.height - 2 * r)) {
			// change moving direction to -
			// velY = -Math.abs(velY);

			// TODO end game
			System.exit(0);
		}

		return false;
	}

	private boolean checkPaddleCollision(double x, double y, double xOld, double yOld) {
		if ((y + 2 * r) > gw.paddle.y && x >= gw.paddle.x && (x + 2 * r) <= (gw.paddle.x + gw.paddle.pw)) {
			x = xOld;
			y = yOld;
			velY = -Math.abs(velY);
			return true;
		}

		return false;
	}

	private boolean checkBrickCollision(double x, double y) {
		boolean collision = false;

		// Avoid java.util.ConcurrentModificationException
		ArrayList<Brick> bricksToBeRemoved = new ArrayList<>();

		Ellipse2D.Double ball = new Ellipse2D.Double(x, y, r * 2, r * 2);
		for (Brick b : gw.brickList) {
			if (ball.intersects(b.x, b.y, b.xw, b.yh)) {
				// COLLISION!
				System.out.println("Collision");
				collision = true;

				double xCompareValue = interpolate(b.x, -1, b.x + b.xw, 1, x + r);
				double yCompareValue = interpolate(b.y, -1, b.y + b.yh, 1, y + r);

				if (xCompareValue > yCompareValue) {
					// Oben oder unten
					if (yCompareValue > -1) {
						// unten
						velY = Math.abs(velY);
					} else {
						// oben
						velY = -Math.abs(velY);
					}
				} else if (xCompareValue == yCompareValue) {
					System.exit(0);
				} else {
					// rechts oder links
					if (xCompareValue > -1) {
						// rechts
						velX = Math.abs(velX);
					} else {
						// links
						velX = -Math.abs(velX);
					}
				}

				if (checkHitCounter(b) != null) {
					bricksToBeRemoved.add(b);
				}

			}
		}

		for (Brick brick : bricksToBeRemoved) {
			gw.brickList.remove(brick);
		}

		return collision;
	}

	private Brick checkHitCounter(Brick brick) {
		if (brick.hits > 1) {
			brick.hits -= 1;
			brick.colour = Color.BLUE;
			return null;
		} else if (brick.hits == 1) {
			return brick;
		}
		return null;

	}

	private double interpolate(double x1, double y1, double x2, double y2, double x) {
		// Have a look at
		// http://www.peter-junglas.de/fh/vorlesungen/thermodynamik1/html/app-a.html
		return y1 + ((y2 - y1) / (x2 - x1) * (x - x1));
	}

}
