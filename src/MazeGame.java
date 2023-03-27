import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeGame extends BoardGame {

	public MazeGame() {
		readFromFile();
	}

	public int[][] readFromFile() {
		try {
			FileInputStream fileReader = new FileInputStream("maze3.txt");
			Scanner input = new Scanner(fileReader);

			sizeMazeX = input.nextInt();
			sizeMazeY = input.nextInt();
			mapArray = new int[sizeMazeX][sizeMazeY];

			startLocationX = input.nextInt();
			startLocationY = input.nextInt();

			endLocationX = input.nextInt();
			endLocationY = input.nextInt();

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

}
