
public class LocationTracker {

	private int currentPositionX;
	private int currentPositionY;
	private int[][] trackerMaze;
	private boolean goSouthPossible = false;
	private boolean goNorthPossible = false;
	private boolean goEastPossible = false;
	private boolean goWestPossible = false;
	private MazeStack pathStack = new MazeStack();
	private MazeStack branchStack = new MazeStack();
	private Maze maze = new Maze();
	int movesOptions = 0;
	int countMoves = 0;

	public LocationTracker(Maze maze) {
		trackerMaze = maze.getMapArray();
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

	public void setStartingLocation() {
		currentPositionX = maze.getStartLocationX();
		currentPositionY = maze.getStartLocationY();
	}

	public void printCurrentLocation() {
		System.out.println("currentlocationx = " + currentPositionX);
		System.out.println("currentlocationy = " + currentPositionY);
	}

	public void printTrackedMaze() {
		for (int k = 0; k < trackerMaze.length; k++) {
			for (int l = 0; l < trackerMaze.length; l++) {
				System.out.print(trackerMaze[k][l] + " ");
			}
			System.out.println();
		}
	}

	public void changePossibleDirectionsSNEW(boolean goSouthPossible, boolean goNorthPossible, boolean goEastPossible,
			boolean goWestPossible) {
		setGoSouthPossible(goSouthPossible);
		setGoNorthPossible(goNorthPossible);
		setGoEastPossible(goEastPossible);
		setGoWestPossible(goWestPossible);
	}

	public void move() {

		setStartingLocation();
		branchStack.push(currentPositionY);
		branchStack.push(currentPositionX);
		changePossibleDirectionsSNEW(true, true, true, true);
		while (!maze.isSolved()) {
			trackerMaze[currentPositionX][currentPositionY] = 0;
			changePossibleDirectionsSNEW(false, false, false, false);
			closeProgramIfMazeSolved();
			printCurrentLocation();
			setMovesOptions(0);
			countMoves++;
			pathStack.push(currentPositionY);
			pathStack.push(currentPositionX);
			printTrackedMaze();

			if (!isGoSouthPossible()) {
				handleGoSouthNotPossible();
			}

			if (!isGoNorthPossible()) {
				handleGoNorthNotPossible();
			}

			if (!isGoEastPossible()) {
				handleGoEastNotPossible();
			}

			if (!isGoWestPossible()) {
				handleGoWestNotPossible();
			}
			printMovesOptions();
			if (movesOptions == 1) {
				handleOnePathToGo();
			}
			if (movesOptions == 0) {
				handleNoPathsToGo();
			}
			if (movesOptions > 1) {
				handleMoreThanOnePathToGo();
			}
		}
		trackerMaze[currentPositionX][currentPositionY] = 0;
	}

	private void printMovesOptions() {
		System.out.println("ways to go: " + movesOptions);
	}

	private void handleMoreThanOnePathToGo() {
		branchStack.push(currentPositionY);
		branchStack.push(currentPositionX);
		if (goSouthPossible != false) {
			currentPositionX = currentPositionX + 1;

		} else if (goNorthPossible != false) {
			currentPositionX = currentPositionX - 1;

		} else if (goEastPossible != false) {
			currentPositionY = currentPositionY + 1;

		} else if (goWestPossible != false) {
			currentPositionY = currentPositionY - 1;
		}
	}

	private void handleNoPathsToGo() {
		if (!branchStack.isEmpty()) {
			if (currentPositionX < maze.getSizeMazeX() - 1) {
				if (trackerMaze[currentPositionX + 1][currentPositionY] == 0) {
					goSouthPossible = false;
				} else if (trackerMaze[currentPositionX - 1][currentPositionY] == 0) {
					goNorthPossible = false;
				}
			}
			if (currentPositionY < maze.getSizeMazeY() - 1) {
				if (trackerMaze[currentPositionX][currentPositionY + 1] == 0) {
					goEastPossible = false;
				} else if (trackerMaze[currentPositionX][currentPositionY - 1] == 0) {
					goEastPossible = true;
				}
			}
			currentPositionX = branchStack.pop();
			currentPositionY = branchStack.pop();
		} else {
			System.exit(0);
		}
	}

	private void handleOnePathToGo() {
		if (goSouthPossible) {
			currentPositionX = currentPositionX + 1;

		}
		if (goNorthPossible) {
			currentPositionX = currentPositionX - 1;

		}
		if (goEastPossible) {
			currentPositionY = currentPositionY + 1;

		}
		if (goWestPossible) {
			currentPositionY = currentPositionY - 1;

		}
	}

	private void handleGoWestNotPossible() {
		try {
			if (trackerMaze[currentPositionX][currentPositionY - 1] == 0) {
				goWestPossible = false;
			} else {
				goWestPossible = true;
				movesOptions++;

			}
		} catch (Exception e) {
			goWestPossible = false;
		}
	}

	private void handleGoEastNotPossible() {
		try {

			if (trackerMaze[currentPositionX][currentPositionY + 1] == 0) {
				goEastPossible = false;
			} else {
				goEastPossible = true;
				movesOptions++;

			}
		} catch (Exception e) {
			goEastPossible = false;
		}
	}

	private void handleGoNorthNotPossible() {
		try {
			if (trackerMaze[currentPositionX - 1][currentPositionY] == 0) {
				goNorthPossible = false;
			} else {
				goNorthPossible = true;
				movesOptions++;

			}
		} catch (Exception e) {
			goNorthPossible = false;
		}
	}

	private void handleGoSouthNotPossible() {
		try {
			if (trackerMaze[currentPositionX + 1][currentPositionY] == 0) {
				goSouthPossible = false;
			} else {
				goSouthPossible = true;
				movesOptions++;

			}
		} catch (Exception e) {
			goSouthPossible = false;
		}
	}

	private void closeProgramIfMazeSolved() {
		if (currentPositionX == maze.getEndLocationX() && currentPositionY == maze.getEndLocationY()) {
			maze.setSolved(true);
			System.out.println("Maze solved in " + countMoves + " moves");
			printCurrentLocation();
			System.out.println("MAZE SOLVED");
			System.exit(0); // after solving, the program ends
		}
	}

}
