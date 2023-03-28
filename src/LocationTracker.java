
abstract class LocationTracker implements ILocationTracker {

	protected int currentPositionX;
	protected int currentPositionY;
	protected int[][] trackerMaze;
	protected boolean goSouthPossible = false;
	protected boolean goNorthPossible = false;
	protected boolean goEastPossible = false;
	protected boolean goWestPossible = false;
	protected int movesOptions = 0;
	protected int countMoves = 0;

	public LocationTracker() {
	}

	public int getX() {
		return currentPositionX;
	}

	public void setX(int x) {
		this.currentPositionX = x;
	}

	public int getY() {
		return currentPositionY;
	}

	public void setY(int y) {
		this.currentPositionY = y;
	}

	public boolean isGoSouthPossible() {
		return goSouthPossible;
	}

	public void setGoSouthPossible(boolean goSouthPossible) {
		this.goSouthPossible = goSouthPossible;
	}

	public boolean isGoNorthPossible() {
		return goNorthPossible;
	}

	public void setGoNorthPossible(boolean goNorthPossible) {
		this.goNorthPossible = goNorthPossible;
	}

	public boolean isGoEastPossible() {
		return goEastPossible;
	}

	public void setGoEastPossible(boolean goEastPossible) {
		this.goEastPossible = goEastPossible;
	}

	public boolean isGoWestPossible() {
		return goWestPossible;
	}

	public void setGoWestPossible(boolean goWestPossible) {
		this.goWestPossible = goWestPossible;
	}

	public int getMovesOptions() {
		return movesOptions;
	}

	public void setMovesOptions(int movesOptions) {
		this.movesOptions = movesOptions;
	}

	public int getCountMoves() {
		return countMoves;
	}

	public void setCountMoves(int countMoves) {
		this.countMoves = countMoves;
	}

	@Override
	public void printCurrentLocation() {
		System.out.println("currentlocationx = " + currentPositionX);
		System.out.println("currentlocationy = " + currentPositionY);
		System.out.println();
	}

	@Override
	public void printTrackedPath() {
		for (int k = 0; k < trackerMaze.length; k++) {
			for (int l = 0; l < trackerMaze.length; l++) {
				System.out.print(trackerMaze[k][l] + " ");
			}
			System.out.println();
		}
	}

}
