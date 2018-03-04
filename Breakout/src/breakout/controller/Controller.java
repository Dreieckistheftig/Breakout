package breakout.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import breakout.model.GameWorld;

/**
 * Contains the Listeners to interact with the game.
 * 
 * @author Alexander
 *
 */
public class Controller {
	private GameWorld gw;
	private JFrame vFrame;
	
	public Controller(GameWorld gw, JFrame vFrame) {
		this.gw = gw;
		this.vFrame = vFrame;
		
		addKeyListener();
		addMouseMotionListener();
	}
	
	/**
	 * Defines and adds a new MouseMotionListener.
	 */
	private void addMouseMotionListener() {
		vFrame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				mouseMoved(arg0);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				gw.paddle.moveTo(arg0.getX());
			}
		});
	}

	/**
	 * Defines and adds a new KeyListener.
	 */
	private void addKeyListener() {
		vFrame.addKeyListener(new KeyListener() {
					
			@Override
			public void keyTyped(KeyEvent arg0) {
								// TODO Auto-generated method stub

								// int[][] level1 = {{0,3,1,1,0,0,0},
								// {0,1,0,0,0,0,0}};

			}
					
			@Override
			public void keyReleased(KeyEvent arg0) {
								// TODO Auto-generated method stub

			}
					
			@Override
			public void keyPressed(KeyEvent key) {
					
				/**
				 * TODO Flag im Listener setzen, ob nach links, rechts oder gar nicht bewegt
				 * werden soll. Dieses dann in einer paddel_update() Methode abfragen. -->
				 * Abhängig vom Framerate-Delta bewegen.
				 */
						
				if (key.getKeyCode() == KeyEvent.VK_LEFT) {
					gw.paddle.move(false, 1.0);
				} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
					gw.paddle.move(true, 1.0);
				}
			}
		});
	}	
}
