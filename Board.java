import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board implements Comparable<Board> {
	Board parent= null ;

	int[][] tiles = new int[3][3];
	int h;
	int g = 0;

	// construct from 2D array
	public Board(int[][] tiles) {
		this.tiles = tiles;
	}

	// constructor from 1D array
	Board(int[] arr) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tiles[i][j] = arr[k++];
			}
		}
	}

	public int[][] getTiles() {
		return tiles;
	}

	public void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(tiles[i][j] != 0) {
					System.out.print(tiles[i][j] + " ");
				}
				else {
					System.out.print(" " + " ");
				}
				
			}
			System.out.println();
		}
		// System.out.println();
	}

	// get all directions of 0 possible
	List<D> getDirections() {
		List<D> directions = new ArrayList<D>();
		Location loc0 = getLocation(0);

		// check the move of the blank tile east
		int newJ = loc0.j + 1;
		if (newJ <= 2) {
			directions.add(D.east);
		}

		// check the move of the blank tile west
		newJ = loc0.j - 1;
		if (newJ >= 0) {
			directions.add(D.west);
		}

		// check the move of the blank tile north
		int newI = loc0.i - 1;
		if (newI >= 0) {
			directions.add(D.north);
		}

		// check the move of the blank tile south
		newI = loc0.i + 1;
		if (newI <= 2) {
			directions.add(D.south);
		}

		return directions;
	}

	List<Move> getMoves() {
		List<Move> moves = new ArrayList<Move>();
		Location loc0 = getLocation(0);

		// check the move of the blank tile east
		int newJ = loc0.j + 1;
		if (newJ <= 2) {
			Move m = new Move(D.west, tiles[loc0.i][newJ]);
			moves.add(m);
		}
		// check the move of the blank tile west
		newJ = loc0.j - 1;
		if (newJ >= 0) {
			Move m = new Move(D.east, tiles[loc0.i][newJ]);
			moves.add(m);
		}
		// check the move of the blank tile north
		int newI = loc0.i - 1;
		if (newI >= 0) {
			Move m = new Move(D.south, tiles[newI][loc0.j]);
			moves.add(m);
		}
		// check the move of the blank tile south
		newI = loc0.i + 1;
		if (newI <= 2) {
			Move m = new Move(D.north, tiles[newI][loc0.j]);
			moves.add(m);
		}

		return moves;
	}

	// tile we are looking for
	Location getLocation(int tile) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tiles[i][j] == tile) {
					Location loc = new Location(i, j);
					return loc;
				}
			}
		}
		return null;
	}

	// returns a new Board object with 0 moved in direction d
	Board createChild(D d) {
		int[][] arrayCopy = new int[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arrayCopy[i][j] = tiles[i][j];
			}
		}

		Board childBoard = new Board(arrayCopy);
		Location loc0 = getLocation(0);

		switch (d) {
		case north:
			childBoard.switchTiles(loc0, loc0.north());
			break;

		case south:
			childBoard.switchTiles(loc0, loc0.south());
			break;

		case east:
			childBoard.switchTiles(loc0, loc0.east());
			break;

		case west:
			childBoard.switchTiles(loc0, loc0.west());
			break;

		}
		childBoard.setG(this.getG() + 1);
		childBoard.setParent(this);
		return childBoard;
	}

	public Board getParent() {
		return parent;
	}

	public void setParent(Board parent) {
		this.parent = parent;
	}

	// switch tiles at loc1 and loc2
	void switchTiles(Location loc1, Location loc2) {
		int temp = getTileAtLoc(loc1);
		tiles[loc1.i][loc1.j] = tiles[loc2.i][loc2.j];
		tiles[loc2.i][loc2.j] = temp;
	}

	int getTileAtLoc(Location loc) {
		return tiles[loc.i][loc.j];
	}

	// is this board equal to other board
	boolean equalBoards(Board other) {
		if (Arrays.deepEquals(this.tiles, other.tiles)) {
			return true;
		} else {
			return false;
		}
	}

	void setH(Board end) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int tile = tiles[i][j];
				if (tile != 0) {
					Location loc = new Location(i, j);
					Location correctLoc = end.getLocation(tile);
					sum += loc.dist(correctLoc);
				}
			}
		}
		h = sum;
	}

	int getF() {
		return g + h;
	}

	@Override
	public int compareTo(Board other) {
		if (this.getF() < other.getF()) {
			return -1;
		} else if (this.getF() < other.getF()) {
			return 1;
		} else {
			return 0;
		}
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}
}