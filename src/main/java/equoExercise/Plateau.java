package equoExercise;

// Might be innecesary to create a class just for a grid, but assuming somewhen the drones
// will have to detect oil, and that in the future plateaus might stop being rectangular,
// having a separate class for it will make future changes easier. Also it's a little cleaner

// final as it's a singleton
public final class Plateau {
	
	private static Plateau instance = null;
	
	public Plateau(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public static Plateau getInstance(int xSize, int ySize) {
		if (instance == null) {
			instance = new Plateau(xSize, ySize);
		}
		return instance;
	}
	
	private int xSize;
	private int ySize;
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	
	public String getSize() {
		return (xSize + " " + ySize);
	}
	
}
