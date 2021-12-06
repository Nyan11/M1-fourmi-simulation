package modelisation.utils;

public class Position {
	private int positionX;
	private int positionY;
	
	public Position(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public String toString() {
		return "(" + positionX + " @ " + positionY + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionX;
		result = prime * result + positionY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}

	public int x() {
		return this.positionX;
	}
	
	public int y() {
		return this.positionY;
	}
	
	public void set(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	public void setX(int x) {
		this.positionX = x;
	}
	
	public void setY(int y) {
		this.positionY = y;
	}

}
