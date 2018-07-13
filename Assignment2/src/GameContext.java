/**
 * This class represents an in-progress game. It will hold the "global"
 * statistics about a game (such as the size of the level, a reference to the
 * entity bag, and scores).
 */
public class GameContext {
	private int w;
	private int h;
	private EntityBag entities;
	private boolean paused;

	public GameContext(EntityBag entities) {
		this.entities = entities;
	}

	public EntityBag getEntities() {
		return entities;
	}

	/**
	 * Set the grid size of the current game.
	 * 
	 * @param w
	 *            The maximum x-grid coordinate.
	 * @param h
	 *            The maximum y-grid coordinate.
	 */
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}

	/**
	 * Get the width of the game (in pixels).
	 * 
	 * @return The width.
	 */
	public int getW() {
		return w * Pacman.CELL_SIZE;
	}

	/**
	 * Get the height of the game (in pixels).
	 * 
	 * @return The height.
	 */
	public int getH() {
		return h * Pacman.CELL_SIZE;
	}

	/**
	 * Used to tell if the game is currently paused.
	 * 
	 * @return true if the game is currently paused and false otherwise
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * Pause the game.
	 */
	public void pause() {
		paused = true;
	}

	/**
	 * Unpause the game.
	 */
	public void resume() {
		paused = false;
	}
}
