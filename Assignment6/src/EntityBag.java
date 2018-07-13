import java.util.ArrayList;
import java.util.List;

import util.Depth;

/**
 * A simple bag (unordered list) of entities. This bag has a maximum of 500
 * entities (for now) which should be enough for basic testing purposes.
 */
public class EntityBag {
	/**
	 * A dynamic list to hold entities. The list holds the array's effective
	 * size internally.
	 */
	private List<Entity> entities;

	public EntityBag() {
		entities = new ArrayList<>();
	}

	/**
	 * Return a copy of the entity array. Since we can't also pass back an
	 * effective size, we only make the returned array (exactly) as big as
	 * necessary.
	 * 
	 * @return A copy of the entity array.
	 */
	public List<Entity> getAllEntities() {
		return new ArrayList<>(entities);
	}

	/**
	 * Returns a list of all the entities which sit on depth d.
	 * 
	 * @param d
	 *            The target depth.
	 * @return The filtered list.
	 */
	public List<Entity> getEntitiesAtDepth(Depth d) {
		List<Entity> filtered = new ArrayList<>();

		for (Entity e : getAllEntities()) {
			if (e.getDepth() == d) {
				filtered.add(e);
			}
		}

		return filtered;
	}

	/**
	 * Add an entity to the bag.
	 * 
	 * @param e
	 *            The entity to add.
	 */
	public void addEntity(Entity e) {
		entities.add(e);
	}

	/**
	 * Remove an entity from the bag.
	 * 
	 * @param e
	 *            The entity to remove.
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * Determine if there is a blocking entity at grid coordinate (x, y).
	 * 
	 * @param x
	 *            The x-coordinate.
	 * @param y
	 *            The y-coordinate.
	 * @return true if (x, y) is blocked, false otherwise
	 */
	public boolean isBlocked(int x, int y) {
		for (Entity e : getAllEntities()) {
			if (e.getGridX() == x && e.getGridY() == y && e.isSolid()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Should be called whenever an entity moves. This will be used to inform
	 * all entity pairs which are touching that a collision has happened.
	 * 
	 * @param moved The entity which moved.
	 */
	public void onMove(Entity moved) {
		for (Entity e : getAllEntities()) {
			if (e != moved && e.getGridX() == moved.getGridX() && e.getGridY() == moved.getGridY()) {
				e.touched(moved);
				moved.touched(e);
			}
		}
	}
}
