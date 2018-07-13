import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.Depth;
import util.Direction;

public class PlayerTest {
	private Player player;

	@Before
	public void setup() {
		EntityBag bag = new EntityBag();
		GameContext c = new GameContext(bag);
		c.setSize(20, 30);

		player = new Player(c, 5, 7);
	}

	@Test
	public void testDepth() {
		assertEquals(Depth.FRONT, player.getDepth());
	}

	@Test
	public void testInitialCoordinates() {
		assertEquals(100, player.getX());
		assertEquals(140, player.getY());
	}

	@Test
	public void testUpdateNull() {
		player.update(null);

		assertEquals(100, player.getX());
		assertEquals(140, player.getY());
	}

	@Test
	public void testMovementOnGrid() {
		for (int i = 0; i < 20; i++) {
			player.update(Direction.EAST);
		}

		for (int i = 0; i < 20; i++) {
			player.update(Direction.NORTH);
			player.update(Direction.NORTH);
		}

		assertEquals(120, player.getX());
		assertEquals(100, player.getY());
	}

	@Test
	public void testWrapLeft() {
		for (int i = 0; i < 120; i++) {
			player.update(Direction.WEST);
		}

		assertEquals(380, player.getX());
		assertEquals(140, player.getY());
	}

	@Test
	public void testWrapDown() {
		for (int i = 0; i < 480; i++) {
			player.update(Direction.SOUTH);
		}

		assertEquals(100, player.getX());
		assertEquals(20, player.getY());
	}
}
