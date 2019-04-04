import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BoardList {
	List<Board> boards = new ArrayList<Board>();

	Boolean contains(Board other) {
		for (Board b : boards) {
			if (Arrays.deepEquals(other.getTiles(), b.getTiles())) {
				return true;
			}
		}
		return false;
	}

	void add(Board other) {
		if (!boards.contains(other)) {
			boards.add(other);
		}
	}

	Board takeBoard() {
		Board b = boards.get(0);
		boards.remove(b);
		return b;
	}

	void print() {
		for (Board b : boards) {
			System.out.print(b.getF() + " ");
		}
		System.out.println();
	}

	void sortList() {
		Collections.sort(boards);
	}
}
