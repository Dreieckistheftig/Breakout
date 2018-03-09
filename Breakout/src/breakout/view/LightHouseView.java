package breakout.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import breakout.model.Ball;
import breakout.model.GameWorld;
import breakout.model.Paddle;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;

/**
 * View class for the LightHouse.
 * 
 * @author armin
 *
 */
public class LightHouseView {

	private final GameWorld gw;
	private final LighthouseDisplay ld;
	private final byte[] backbuffer;
	private BufferedImage image;

	/**
	 * Constructor.
	 * 
	 * @param gw
	 *            GameWorld, which should be used.
	 * @param dW
	 *            Width of the view.
	 * @param dH
	 *            Height of the view.
	 * @param username
	 *            Username for the LightHouse-API.
	 * @param apitoken
	 *            API-Token for the LightHouse-API.
	 */
	public LightHouseView(GameWorld gw, int dW, int dH, String username, String apitoken) {
		this.gw = gw;
		ld = new LighthouseDisplay(username, apitoken);

		// We will use a BufferedImage to draw the view. It will have the size of the
		// GameWorld. The Array for the LightHouse will be generated by reading the
		// pixels.
		image = new BufferedImage(dW, dH, BufferedImage.TYPE_INT_RGB);
		backbuffer = new byte[dW * dH * 3];
	}

	/**
	 * Getter for ld.
	 * 
	 * @return ld.
	 */
	public LighthouseDisplay getLd() {
		return ld;
	}

	/**
	 * Update the local BufferedImage by painting it all black and the adding the
	 * ball.
	 */
	private void updateImage() {
		// Convert image to Graphics2D
		Graphics2D g = (Graphics2D) image.getGraphics();

		// Use AntiAliasing to be able to show the ball more precisely.
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Komplettes Display schwarz, damit nur die aktuelle Position des Balls
		// angezeigt wird.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		Ball ball = gw.getBall();

		g.setColor(Color.RED);

		// Draw ball on the image.
		Ellipse2D.Double ballS = new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getR() * 2, ball.getR() * 2);
		g.fill(ballS);

		Paddle paddle = gw.getPaddle();

		g.setColor(Color.YELLOW);
		// Draw Paddle on the image.
		g.fillRect((int) paddle.getX(), (int) paddle.getY(), (int) paddle.getPw(), (int) paddle.getPh());

		// Draw all bricks
		for (int i = 0; i < gw.brickList.size(); i++) {

			g.setColor(gw.brickList.get(i).getColour());
			g.fillRect((int) gw.brickList.get(i).getX(), (int) gw.brickList.get(i).getY(),
					(int) gw.brickList.get(i).getXw(), (int) gw.brickList.get(i).getYh());
		}

		g.dispose();

	}

	public void render() {
		updateImage();

		// Read the pixels of the BufferedImage, split them up in the 3 (4 with alpha
		// channel) components and adding them to the array we want to send to the
		// LightHouse.
		int index = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {

				// See http://www.binaryconvert.com/convert_signed_int.html?hexadecimal=FFFFFFFF
				// for conversion from int to byte.

				// argb -> [8a, 8r, 8g, 8b]
				int argb = image.getRGB(x, y);
				// byte b = (byte)argb;
				// byte g = (byte)(argb >> 8);
				// byte r = (byte)(argb >> 16);
				backbuffer[index++] = (byte) (argb >> 16);
				backbuffer[index++] = (byte) (argb >> 8);
				backbuffer[index++] = (byte) (argb);

			}
		}

		try {
			ld.send(backbuffer);
		} catch (IOException e) {
		}
	}
}
