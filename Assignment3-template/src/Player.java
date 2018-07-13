import java.awt.Color;
import java.awt.Graphics;

import util.Depth;
import util.Direction;

public class Player extends Entity {
	private GameContext context;
	private int x;
	private int y;

	public Player(GameContext context, int x, int y) {
		this.context = context;
		this.x = x * Pacman.CELL_SIZE;
		this.y = y * Pacman.CELL_SIZE;
	}

	@Override
	public Depth getDepth() {
		return Depth.FRONT;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, Pacman.CELL_SIZE, Pacman.CELL_SIZE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void update(Direction input) {
		if (input == null) {
			return;
		}

		x = wrap(x + input.getDeltaX(), context.getW());
		y = wrap(y + input.getDeltaY(), context.getH());
	}

	private int wrap(int val, int max) {
		return (val < 0) ? (val + max) : (val % max);
	}
}
