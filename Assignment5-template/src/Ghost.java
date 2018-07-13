import java.awt.Color;
import java.awt.Graphics;

import util.Depth;
import util.Direction;

public class Ghost extends DynamicEntity {
	public Ghost(GameContext context, int x, int y) {
		super(context, x, y, Depth.FRONT);
	}

	@Override
	public void update(Direction input) {
		move(atIntersection() ? chooseFacing() : getFacing());
	}

	/**
	 * Determine if the ghost is at an intersection (has the possibility of
	 * choice).
	 * 
	 * @return true if the ghost is at an intersection, false otherwise
	 */
	private boolean atIntersection() {
		return getX() % Pacman.CELL_SIZE == 0 && getY() % Pacman.CELL_SIZE == 0;
	}

	/**
	 * Choose a new facing according to the ghost's AI. This implementation
	 * chooses a the "closest" non-blocked direction with respect to the
	 * player's current position. This will only choose the "backwards"
	 * direction of the current facing if the ghost is at a dead-end.
	 * 
	 * @return The "best" direction.
	 */
	private Direction chooseFacing() {
		Player p = getContext().getPlayer();

		int tx = p.getGridX();
		int ty = p.getGridY();

		int cost = Integer.MAX_VALUE;
		Direction best = Direction.NORTH;

		for (Direction d : Direction.values()) {
			int x = getGridX() + d.getDeltaX();
			int y = getGridY() + d.getDeltaY();

			int c = Math.abs(tx - x) + Math.abs(ty - y);

			if (d.opposite() == getFacing()) {
				c += getContext().getW() + getContext().getH();
			}

			if (!this.isTargetBlocked(d) && c < cost) {
				cost = c;
				best = d;
			}
		}

		return best;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(getX(), getY(), Pacman.CELL_SIZE, Pacman.CELL_SIZE);
	}
	
	@Override
	public void touchedPlayer(Player player) {
		getContext().getEntities().removeEntity(player);
		getContext().pause();
	}
}
