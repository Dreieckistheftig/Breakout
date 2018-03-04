package breakout.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import breakout.model.GameWorld;

public class Ball {

	private double x, y, r;
	private double velX, velY;
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
		checkBrickCollision(x, y);

	}

	/**
	 * Check for collision with a surrounding wall. If necessary, it changes the
	 * vector for the movement.
	 * 
	 * @param x
	 *            Upper left x coordinate of the surrounding rectangle of the ball.
	 * @param y
	 *            Upper left y coordinate of the surrounding rectangle of the ball.
	 * @param xOld
	 *            Old x coordinate
	 * @param yOld
	 *            Old y coordinate
	 * @return true if there is some kind of collision.
	 */
	private boolean checkWallCollision(double x, double y, double xOld, double yOld) {
		// Check for collision with y-axis.
		if (x < 0) {

			// Collision on the left side

			// Move the ball back to the position before the last move
			x = xOld;
			y = yOld;

			// change moving direction to +
			velX = Math.abs(velX);

			return true;

		} else if ((x + 2 * r) > gw.getWidth()) {

			// Collision on the right side

			// Move the ball back to the position before the last move
			x = xOld;
			y = yOld;

			// change moving direction to -
			velX = -Math.abs(velX);

			return true;

		}

		// Check for collision with x-axis.
		if (y < 0) {

			// Collision on the top

			// Move the ball back to the position before the last move
			x = xOld;
			y = yOld;

			// change moving direction to +
			velY = Math.abs(velY);

			return true;

		} else if (y > (gw.getHeight() - 2 * r)) {

			// Collision on the bottom

			// change moving direction to -
			// velY = -Math.abs(velY);

			// TODO end game
			System.exit(0);
		}

		return false;
	}

	/**
	 * Check for collision with the paddle.
	 * 
	 * @param x
	 *            Upper left x coordinate of the surrounding rectangle of the ball.
	 * @param y
	 *            Upper left y coordinate of the surrounding rectangle of the ball.
	 * @param xOld
	 *            Old x coordinate
	 * @param yOld
	 *            Old y coordinate
	 * @return true if there is a collision.
	 */
	private boolean checkPaddleCollision(double x, double y, double xOld, double yOld) {
		if ((y + 2 * r) > gw.paddle.getY() && x >= gw.paddle.getX()
				&& (x + 2 * r) <= (gw.paddle.getX() + gw.paddle.getPw())) {

			// Move the ball back to the position before the last move
			x = xOld;
			y = yOld;

			// change moving direction to -
			velY = -Math.abs(velY);

			return true;

		}

		return false;
	}

	/**
	 * Checks for collision with the bricks specified in the GameWorld. If there is
	 * a collision, this method changes the moving vector accordingly.
	 * 
	 * @param x
	 *            Upper left x coordinate of the surrounding rectangle of the ball.
	 * @param y
	 *            Upper left y coordinate of the surrounding rectangle of the ball.
	 * @return true, if there is some kind of collision.
	 */
	private boolean checkBrickCollision(double x, double y) {
		boolean collision = false;

		// Avoid java.util.ConcurrentModificationException
		ArrayList<Brick> bricksToBeRemoved = new ArrayList<>();

		Ellipse2D.Double ball = new Ellipse2D.Double(x, y, r * 2, r * 2);
		for (Brick b : gw.brickList) {
			if (ball.intersects(b.getX(), b.getY(), b.getXw(), b.getYh())) {
				// COLLISION!
				collision = true;

				// Generating very small rectangles on all four sides of the brick, to check the
				// collision with intersects().

				if (ball.intersects(b.getX(), b.getY(), b.getXw(), 0.001)) {
					// Top side collision

					// Change moving Vector
					velY = -Math.abs(velY);
					y = b.getY() - r * 2;
					// Check brick status
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}

				} else if (ball.intersects(b.getX(), b.getY() + b.getYh() - 0.001, b.getXw(), 0.001)) {
					// Bottom side collision

					// Change moving vector
					velY = Math.abs(velY);
					y = b.getY() + b.getYh();

					// Check brick status
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}
				}
				if (ball.intersects(b.getX(), b.getY(), 0.001, b.getYh())) {
					// Left side collision

					// Change moving Vector
					velX = -Math.abs(velX);
					x = b.getX() - r * 2;
					// Check brick status
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}

				} else if (ball.intersects(b.getX() + b.getXw() - 0.001, b.getY(), 0.001, b.getYh())) {
					// Right side collision.

					// Change moving vector
					velX = Math.abs(velX);
					x = b.getX() + b.getXw();

					// Check brick status
					if (checkHitCounter(b) != null) {
						bricksToBeRemoved.add(b);
					}

				}
			}
		}

		// Remove hit bricks
		gw.brickList.removeAll(bricksToBeRemoved);

		return collision;
	}

	/**
	 * Check the counter of the specified brick. If 3 hits are left, subtract one
	 * and change the colour to BLUE. If 2 hits are left, subtract one and change
	 * the colour to GREEN. If 1 hit is left, return the brick, because it needs to
	 * be deleted.
	 * 
	 * @param brick
	 *            Brick to check on.
	 * @return null, if the brick has at least 1 hit to go. OR: the specified brick,
	 *         if it needs to be deleted, because this was the last hit.
	 */
	private Brick checkHitCounter(Brick brick) {
		if (brick.getHits() > 1) {

			brick.setHits(brick.getHits() - 1);
			// TODO write brick.update() and set color in there

			if (brick.getHits() == 2) {

				brick.setColour(Color.BLUE);

			} else if (brick.getHits() == 1) {

				brick.setColour(Color.GREEN);

			}

			return null;

		} else if (brick.getHits() == 1) {
			return brick;
		}
		return null;

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
	 * Setter for y.
	 * 
	 * @param y
	 *            y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Getter for r.
	 * 
	 * @return r
	 */
	public double getR() {
		return r;
	}

	/**
	 * Setter for r.
	 * 
	 * @param r
	 *            r
	 */
	public void setR(double r) {
		this.r = r;
	}

	/**
	 * Getter vor velX.
	 * 
	 * @return velX
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * Setter for velX
	 * 
	 * @param velX
	 *            velX
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * Getter for velY.
	 * 
	 * @return velY.
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Setter for velY.
	 * 
	 * @param velY
	 *            velY
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

	/**
	 * Getter for GameWorld/gw.
	 * 
	 * @return gw
	 */
	public GameWorld getGw() {
		return gw;
	}

}
