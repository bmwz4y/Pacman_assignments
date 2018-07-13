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
}
