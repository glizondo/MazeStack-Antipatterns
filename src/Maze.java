import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

	private int x;
	private int y;
	private int[][] startPoint;
	private int[][] endPoint;
	private int[][] currentPoint;
	private int[][] maze;

	public Maze() {

	}

	public void readFromFile() {
		try {
			FileInputStream fileReader = new FileInputStream("maze.txt");
			Scanner input = new Scanner(fileReader);
			int sizeX = input.nextInt();
			int sizeY = input.nextInt();
			maze = new int[sizeX][sizeY];

			int startX = input.nextInt();
			int startY = input.nextInt();
			startPoint = new int[startX][startY];

			int endX = input.nextInt();
			int endY = input.nextInt();
			endPoint = new int[endX][endY];

			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					maze[i][j] = input.nextInt();

				}
			}
			// Prints maze in console
			printMaze();
			input.close();
		}

		catch (IOException e) {
			System.out.println("Error reading file");
		}

	}

	public void move() {

	}

	// Prints maze in console
	public void printMaze() {
		for (int k = 0; k < maze.length; k++) {
			for (int l = 0; l < maze.length; l++) {
				System.out.print(maze[k][l]);
			}
			System.out.println();
		}
	}

}
