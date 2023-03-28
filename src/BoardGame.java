
abstract class BoardGame implements IBoardGame {

	protected int startLocationX;
	protected int startLocationY;
	protected int endLocationX;
	protected int endLocationY;
	protected int sizeMazeX;
	protected int sizeMazeY;
	protected int[][] mapArray;
	protected boolean solved = false;

	@Override
	public void printMap() {
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

}
