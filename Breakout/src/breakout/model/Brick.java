package breakout.model;

import java.awt.Color;

public class Brick {
	public final double x, y, xw, yh;
	public int hits;
	public Color colour;
	private final GameWorld gw;

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
}
