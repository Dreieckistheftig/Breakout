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
		checkBrickCollision(x, y, xOld, yOld);

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
	
	private boolean checkBrickCollision(double x, double y, double xOld, double yOld) {
		for (int i = 0; i < gw.brickList.size(); i++) {

			//if(velY > 0)
			// oben
			if (y + 2 * r >= gw.brickList.get(i).y && x >= gw.brickList.get(i).x
					&& x + 2 * r <= (gw.brickList.get(i).x + gw.brickList.get(i).xw)) {
				checkHitCounter(i);
				velY = -Math.abs(velY);
				x = xOld;
				y = yOld;
				return true;
			} else

			//if(velY < 0)
			// unten
			if (y <= (gw.brickList.get(i).y + gw.brickList.get(i).yh) && x + 2 * r >= gw.brickList.get(i).x
					&& x <= (gw.brickList.get(i).x + gw.brickList.get(i).xw)) {
				checkHitCounter(i);
				velY = Math.abs(velY);
				x = xOld;
				y = yOld;
				return true;
			} else

			//if(velX > 0)
			// links
			if (y + 2 * r >= gw.brickList.get(i).y && y <= (gw.brickList.get(i).y + gw.brickList.get(i).yh)
					&& x + 2 * r >= gw.brickList.get(i).x) {
				checkHitCounter(i);
				velX = -Math.abs(velX);
				x = xOld;
				y = yOld;
				return true;
			} else

			//if(velX < 0)
			// rechts
			if (y + 2 * r >= gw.brickList.get(i).y && y <= (gw.brickList.get(i).y + gw.brickList.get(i).yh)
					&& x <= (gw.brickList.get(i).x + gw.brickList.get(i).xw)) {
				checkHitCounter(i);
				velX = Math.abs(velX);
				x = xOld;
				y = yOld;
				return true;
			}
		}
		return false;
	}
	
	private void checkHitCounter(int i) {
		if (gw.brickList.get(i).hits > 1) {
			gw.brickList.get(i).hits -= 1;

		} else if (gw.brickList.get(i).hits == 1) {
			gw.brickList.remove(i);

			System.out.println("Brick removed");
		}

	}

}
