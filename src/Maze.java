import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

	private int startLocationX;
	private int startLocationY;
	private int endLocationX;
	private int endLocationY;
	private int sizeMazeX;
	private int sizeMazeY;
	private int[][] mapArray;
	private boolean solved = false;

	public Maze() {
		readMazeFromFile();
	}

	public int[][] readMazeFromFile() {
		try {
			// Maze is put to a file reader in order to be read
			FileInputStream fileReader = new FileInputStream("maze3.txt");
			Scanner input = new Scanner(fileReader);
			// first entry in the file is the size of the maze, which is read here
			sizeMazeX = input.nextInt();
			sizeMazeY = input.nextInt();
			// using that size info, the maze is created
			mapArray = new int[sizeMazeX][sizeMazeY];

			// the starting location is read next
			startLocationX = input.nextInt();
			startLocationY = input.nextInt();

			// and then the ending location
			endLocationX = input.nextInt();
			endLocationY = input.nextInt();

			// the actual maze is now read and put into a 2d array
			for (int i = 0; i < sizeMazeX; i++) {
				for (int j = 0; j < sizeMazeY; j++) {
					mapArray[i][j] = input.nextInt();

				}
			}
			input.close();
		}

		catch (IOException e) {
			System.out.println("Error reading file");
		}
		return mapArray;

	}

	// Prints maze in console inside the class
	public void printMaze() {
		for (int k = 0; k < mapArray.length; k++) {
			for (int l = 0; l < mapArray.length; l++) {
				System.out.print(mapArray[k][l] + " ");
			}
			System.out.println();
		}
	}

	public int getStartLocationX() {
		return startLocationX;
	}

	public void setStartLocationX(int startLocationX) {
		this.startLocationX = startLocationX;
	}

	public int getStartLocationY() {
		return startLocationY;
	}

	public void setStartLocationY(int startLocationY) {
		this.startLocationY = startLocationY;
	}

	public int getEndLocationX() {
		return endLocationX;
	}

	public void setEndLocationX(int endLocationX) {
		this.endLocationX = endLocationX;
	}

	public int getEndLocationY() {
		return endLocationY;
	}

	public void setEndLocationY(int endLocationY) {
		this.endLocationY = endLocationY;
	}

	public int getSizeMazeX() {
		return sizeMazeX;
	}

	public void setSizeMazeX(int sizeMazeX) {
		this.sizeMazeX = sizeMazeX;
	}

	public int getSizeMazeY() {
		return sizeMazeY;
	}

	public void setSizeMazeY(int sizeMazeY) {
		this.sizeMazeY = sizeMazeY;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public int[][] getMapArray() {
		return mapArray;
	}

	public void setMapArray(int[][] mapArray) {
		this.mapArray = mapArray;
	}
	

	public boolean isBlock(int currentPositionX, int currentPositionY) {
		if (mapArray[currentPositionX][currentPositionY] == 0) {
			return true;
		} else {
			return false;
		}
	}

}
