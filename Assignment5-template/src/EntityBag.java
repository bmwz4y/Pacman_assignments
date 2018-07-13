import java.util.ArrayList;
import java.util.List;

import util.Depth;

/**
 * A simple bag (unordered list) of entities. This bag has a maximum of 500
 * entities (for now) which should be enough for basic testing purposes.
 */
public class EntityBag {
	private static final int NUM_ENTITIES = 75;
	
	/**
	 * A dynamic array list to hold entities.
	 */
	private List<Entity> entities;

	public EntityBag() {
		entities = new ArrayList<Entity>(NUM_ENTITIES);
	}

	/**
	 * Return a copy of the entity array. Since we can't also pass back an
	 * effective size, we only make the returned array (exactly) as big as
	 * necessary.
	 * 
	 * @return A copy of the entity array.
	 */
	public List<Entity> getAllEntities() {
		List<Entity> filter = new ArrayList<Entity>(entities.size());
		boolean success = false;
		
		success = filter.addAll(entities);
		
		if (success)
			return filter;
		else
		{
			filter = null;
			return getAllEntities();
		}
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

	public List<Entity> getEntitiesAtDepth(Depth depth) {
		List<Entity> filter = new ArrayList<Entity>();
		
		for (Entity e : getAllEntities()) {
			if (e.getDepth() == depth) {
				filter.add(e);
			}
		}
		
		return filter;
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	public void onMove(Entity entity){
		
		for (Entity e : getAllEntities()) {
			if (entity != e && entity.getGridX() == e.getGridX() && entity.getGridY() == e.getGridY()) {
				entity.touched(e);
				e.touched(entity);
			}
		}
	}
}
