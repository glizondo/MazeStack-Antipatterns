
public class MazeTracker extends LocationTracker {
	
	protected MazeStack pathStack = new MazeStack();
	protected MazeStack branchStack = new MazeStack();
	protected MazeGame maze = new MazeGame();

	public MazeTracker(MazeGame maze) {
		super(maze);
	}
	
	@Override
	public void setStartingLocation() {
		currentPositionX = maze.getStartLocationX();
		currentPositionY = maze.getStartLocationY();
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
			printTrackedPath();
			handlePossibleDirections();
			printMovesOptions();
			handlePossiblePaths();
		}
		trackerMaze[currentPositionX][currentPositionY] = 0;
	}

	private void handlePossiblePaths() {
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

	private void handlePossibleDirections() {
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
