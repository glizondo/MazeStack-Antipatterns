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
	private boolean nextPoint1Possible = false;
	private boolean nextPoint2Possible = false;
	private boolean nextPoint3Possible = false;
	private boolean nextPoint4Possible = false;
	private boolean closedPoint1 = false;
	private boolean closedPoint2 = false;
	private boolean closedPoint3 = false;
	private boolean closedPoint4 = false;

//	private int[][] startPoint;
//	private int[][] endPoint;
//	private int[][] currentPoint;
	private int[][] maze;
	private MazeStack pathStack = new MazeStack();
	private MazeStack branchStack = new MazeStack();

	public Maze() {

	}

	public void readFromFile() {
		try {
			FileInputStream fileReader = new FileInputStream("maze3.txt");
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

		int nextPointX1 = currentLocationX + 1;
		int nextPointX2 = currentLocationX - 1;
		int nextPointY1 = currentLocationY + 1;
		int nextPointY2 = currentLocationY - 1;

		System.out.println("currentlocationx = " + currentLocationX);
		System.out.println("currentlocationy = " + currentLocationY);
		System.out.println("maze current location" + maze[currentLocationX][currentLocationY]);

		System.out.println(maze[currentLocationX + 1][currentLocationY]);

		branchStack.push(currentLocationY);
		branchStack.push(currentLocationX);

		while (maze[currentLocationX][currentLocationY] == 1) {

			if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
				System.out.println("Solved the maze!!");
				System.out.println(endLocationX + " / " + endLocationY);
				System.exit(0);
			}

			System.out.println("Current Location " + "X: " + currentLocationX + "Y: " + currentLocationY);
			count = 0;
			moveCount++;
			pathStack.push(currentLocationY);
			pathStack.push(currentLocationX);
			if (currentLocationX != 0) {
				try {
					if (maze[currentLocationX + 1][currentLocationY] == 0) {
						nextPoint1Possible = false;
						System.out.println("Line1");
					} else {
						nextPoint1Possible = true;
						System.out.println("Line1True");
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (currentLocationX != sizeMazeX) {
				try {
					if (maze[currentLocationX - 1][currentLocationY] == 0) {
						nextPoint2Possible = false;
						System.out.println("Line2");
					} else {
						nextPoint2Possible = true;
						System.out.println("Line2True");
						count++;

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

//			if (currentLocationY != 0) {
			try {

				if (maze[currentLocationX][currentLocationY + 1] == 0) {
					nextPoint3Possible = false;
					System.out.println("Line3");
				} else {
					nextPoint3Possible = true;
					closedPoint4 = true;
					System.out.println("Line3True");
					count++;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

//			}

			if (currentLocationY != sizeMazeY && closedPoint4 == false) {
				try {
					if (maze[currentLocationX][currentLocationY - 1] == 0) {
						nextPoint4Possible = false;
						System.out.println("Line4");
					} else {
						nextPoint4Possible = true;
						System.out.println("Line4True");
						count++;

					}
				} catch (Exception e) {
					nextPoint4Possible = false;
					e.printStackTrace();
				}
			}

			System.out.println("count " + count);
			if (count == 1) {

				// Only one way to go
				if (nextPoint1Possible == true) {
					currentLocationX = currentLocationX + 1;

				}
				if (nextPoint2Possible == true) {
					currentLocationX = currentLocationX - 1;

				}
				if (nextPoint3Possible == true) {
					System.out.println("Update location for 3");
					currentLocationY = currentLocationY + 1;

				}
				if (nextPoint4Possible == true) {
					currentLocationY = currentLocationY - 1;

				}
			} else if (count == 0) {

				// Go to last branch
				if (!branchStack.isEmpty()) {
					currentLocationX = branchStack.pop();
					currentLocationY = branchStack.pop();
				} else {
					System.out.println("Not possible");
					System.exit(0);
				}

			} else if (count > 1) {
				// add current location to last branch
				// pick way to go

				branchStack.push(currentLocationY);
				branchStack.push(currentLocationX);

				if (nextPoint1Possible = true) {
					currentLocationX = currentLocationX + 1;

				} else if (nextPoint2Possible = true) {
					currentLocationX = currentLocationX - 1;

				} else if (nextPoint3Possible = true) {
					currentLocationY = currentLocationY + 1;

				} else if (nextPoint4Possible = true) {
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
