import java.awt.Graphics;

import util.Depth;
import util.Direction;

/**
 * Represents an abstract entity (wall, ghost, player, pellet, etc). Entities
 * are responsible for holding their own state, updating their own state, and
 * being able to draw themselves to the screen.
 */
public abstract class Entity {
	/**
	 * Get the z-depth of the entity. Entities are drawn back-to-front so that
	 * entities with a higher z-depth will be drawn on top of entities with a
	 * lower z-depth.
	 *
	 * @return The depth.
	 */
	abstract public Depth getDepth();

	/**
	 * @return The x value of the entity's grid-coordinate.
	 */
	abstract public int getGridX();

	/**
	 * @return The y value of the entity's grid-coordinate.
	 */
	abstract public int getGridY();

	/**
	 * @return true if the object can not be moved through, false otherwise.
	 */
	public boolean isSolid() {
		return false;
	}

	/**
	 * Fired when another entity collides with this entity.
	 *
	 * @param other The colliding entity.
	 */
	public void touched(Entity other) {
		// do nothing by default
	}

	/**
	 * Fired when the player collides with this entity.
	 *
	 * @param other The player.
	 */
	public void touchedPlayer(Player player) {
		// do nothing by default
	}

	/**
	 * Update the entity. The input parameter is the most recent direction the
	 * user has entered. This value will be ignored by most entities.
	 *
	 * @param input The player input.
	 */
	public void update(Direction input) {
		// do nothing by default
	}

	/**
	 * Render the entity to the graphics object.
	 *
	 * @param g The graphics object.
	 */
	abstract public void draw(Graphics g);
}
