package breakout.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import breakout.model.GameWorld;
import breakout.view.LocalView;

/**
 * Contains the Listeners to interact with the game.
 * 
 * @author Alexander
 *
 */
public class Controller {
	private GameWorld gw;
	private JFrame vFrame;
	private LocalView view;
	
	/**
	 * Constructor of a listener class for the game.
	 * 
	 * @param gw
	 * 			The GameWorld to have impact on.
	 * @param vFrame
	 * 			The frame to listen to.
	 */
	public Controller(GameWorld gw, JFrame vFrame, LocalView view) {
		this.gw = gw;
		this.vFrame = vFrame;
		this.view = view;
		
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
				gw.paddle.moveTo(arg0.getX() / (int) view.getScale());
			}
		});
	}

	/**
	 * Defines and adds a new KeyListener.
	 */
	private void addKeyListener() {
		vFrame.addKeyListener(new KeyListener() {
					
			@Override
			public void keyTyped(KeyEvent key) {
								// TODO Auto-generated method stub

				// Game pause on 'p'
				if (key.getKeyChar() == 'p') {
					gw.pause();
				}
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
				 * Abh�ngig vom Framerate-Delta bewegen.
				 */
						
				if (key.getKeyCode() == KeyEvent.VK_LEFT) {
					gw.paddle.move(false);
				} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
					gw.paddle.move(true);
				}
			}
		});
	}	
}