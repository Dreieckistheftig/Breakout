package breakout.model;

import breakout.model.Ball;

/**
 * GameWorld/Model.
 * 
 * @author armin
 *
 */
public class GameWorld {

	public final double width, height;

	public final Ball ball;
	public final Paddle paddle;

	/**
	 * Constructor for the GameWorld.
	 * 
	 * @param width
	 *            width of the GameWorld.
	 * @param height
	 *            height of the GameWorld.
	 */
	public GameWorld(double width, double height) {
		this.width = width;
		this.height = height;

		// Generate a new ball in THIS GameWorld, startpoint is in the middle of the
		// world, radius is 0,5
		ball = new Ball(this, width / 2, height / 2, .4);

		// Setting the velocity the ball is moving in one second (assuming that the
		// time-delta given to the method in update() is in seconds).
		ball.velX = ball.velY = 4;

		// Create a new paddle.
		paddle = new Paddle(this, width / 2, height * 0.93, width / 5, height * 0.08);
	}

	/**
	 * Update the location of the ball.
	 * 
	 * @param delta
	 *            time-delta in seconds.
	 */
	public void update(double delta) {
		ball.update(delta);
	}
}
