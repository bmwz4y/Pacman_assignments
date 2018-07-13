/**
 * A simple bag (unordered list) of entities. This bag has a maximum of 500
 * entities (for now) which should be enough for basic testing purposes.
 */
public class EntityBag {
	private static final int MAX_ENTITIES = 500;

	/**
	 * A static array to hold entities, and the array's effective size.
	 */
	private int numEntities;
	private Entity[] entities;

	public EntityBag() {
		entities = new Entity[MAX_ENTITIES];
	}

	/**
	 * Return a copy of the entity array. Since we can't also pass back an
	 * effective size, we only make the returned array (exactly) as big as
	 * necessary.
	 *
	 * @return A copy of the entity array.
	 */
	public Entity[] getAllEntities() {
		Entity[] trimmed = new Entity[numEntities];

		for (int i = 0; i < numEntities; i++) {
			trimmed[i] = entities[i];
		}

		return trimmed;
	}

	/**
	 * Add an entity to the bag.
	 *
	 * @param e
	 *            The entity to add.
	 */
	public void addEntity(Entity e) {
		entities[numEntities++] = e;
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
		for (int i = 0; i < numEntities; i++) {
			if (entities[i].getGridX() == x && entities[i].getGridY() == y && entities[i].isSolid()) {
				return true;
			}
		}

		return false;
	}
}
