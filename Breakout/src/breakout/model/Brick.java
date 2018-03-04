package breakout.model;

import java.awt.Color;

public class Brick {

	public final double x, y, xw, yh;
	public int hits;
	public Color colour;
	private final GameWorld gw;

	/**
	 * Constructor for a new Brick.
	 * @param gw GameWorld, the brick will be in.
	 * @param x x coordinate of the upper left corner.
	 * @param y y coordinate of the upper left corner.
	 * @param xw 
	 * @param yh
	 * @param colour
	 * @param typ
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

	public enum BrickType {

		NORMAL(1), DOUBLE(2), TRIPLE(3);

		private int neededHits;

		private BrickType(int number) {
			neededHits = number;
		}

	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getXw() {
		return xw;
	}

	public double getYh() {
		return yh;
	}

	public GameWorld getGw() {
		return gw;
	}

}
