
// not being used
class Move {

	D d;
	int number;

	public Move(D d, int number) {
		this.d = d;
		this.number = number;
	}

	void printMove() {
		System.out.println(number + " to the " + d);
	}
}
