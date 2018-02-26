package breakout.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import breakout.model.Ball;
import breakout.model.GameWorld;

/**
 * View class for the local window.
 * 
 * @author armin
 *
 */
public class LocalView extends JPanel {

	private static final long serialVersionUID = 1L;

	private final GameWorld gw;
	// Scale we use to scale the world up.
	private double scale = 20;

	/**
	 * Constructor for LocalView.
	 * 
	 * @param gw
	 *            GameWorld to show.
	 */
	public LocalView(GameWorld gw) {
		this.gw = gw;
	}

	@Override
	/**
	 * Paint the components.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw a rectangle as border
		// TODO the rectangle needs to be moved, the ball is going over the border.
		g.drawRect(0, 0, (int) (gw.width * scale), (int) (gw.height * scale));

		// Draw the ball
		Ball b = gw.ball;
		g.setColor(Color.RED);
		g.fillOval((int) (b.x * scale), (int) (b.y * scale), (int) (b.r * 2 * scale), (int) (b.r * 2 * scale));
	}

}