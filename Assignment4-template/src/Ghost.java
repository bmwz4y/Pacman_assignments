import java.awt.Color;
import java.awt.Graphics;

import util.Depth;
import util.Direction;

public class Ghost extends DynamicEntity {

	public Ghost(GameContext context, int x, int y) {
		super(context, x, y, Depth.FRONT);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		super.draw(g);
	}

	@Override
	public void move(Direction direction) {
		int next = (int) (Math.random()*4);
		direction.Random(next);
		super.update(direction);
	}
	
}
