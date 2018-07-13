package util;
public enum Direction {
	NORTH, SOUTH, EAST, WEST;
	
	public int getDeltaX() {
		if (this == WEST) return -1;
		if (this == EAST) return +1;
		return 0;
	}
	
	public int getDeltaY() {
		if (this == NORTH) return -1;
		if (this == SOUTH) return +1;
		return 0;
	}
	
	public Direction opposite() {
		if (this == NORTH) return SOUTH;
		if (this == SOUTH) return NORTH;
		if (this == EAST) return WEST;
		if (this == WEST) return EAST;
		return null;
	}
}
