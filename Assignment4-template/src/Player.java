import java.awt.Color;
import java.awt.Graphics;

import util.Depth;
import util.Direction;

public class Player extends DynamicEntity {
	
	public Player(GameContext context, int x, int y) {
		super(context, x, y, Depth.FRONT);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		super.draw(g);
	}

	@Override
	public void move(Direction direction) {
		super.update(direction);
	}
	
}
