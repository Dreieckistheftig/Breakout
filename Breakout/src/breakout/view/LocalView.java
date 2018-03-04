package breakout.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import breakout.model.Ball;
import breakout.model.GameWorld;
import breakout.model.Paddle;

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
	private static double scale = 20;

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
		g.drawRect(0, 0, (int) ((gw.getWidth()) * scale), (int) ((gw.getHeight()) * scale));

		// Draw the ball
		Ball b = gw.ball;
		g.setColor(Color.RED);
		g.fillOval((int) (b.getX() * scale), (int) (b.getY() * scale), (int) (b.getR() * 2 * scale),
				(int) (b.getR() * 2 * scale));

		// Draw paddle
		Paddle p = gw.paddle;
		g.setColor(Color.BLUE);
		g.fillRect((int) (p.getX() * scale), (int) (p.getY() * scale), (int) (p.getPw() * scale),
				(int) (p.getPh() * scale));

		for (int i = 0; i < gw.brickList.size(); i++) {

			g.setColor(gw.brickList.get(i).colour);
			g.fillRect((int) (gw.brickList.get(i).x * scale), (int) (gw.brickList.get(i).y * scale),
					(int) (gw.brickList.get(i).xw * scale), (int) (gw.brickList.get(i).yh * scale));
		}
	}

	public static double getScale() {
		return scale;
	}

}
