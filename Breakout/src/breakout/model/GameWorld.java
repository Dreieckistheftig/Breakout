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

	private boolean pause;
	private final Ball ball;

	private final Paddle paddle;

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
		pause = true;

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
	 * Update the location of the ball if the game is not on pause.
	 * 
	 * @param delta
	 *            time-delta in seconds.
	 */
	public void update(double delta) {
		if (!pause) {
			ball.update(delta);
			//TODO paddle.update(delta);
		}
	}

	/**
	 * Pauses and resumes the game.
	 */
	public void pause() {
		pause = !pause;
	}
	
	/**
	 * Starts the game.
	 */
	public void endPause() {
		pause = false;
	}
	
	/**
	 * Getter for width.
	 * 
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Getter for height.
	 * 
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Getter for padle.
	 * @return paddle
	 */
	public Paddle getPaddle() {
		return paddle;
	}
	
	/**
	 * Getter for ball.
	 * @return ball
	 */
	public Ball getBall() {
		return ball;
	}

}
