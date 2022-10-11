import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

	private int currentLocationX;
	private int currentLocationY;
	private int startLocationX;
	private int startLocationY;
	private int endLocationX;
	private int endLocationY;
	private int sizeMazeX;
	private int sizeMazeY;
	private boolean goSouthPossible = false;
	private boolean goNorthPossible = false;
	private boolean goEastPossible = false;
	private boolean goWestPossible = false;
	private int[][] maze;
	private MazeStack pathStack = new MazeStack();
	private MazeStack branchStack = new MazeStack();

	public Maze() {

	}

	public void readFromFile() {
		try {
			FileInputStream fileReader = new FileInputStream("maze5.txt");
			Scanner input = new Scanner(fileReader);
			sizeMazeX = input.nextInt();
			sizeMazeY = input.nextInt();
			maze = new int[sizeMazeX][sizeMazeY];

			startLocationX = input.nextInt();
			startLocationY = input.nextInt();
//			startPoint = new int[startX][startY];

			endLocationX = input.nextInt();
			endLocationY = input.nextInt();
//			endPoint = new int[endX][endY];

			for (int i = 0; i < sizeMazeX; i++) {
				for (int j = 0; j < sizeMazeY; j++) {
					maze[i][j] = input.nextInt();

				}
			}
			input.close();
			printMaze();
		}

		catch (IOException e) {
			System.out.println("Error reading file");
		}

	}

	public void move() {

		int count = 0;
		int moveCount = 0; // moves since last branch

		currentLocationX = startLocationX;
		currentLocationY = startLocationY;

		System.out.println("currentlocationx = " + currentLocationX);
		System.out.println("currentlocationy = " + currentLocationY);
		System.out.println("maze current location" + maze[currentLocationX][currentLocationY]);

		System.out.println(maze[currentLocationX + 1][currentLocationY]);

		branchStack.push(currentLocationY);
		branchStack.push(currentLocationX);

		goSouthPossible = true;
		goNorthPossible = true;
		goEastPossible = true;
		goWestPossible = true;

		// Happens only if the current location is 1 meaning not a block
		while (maze[currentLocationX][currentLocationY] == 1) {
			// This happens when the current location is the end point. Maze solved
			if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
				System.out.println("Solved the maze!!");
				System.out.println(endLocationX + " / " + endLocationY);
				System.exit(0);
			}

			System.out.println("Current Location " + "X: " + currentLocationX + " Y: " + currentLocationY);
			count = 0;
			moveCount++;
			pathStack.push(currentLocationY);
			pathStack.push(currentLocationX);

			// Possible path to go is x + 1
			// currentLocationX != 0 &&
			if (goSouthPossible == true) {
				try {
					if (maze[currentLocationX + 1][currentLocationY] == 0) {
						goSouthPossible = false;
						System.out.println("Go south False");
					} else {
						goSouthPossible = true;
						goNorthPossible = false;
						goEastPossible = true;
						goWestPossible = true;
						System.out.println("Go south True");
						count++;
//						if (maze[currentLocationX + 2][currentLocationY] == 0) {
////							goSouthClosed = true;
//							goSouthPossible = false;
//						}

					}
				} catch (Exception e) {
					goSouthPossible = false;
					System.out.println("Cannot go south");
				}
			}

			// Possible path to go is x - 1
			// currentLocationX != sizeMazeX &&
			if (goNorthPossible == true) {
				try {
					if (maze[currentLocationX - 1][currentLocationY] == 0) {
						goNorthPossible = false;
						System.out.println("Go north False");
					} else {
						goNorthPossible = true;
						goSouthPossible = false;
						goEastPossible = true;
						goWestPossible = true;
						System.out.println("Go north True");
						count++;

					}
				} catch (Exception e) {
					goNorthPossible = false;
					System.out.println("Cannot go north");
				}
			}
			// Possible path to go is y + 1
//			currentLocationY != 0 &&
			if (goEastPossible == true) {
				try {

					if (maze[currentLocationX][currentLocationY + 1] == 0) {
						goEastPossible = false;
						System.out.println("Go east False");
					} else {
						goEastPossible = true;
						goWestPossible = false;
						goSouthPossible = true;
						goNorthPossible = true;
						System.out.println("Go east True");
						count++;

					}
				} catch (Exception e) {
					goEastPossible = false;
					System.out.println("Cannot go east");
				}

			}
			// Possible path to go is y - 1
			// currentLocationY != sizeMazeY &&
			if (goWestPossible == true) {
				try {
					if (maze[currentLocationX][currentLocationY - 1] == 0) {
						goWestPossible = false;
						System.out.println("Go west False");
					} else {
						goWestPossible = true;
						goEastPossible = false;
						goSouthPossible = true;
						goNorthPossible = true;
						System.out.println("Go west True");
						count++;

					}
				} catch (Exception e) {
					goWestPossible = false;
					System.out.println("Cannot go west");
				}
			}
			// After checking which next step is possible goes to check if there is just 1,
			// 2 or zero options
			System.out.println("count " + count);
			// If there is only one path to follow. Updates location to that one
			if (count == 1) {

				// Only one way to go
				if (goSouthPossible == true) {
					currentLocationX = currentLocationX + 1;

				}
				if (goNorthPossible == true) {
					currentLocationX = currentLocationX - 1;

				}
				if (goEastPossible == true) {
					System.out.println("Update location for 3");
					currentLocationY = currentLocationY + 1;

				}
				if (goWestPossible == true) {
					currentLocationY = currentLocationY - 1;

				}
				// If there are no options to where to go next, this brings back to the last
				// option where there were more than 1 options
			}
			if (count == 0) {

				// Go to last branch
				if (!branchStack.isEmpty()) {

					if (maze[currentLocationX + 1][currentLocationY] == 0) {
						goSouthPossible = false;
						goNorthPossible = false;
						goEastPossible = true;
						goWestPossible = true;
						System.out.println("Go south False");
					} else if (maze[currentLocationX - 1][currentLocationY] == 0) {
						goSouthPossible = false;
						goNorthPossible = false;
						goEastPossible = true;
						goWestPossible = true;
						System.out.println("Go north False");
					} else if (maze[currentLocationX][currentLocationY + 1] == 0) {
						goSouthPossible = true;
						goNorthPossible = true;
						goEastPossible = false;
						goWestPossible = false;
						System.out.println("Go east False");
					} else if (maze[currentLocationX][currentLocationY - 1] == 0) {
						goSouthPossible = true;
						goNorthPossible = true;
						goEastPossible = false;
						goWestPossible = false;
						System.out.println("Go west False");
					}

					currentLocationX = branchStack.pop();
					currentLocationY = branchStack.pop();

				} else {
					System.out.println("Not possible");
					System.exit(0);
				}
				// When there are more than 1 paths to follow, stacks the location for future
				// come back
			}
			if (count > 1) {
				System.out.println("GOT TO COUNT 2");
				// add current location to last branch
				// pick way to go

				branchStack.push(currentLocationY);
				branchStack.push(currentLocationX);

				if (goSouthPossible = true) {
					currentLocationX = currentLocationX + 1;

				} else if (goNorthPossible = true) {
					currentLocationX = currentLocationX - 1;

				} else if (goEastPossible = true) {
					currentLocationY = currentLocationY + 1;

				} else if (goWestPossible = true) {
					currentLocationY = currentLocationY - 1;

				}
			}

		}

	}

	// Prints maze in console inside the class
	public void printMaze() {
		for (int k = 0; k < maze.length; k++) {
			for (int l = 0; l < maze.length; l++) {
				System.out.print(maze[k][l]);
			}
			System.out.println();
		}
	}

	// Override for toString method works only outside the class
	@Override
	public String toString() {
		String line = "";
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				line += " " + maze[row][col];
				if (col == 9) {
					line += "\n";
				}
			}
		}
		return line;
	}

}
