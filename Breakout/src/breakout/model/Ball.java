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

		// Save the old coordinates
		double xOld = x;
		double yOld = y;
		
		// Move the ball
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
			x += velX * delta;
			y += velY * delta;
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
			return true;
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
				collision = true;

				if (ball.intersects(b.x, b.y, b.xw, 0.001)) {
					// oben
					velY = -Math.abs(velY);
					System.out.println("oben");
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}
				} else if (ball.intersects(b.x, b.y, 0.001, b.yh)) {
					// links
					velX = -Math.abs(velX);
					System.out.println("links");
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}

				} else if (ball.intersects(b.x + b.xw - 0.001, b.y, 0.001, b.yh)) {
					// rechts
					velX = Math.abs(velX);
					System.out.println("rechts");
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}
				} else if (ball.intersects(b.x, b.y + b.yh - 0.001, b.xw, 0.001)) {
					// unten
					velY = Math.abs(velY);
					System.out.println("unten");
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}
				}
			}
		}

		// Remove bricks
		for (Brick brick : bricksToBeRemoved) {
			gw.brickList.remove(brick);
		}

		return collision;
	}

	/**
	 * Check the counter
	 * @param brick
	 * @return
	 */
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

}
