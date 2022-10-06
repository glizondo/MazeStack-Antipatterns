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
//		this.x = x;
//		this.y = y;
//		this.startPoint = startPoint;
//		this.endPoint = endPoint;
	}

	public void readFromFile() {
		try {
			FileInputStream fileReader = new FileInputStream("maze.txt");
			Scanner input = new Scanner(fileReader);
			int sizeX = input.nextInt();
			System.out.println(sizeX);
			int sizeY = input.nextInt();
			System.out.println(sizeY);

			maze = new int[sizeX][sizeY];
			System.out.println(maze);
			int startX = input.nextInt();
			System.out.println(startX);

			int startY = input.nextInt();
			System.out.println(startY);
			startPoint = new int[startX][startY];

			int endX = input.nextInt();
			System.out.println(endX);

			int endY = input.nextInt();
			System.out.println(endY);
			endPoint = new int[endX][endY];

			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					maze[i][j] = input.nextInt();

				}
			}
			for (int k = 0; k < 10; k++) {
				for (int l = 0; l < 10; l++) {
					System.out.print(maze[k][l]);
				}
				System.out.println();
			}

//			System.out.println(maze);
		}

		catch (IOException e) {
			System.out.println("Error reading file");
		}
	}

	public void move() {
	
	}

	public void printMaze() {
		for (int k = 0; k < 10; k++) {
			for (int l = 0; l < 10; l++) {
				System.out.print(maze[k][l]);
			}
			System.out.println();
		}
	}

//	public int[][] printMaze(){
//		
//	}

}
