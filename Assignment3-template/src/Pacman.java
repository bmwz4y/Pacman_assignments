import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import util.Depth;
import util.Direction;

public class Pacman extends JFrame {
	public static final int CELL_SIZE = 20;

	private Direction input;
	private GameContext context;

	private PacmanDisplay display;

	public Pacman() {
		setTitle("Pacman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
	}

	/**
	 * Add components to the frame and register the input listener.
	 */
	private void createContents() {
		addKeyListener(new InputListener());

		display = new PacmanDisplay();
		addMenu();
		add(display);
	}

	/**
	 * Create the new game / pause / resume options.
	 */
	private void addMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu game = new JMenu("Game");

		final JMenuItem restart = new JMenuItem("New Game");
		final JMenuItem pause = new JMenuItem("Pause");
		final JMenuItem resume = new JMenuItem("Resume");
		resume.setEnabled(false);

		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(false);
				resume.setEnabled(true);
				context.pause();
			}
		});

		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(true);
				resume.setEnabled(false);
				context.resume();
			}
		});

		game.add(restart);
		game.add(pause);
		game.add(resume);
		bar.add(game);
		setJMenuBar(bar);
	}

	/**
	 * Create a new game. This requires us to create a new context, zero the
	 * user input, and resize the frame (in case the level has changed).
	 */
	private void newGame() {
		input = null;
		EntityBag entities = new EntityBag();
		context = new GameContext(entities);

		makeLevel(context);

		// Resize Frame
		setSize(context.getW(), context.getH() + 44);
		setLocationRelativeTo(null);
	}

	/**
	 * (Statically) populate the level. We're just hard-coding initial positions
	 * here so that we have something to test with.
	 *
	 * @param context
	 *            The game context to populate.
	 */
	private void makeLevel(GameContext context) {
		Player p = new Player(context, 6, 3);
		context.getEntities().addEntity(p);

		for (int i = 2; i <= 6; i++) {
			context.getEntities().addEntity(new Wall(0, i));
			context.getEntities().addEntity(new Wall(i, 0));

			context.getEntities().addEntity(new Wall(19, 9 - i));
			context.getEntities().addEntity(new Wall(19 - i, 9));
		}

		for (int i = 3; i <= 6; i++) {
			context.getEntities().addEntity(new Wall(5, i));
			context.getEntities().addEntity(new Wall(14, i));
		}

		for (int i = 6; i < 10; i++) {
			context.getEntities().addEntity(new Wall(i, 6));
		}

		for (int i = 10; i <= 13; i++) {
			context.getEntities().addEntity(new Wall(i, 3));
		}

		context.setSize(20, 10);
	}

	/**
	 * The main game loop. In a blocking loop, update and render the game if the
	 * game is not currently paused. Then, sleep some amount so that we're
	 * averaging 60 updates per second.
	 */
	public void run() {
		newGame();

		while (true) {
			if (!context.isPaused()) {
				update();
				render();
			}

			try {
				Thread.sleep(1000 / 60);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Update each entity.
	 */
	private void update() {
		for (Entity e : context.getEntities().getAllEntities()) {
			e.update(input);
		}
	}

	/**
	 * Re-draw the game.
	 */
	private void render() {
		display.validate();
		display.repaint();
	}

	/**
	 * A specialized panel which draws the game. When this frame is painted we
	 * simply iterate all the entities in the game and draw them (in layers of
	 * increasing z-depth).
	 */
	private class PacmanDisplay extends JPanel {
		public PacmanDisplay() {
			setDoubleBuffered(true);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.gray);

			for (int i = 0; i < context.getW(); i++) {
				g.drawLine(i * Pacman.CELL_SIZE, 0, i*Pacman.CELL_SIZE, context.getH());
			}

			for (int i = 0; i < context.getH(); i++) {
				g.drawLine(0, i * Pacman.CELL_SIZE, context.getW(), i * Pacman.CELL_SIZE);
			}

			for (Depth d : Depth.values()) {
				for (Entity e : context.getEntities().getAllEntities()) {
					if (e.getDepth() == d) {
						e.draw(g);
					}
				}
			}
		}
	}

	/**
	 * The input listener listens for player key presses and sets the input
	 * instance variable in the outer class to be the most recent user input. We
	 * only listen to presses - holding does nothing (we retain the last input
	 * until a new input is given to emulate the original game).
	 */
	private class InputListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 38)
				input = Direction.NORTH;
			if (e.getKeyCode() == 40)
				input = Direction.SOUTH;
			if (e.getKeyCode() == 39)
				input = Direction.EAST;
			if (e.getKeyCode() == 37)
				input = Direction.WEST;
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public static void main(String[] args) {
		new Pacman().run();
	}
}
