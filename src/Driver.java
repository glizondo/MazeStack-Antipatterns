
public class Driver {

	public static void main(String[] args) {

		MazeGame maze = new MazeGame();
		MazeTracker tracker = new MazeTracker(maze);
		maze.printMap();
		System.out.println();
		tracker.move();

	}

}
