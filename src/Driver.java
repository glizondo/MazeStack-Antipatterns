
public class Driver {

	public static void main(String[] args) {

		Maze maze = new Maze();
		LocationTracker tracker = new LocationTracker(maze);
		maze.printMaze();
		System.out.println();
		tracker.move();

	}

}
