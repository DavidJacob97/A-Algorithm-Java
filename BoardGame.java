import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGame {
	public static void main(String[] args) {

		// int[] A = { 5, 2, 6, 1, 0, 3, 4, 8, 7 };
		// int[] B = { 5, 6, 0, 1, 2, 3, 4, 8, 7 };

		int[] A = { 2, 4, 3, 1, 0, 6, 7, 5, 8 };
		int[] B = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };

		// read in start state & end state
		// int[] A = null;
		// String s = JOptionPane.showInputDialog("Enter the Start State");
		// A = validateString(s);
		// while (A == null) {
		// s = JOptionPane.showInputDialog("Wrong format\n Enter the Start State
		// again");
		// A = validateString(s);
		// }
		Board start = new Board(A);
		start.setG(0);

		// int[] B = null;
		// String t = JOptionPane.showInputDialog("Enter the End State");
		// B = validateString(t);
		// while (B == null) {
		// String j = JOptionPane.showInputDialog("Wrong format\n Enter the End State
		// again");
		// B = validateString(j);
		// }
		Board end = new Board(B);

		Board current = start;
		// System.out.println("Start state");
		// current.print();
		// System.out.println("End state");
		// end.print();
		// System.out.println("Moves are ");
		// List<Move> moves = current.getMoves();
		// for (Move m : moves) {
		// m.printMove();
		// }

		// System.out.println("h values");
		// List<D> directions = current.getDirections();
		// for (D d : directions) {
		// Board newBoard = current.createChild(d);
		// // newBoard.print();
		// int h = newBoard.getH(end);
		// System.out.println("h = " + h);
		// // System.out.println();
		// }

		// A* algorithm
		BoardList open = new BoardList();
		BoardList closed = new BoardList();

		Boolean found = false;
		Board foundBoard = null;
		while (!found) {
			// System.out.println("Current board is ");
			// current.print();
			// all possible directions from current
			List<D> directions = current.getDirections();
			for (D d : directions) {
				// System.out.println(d);
				Board newBoard = current.createChild(d);
				newBoard.setH(end);
				if (!closed.contains(newBoard)) {
					open.add(newBoard);
					open.sortList();
					// System.out.println("Open List");
					// open.print();
					// newBoard.print();
					if (end.equalBoards(newBoard)) {
						found = true;
						foundBoard = newBoard;
						System.out.println("[FOUND]");
						break;
					}
				}
			}
			closed.add(current);
			current = open.takeBoard();
		}
		System.out.println("Sequence of States from Start to End");
		Board b = foundBoard;
		List<Board> parentList = new ArrayList<Board>();
		parentList.add(b);
		while (b.getParent() != null) {
			b = b.getParent();
			parentList.add(0, b);
		}
		for (Board bb : parentList) {
			bb.print();
			System.out.println("-----");
		}
	}

	static int[] validateString(String s) {
		String[] sa = s.split(" ");
		if (sa.length != 9) {
			return null;
		}
		int[] A = new int[9];
		int[] B = new int[9];
		for (int i = 0; i < sa.length; i++) {
			A[i] = Integer.parseInt(sa[i]);
			B[i] = Integer.parseInt(sa[i]);
		}

		Arrays.sort(A);
		int[] C = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		if (Arrays.equals(A, C)) {
			return B;
		} else {
			return null;
		}
	}
}

enum D {
	north, south, east, west;
}
