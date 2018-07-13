import util.Depth;

public abstract class StaticEntity extends Entity {
	private int x;
	private int y;
	private Depth depth;

	public StaticEntity(int x, int y, Depth depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}

	@Override
	public int getGridX() {
		return x;
	}

	@Override
	public int getGridY() {
		return y;
	}

	@Override
	public Depth getDepth() {
		return depth;
	}
}
