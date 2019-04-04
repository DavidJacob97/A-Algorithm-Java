class Location {
	int i;
	int j;

	public Location(int i, int j) {
		this.i = i;
		this.j = j;
	}

	void print() {
		System.out.println("(" + i + "," + j + ")");
	}

	// no checking done here
	// will only be called if the move north, south, etc is possible
	// returns the location north of this location
	public Location north() {
		return new Location(i - 1, j);
	}

	public Location south() {
		return new Location(i + 1, j);
	}

	public Location east() {
		return new Location(i, j + 1);
	}

	public Location west() {
		return new Location(i, j - 1);
	}
	
	int dist(Location newLoc) {
		return (Math.abs(i-newLoc.i) + Math.abs(j- newLoc.j));
	}
}