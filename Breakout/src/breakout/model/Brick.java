package breakout.model;

import java.awt.Color;

public class Brick {

	private final double x, y, xw, yh;
	private int hits;
	private Color colour;
	private final GameWorld gw;

	/**
	 * Constructor for a new Brick.
	 * 
	 * @param gw
	 *            GameWorld, the brick will be in.
	 * @param x
	 *            x coordinate of the upper left corner.
	 * @param y
	 *            y coordinate of the upper left corner.
	 * @param xw
	 *            width of the rectangle
	 * @param yh
	 *            height of the rectangle
	 * @param colour
	 *            Colour of the brick
	 * @param typ
	 *            Type of the brick (Out of the enum-List)
	 */
	public Brick(GameWorld gw, double x, double y, double xw, double yh, Color colour, BrickType typ) {

		this.gw = gw;
		this.x = x;
		this.y = y;
		this.xw = xw;
		this.yh = yh;
		this.colour = colour;
		this.hits = typ.neededHits;

	}

	/**
	 * Enum List for brick-Types. (Actually only specified by the number of hits,
	 * they can take.)
	 * 
	 * @author armin
	 *
	 */
	public enum BrickType {

		NORMAL(1), DOUBLE(2), TRIPLE(3);

		private int neededHits;

		private BrickType(int number) {
			neededHits = number;
		}

	}

	/**
	 * Get the number of hits, the brick can take at the moment.
	 * 
	 * @return Number of hits to go.
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Set the number of hits, the brick can take at the moment.
	 * 
	 * @param hits
	 *            Number of hits to go.
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/**
	 * Get the colour of the brick at the moment.
	 * 
	 * @return Colour.
	 */
	public Color getColour() {
		return colour;
	}

	/**
	 * Set the colour for the brick.
	 * 
	 * @param colour
	 *            Colour.
	 */
	public void setColour(Color colour) {
		this.colour = colour;
	}

	/**
	 * Get the upper left x coordinate of the brick.
	 * 
	 * @return x-coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the upper left y coordiante of the brick.
	 * 
	 * @return y-coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get the width of the brick.
	 * 
	 * @return width
	 */
	public double getXw() {
		return xw;
	}

	/**
	 * Get the height of the brick.
	 * 
	 * @return height
	 */
	public double getYh() {
		return yh;
	}

	/**
	 * Get the GameWorld the brick is placed in.
	 * 
	 * @return GameWorld
	 */
	public GameWorld getGw() {
		return gw;
	}

}
