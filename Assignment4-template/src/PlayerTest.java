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

		assertEquals(5, player.getGridX());
		assertEquals(7, player.getGridY());
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
		assertEquals(6, player.getGridX());
		assertEquals(5, player.getGridY());
	}

	@Test
	public void testWrapLeft() {
		for (int i = 0; i < 120; i++) {
			player.update(Direction.WEST);
		}

		assertEquals(380, player.getX());
		assertEquals(140, player.getY());
		assertEquals(19, player.getGridX());
		assertEquals(7, player.getGridY());
	}

	@Test
	public void testWrapDown() {
		for (int i = 0; i < 480; i++) {
			player.update(Direction.SOUTH);
		}

		assertEquals(100, player.getX());
		assertEquals(20, player.getY());
		assertEquals(5, player.getGridX());
		assertEquals(1, player.getGridY());
	}

	@Test
	public void testHorizontalGridChange() {
		for (int i = 0; i < 9; i++) {
			player.update(Direction.EAST);
		}

		assertEquals(109, player.getX());
		assertEquals(140, player.getY());
		assertEquals(5, player.getGridX());
		assertEquals(7, player.getGridY());

		// Push over the grid edge
		player.update(Direction.EAST);

		assertEquals(110, player.getX());
		assertEquals(140, player.getY());
		assertEquals(6, player.getGridX());
		assertEquals(7, player.getGridY());
	}

	@Test
	public void testVerticalGridChange() {
		// North (and west) take one extra pixel because
		// there are two "center" pixels and we choose a
		// lower one (hence the extra movement before we
		// move over the grid border).
		
		for (int i = 0; i < 10; i++) {
			player.update(Direction.NORTH);
		}

		assertEquals(100, player.getX());
		assertEquals(130, player.getY());
		assertEquals(5, player.getGridX());
		assertEquals(7, player.getGridY());

		// Push over the grid edge
		player.update(Direction.NORTH);

		assertEquals(100, player.getX());
		assertEquals(129, player.getY());
		assertEquals(5, player.getGridX());
		assertEquals(6, player.getGridY());
	}
	
	@Test
	public void testStaysOnGrid() {
		// move horizontally
		player.update(Direction.EAST);
		assertEquals(101, player.getX());
		assertEquals(140, player.getY());

		// ignore input, update with current facing
		player.update(Direction.NORTH);
		assertEquals(102, player.getX());
		assertEquals(140, player.getY());

		// ignore input, update with current facing
		player.update(Direction.SOUTH);
		assertEquals(103, player.getX());
		assertEquals(140, player.getY());

		// go backwards
		player.update(Direction.WEST);
		player.update(Direction.WEST);
		player.update(Direction.WEST);
		
		// move vertically
		player.update(Direction.SOUTH);
		assertEquals(100, player.getX());
		assertEquals(141, player.getY());

		// ignore input, update with current facing
		player.update(Direction.EAST);
		assertEquals(100, player.getX());
		assertEquals(142, player.getY());

		// ignore input, update with current facing
		player.update(Direction.WEST);
		assertEquals(100, player.getX());
		assertEquals(143, player.getY());
	}
}
