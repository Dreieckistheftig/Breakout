package breakout.model;

import java.awt.Color;
import java.util.ArrayList;

import breakout.model.Ball;
import breakout.model.Brick.BrickType;

/**
 * GameWorld/Model.
 * 
 * @author armin
 *
 */
public class GameWorld {

	private final double width, height;

	public final Ball ball;
	public final Paddle paddle;
	public ArrayList<Brick> brickList = new ArrayList<>();

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
		ball = new Ball(this, width / 2, height / 2, .5);

		// Setting the velocity the ball is moving in one second (assuming that the
		// time-delta given to the method in update() is in seconds).
		ball.setVelX(5);
		ball.setVelY(10);

		// Create a new paddle.
		paddle = new Paddle(this, width / 2, height * 0.93, width / 5, height * 0.08);

		// Create new bricks.

		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 7; i++) {
				brickList.add(new Brick(this, i * 4, j, 3, 1, Color.MAGENTA, BrickType.TRIPLE));
			}
		}

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

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
