
public class Maze {

	private int x;
	private int y;
	private int[][] startPoint;
	private int[][] endPoint;
	private int[][] currentPoint;

	public Maze(int x, int y, int[][] startPoint, int[][] endPoint) {
		this.x = x;
		this.y = y;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

}
