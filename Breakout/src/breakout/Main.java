package breakout;

import javax.swing.JFrame;

import breakout.model.GameWorld;
import breakout.view.LightHouseView;
import breakout.view.LocalView;

/**
 * The retro arcade game BREAKOUT made with the MVC pattern.
 * 
 * The model holds all statuses and values while the controller contains a mouse
 * and key listener to enable the bar control for the user. The view has
 * multiple subclasses for different displays, for example the device to play on
 * and the 14x28 pixels highrise-building, and is updated by the Model class
 * regularly.
 * 
 * The Main class starts the game by initializing the Model class with the
 * correct display resolutions, loading a brick layout and starting the
 * Controller class with the listeners.
 * 
 * This pattern makes it possible for more than one person to effectively work
 * simultaneously on the game development and abstract the processes of the
 * program.
 * 
 * Order of tasks: 1) Writing the Model class and creating a "ball" point which
 * moves and bounces of all window borders. The bounce aims a physically logical
 * direction. Important: measuring the time for a constant FPS rate. 2) Writing
 * of the View class and a subclass to display the game process in an applet on
 * the PC. 3) Writing of the Controller class which enables user input via mouse
 * or direction keys. 4) Creating bar coordinates in the model an make them
 * changeable by the user input. The bar is counted by the ball as border to
 * bounce of. 5) Ground border is changed to GAME OVER. 6) Implementation of
 * three lives and GAME OVER upon losing all. Don't forget to show them on
 * screen. 7) Being creative and designing levels of different brick layouts
 * which are general enough to be interpreted by all views. 8) Making the bricks
 * in the layouts objects for the ball to bounce off in the right direction (and
 * being destroyed by the ball). 9) Level clear, level advance and score
 * implementation. 10) Extras like highscore, items, etc.
 */

public class Main {
	public static void main(String[] args) throws Exception {
		// Initialize a new GameWorld (model) with a resolution of 28px width and 14px
		// height.
		GameWorld gw = new GameWorld(28, 14);

		// Initialize a new LocalView (view) which we will put on the JFrame constructed
		// next
		LocalView view = new LocalView(gw);

		// Constructing a new JFrame to show the game locally
		JFrame vFrame = new JFrame("Example");
		vFrame.add(view);
		vFrame.pack();
		vFrame.setSize(600, 350);
		vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vFrame.setLocationRelativeTo(null);
		vFrame.setVisible(true);

		// Initialize a new LightHouseView (view) to show the game on the LightHouse
		LightHouseView lhView = new LightHouseView(gw, 28, 14, "DoubleAA", "API-TOK_cN52-Afg8-7ONW-ngFD-eYu5");

		// Save the current time in nano-seconds
		long lastTime = System.nanoTime();
		
		// Update the views
		while (true) {
			long now = System.nanoTime();
			
			// Updating the GameWorld by giving the commenced time to scale the movement
			gw.update((now - lastTime) / 1e9);
			lastTime = now;
			
			// Update the views
			view.repaint();
			lhView.render();
			
			// Sleep for 20 milliseconds
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
